package com.monbosonasty.meteorchatgptx.modules;

import meteordevelopment.meteorclient.events.game.ReceiveMessageEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.monbosonasty.meteorchatgptx.Addon;

public class ChatGPTResponder extends Module {
    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> enabled = sgGeneral.add(new BoolSetting.Builder()
        .name("enabled")
        .description("Enable/disable the ChatGPT responder.")
        .defaultValue(true)
        .build()
    );

    private final Setting<String> keywords = sgGeneral.add(new StringSetting.Builder()
        .name("trigger-words")
        .description("Comma-separated keywords to look for in chat messages.")
        .defaultValue("unscramble,math,solve")
        .build()
    );

    private final Setting<Boolean> autoSend = sgGeneral.add(new BoolSetting.Builder()
        .name("auto-send")
        .description("Automatically send ChatGPT's answer in chat.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> previewOnly = sgGeneral.add(new BoolSetting.Builder()
        .name("preview-only")
        .description("Show the answer in client chat instead of sending automatically.")
        .defaultValue(false)
        .build()
    );

    private String lastAnswer = null;

    public ChatGPTResponder() {
        super(Addon.CHATGPT_CATEGORY, "chatgpt-responder", "Automatically answers chat games using ChatGPT.");
    }

    @EventHandler
    private void onMessage(ReceiveMessageEvent event) {
        if (!enabled.get()) return;

        String message = event.getMessage().getString().toLowerCase();
        List<String> triggerWords = Arrays.asList(keywords.get().toLowerCase().split(","));

        for (String word : triggerWords) {
            if (message.contains(word.trim())) {
                askChatGPT(message).thenAccept(answer -> {
                    if (answer != null && mc.player != null) {
                        lastAnswer = answer;
                        mc.execute(() -> {
                            if (previewOnly.get()) {
                                mc.player.sendMessage(Text.literal("[ChatGPT] " + answer), false);
                            }
                            if (autoSend.get() && !previewOnly.get()) {
                                mc.player.networkHandler.sendChatMessage(answer);
                            }
                        });
                    }
                });
                break;
            }
        }
    }

    private CompletableFuture<String> askChatGPT(String prompt) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String apiKey = System.getenv("OPENAI_API_KEY");
                if (apiKey == null) return null;

                String body = "{ \"model\": \"gpt-4o-mini\", \"messages\": [ { \"role\": \"user\", \"content\": \"" + prompt.replace("\"", "\\\"") + "\" } ] }";

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + apiKey)
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                String res = response.body();
                int start = res.indexOf("\"content\":\"") + 11;
                if (start == -1) return null;
                int end = res.indexOf("\"", start);
                if (end == -1) return null;

                return res.substring(start, end).replace("\\n", " ").replace("\\\"", "\"");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
