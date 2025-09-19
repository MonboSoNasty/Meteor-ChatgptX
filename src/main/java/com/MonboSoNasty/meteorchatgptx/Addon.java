package com.MonboSoNasty.meteorchatgptx;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.MonboSoNasty.meteorchatgptx.modules.ChatGPTResponder;

public class Addon {
    // Create a new category for your modules
    public static final Category CHATGPT_CATEGORY = new Category("ChatGPT");

    public static void onInitialize() {
        // Register your module under the new category
        Modules.get().add(new ChatGPTResponder());
        System.out.println("[ChatGPTResponder] Module registered successfully!");
    }
}
