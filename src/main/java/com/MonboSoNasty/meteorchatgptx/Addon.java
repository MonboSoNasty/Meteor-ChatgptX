package com.MonboSoNasty.meteorchatgptx;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import com.MonboSoNasty.meteorchatgptx.modules.ChatGPTResponder;

public class Addon {
    // Just pass the category name
    public static final Category CHATGPT_CATEGORY = new Category("ChatGPT");

    public static void onInitialize() {
        Modules.get().add(new ChatGPTResponder());
        System.out.println("[ChatGPTResponder] Module registered successfully!");
    }
}
