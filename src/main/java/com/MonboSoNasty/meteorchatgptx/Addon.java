package com.MonboSoNasty.meteorchatgptx.addon;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.MonboSoNasty.meteorchatgptx.addon.modules.ChatGPTResponder;

public class Addon {
    public static final Category CHATGPT_CATEGORY = new Category("ChatGPT", "ChatGPT Modules");

    public static void onInitialize() {
        Modules.get().add(new ChatGPTResponder());
        System.out.println("[ChatGPTResponder] Module registered successfully!");
    }
}

