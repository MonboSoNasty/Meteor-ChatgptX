package com.MonboSoNasty.meteorchatgptx.hud;

import com.MonboSoNasty.meteorchatgptx.Addon;
import com.MonboSoNasty.meteorchatgptx.modules.ChatGPTResponder;
import meteordevelopment.meteorclient.systems.hud.*;
import meteordevelopment.meteorclient.utils.render.color.Color;

public class ChatGPTHud extends HudElement {

    // HudElementInfo now needs explicit type
    public static final HudElementInfo<ChatGPTHud> INFO = new HudElementInfo<>(
            Addon.CHATGPT_HUD_GROUP,
            "chatgpt-hud",
            "Displays last ChatGPT answer.",
            ChatGPTHud::new
    );

    public ChatGPTHud(HudElementInfo<?> info) {
        super(info);
    }

    @Override
    public void update(HudRenderer renderer) {
        String lastAnswer = ChatGPTResponder.getLastAnswer();
        renderer.text(
                "ChatGPT: " + (lastAnswer != null ? lastAnswer : "No answer yet"),
                x,
                y,
                Color.WHITE
        );
    }
}
