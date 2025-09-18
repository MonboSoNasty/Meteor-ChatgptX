package com.monbosonasty.meteorchatgptx;

import meteordevelopment.meteorclient.systems.modules.Modules;
import com.monbosonasty.meteorchatgptx.modules.ChatGPTResponder;

public class Addon {

    // Called when the addon initializes
    public static void onInitialize() {
        // Register the ChatGPTResponder module
        Modules.get().add(new ChatGPTResponder());

        // Debug message to confirm registration
        System.out.println("[ChatGPTResponder] Module registered successfully!");
    }
}
