package com.MonboSoNasty.meteorchatgptx;

import com.MonboSoNasty.meteorchatgptx.modules.ChatGPTResponder;
import com.MonboSoNasty.meteorchatgptx.hud.ChatGPTHud;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.commands.Commands;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    // Module category
    public static final Category CHATGPT_CATEGORY = new Category("ChatGPT");

    // HUD group
    public static final HudGroup CHATGPT_HUD_GROUP = new HudGroup("ChatGPT");

    @Override
    public void onInitialize() {
        LOG.info("Initializing ChatGPT Meteor Addon");

        // Register modules
        Modules.get().add(new ChatGPTResponder());

        // Register HUD elements
        Hud.get().register(ChatGPTHud.INFO);

        LOG.info("ChatGPT Meteor Addon initialized!");
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CHATGPT_CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.MonboSoNasty.meteorchatgptx";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("MonboSoNasty", "Meteor-ChatgptX");
    }
}
