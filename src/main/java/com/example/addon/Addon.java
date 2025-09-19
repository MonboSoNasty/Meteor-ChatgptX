package com.monbosonasty.meteorchatgptx;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.monbosonasty.meteorchatgptx.modules.ChatGPTResponder;

public class Addon {
    // Category for ChatGPT modules
    public static final Category CHATGPT_CATEGORY = new Category("ChatGPT", "ChatGPT Modules");

    public static void onInitialize() {
        // Register ChatGPTResponder
        Modules.get().add(new ChatGPTResponder());
        System.out.println("[ChatGPTResponder] Module registered successfully!");
    }
}
