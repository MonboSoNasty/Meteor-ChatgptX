package com.MonboSoNasty.meteorchatgptx;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.MonboSoNasty.meteorchatgptx.modules.ChatGPTResponder;

public class Addon {
    // Category for ChatGPT modules in Meteor GUI
    public static final Category CHATGPT_CATEGORY = new Category("ChatGPT", "ChatGPT Modules");

    public static void onInitialize() {
        // Register the ChatGPTResponder module
        Modules.get().add(new ChatGPTResponder());
        System.out.println("[ChatGPTResponder] Module registered successfully!");
    }
}
