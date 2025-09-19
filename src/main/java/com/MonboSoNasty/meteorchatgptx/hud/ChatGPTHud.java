package com.MonboSoNasty.meteorchatgptx.hud;

import com.MonboSoNasty.meteorchatgptx.Addon;
import com.MonboSoNasty.meteorchatgptx.modules.ChatGPTResponder;
import meteordevelopment.meteorclient.systems.hud.*;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;

public class ChatGPTHud extends HudElement {

    public static final HudElementInfo<ChatGPTHud> INFO = new HudElementInfo<>(Addon.CHATGPT_HUD_GROUP, "chatgpt-hud", "Displays last ChatGPT answer.", ChatGPTHud::new);

    public ChatGPTHud(HudGroup group) {
        super(group, "chatgpt-hud", "Displays last ChatGPT answer.");
    }

    @Override
    public void update(HudRenderer renderer) {
        String lastAnswer = ChatGPTResponder.getLastAnswer();
        renderer.text("ChatGPT: " + (lastAnswer != null ? lastAnswer : "No answer yet"), x, y, 0xFFFFFF);
    }
}
