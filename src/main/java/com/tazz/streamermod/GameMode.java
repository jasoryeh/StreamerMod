package com.tazz.streamermod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

//Copied directly from Shiny's mod with a few, minor edits
public class GameMode {


    public static final int CUSTOM_INV = 0;

    private static final String[] desc = {"key.gamemode.desc"};

    private static final int[] keyValues = {Keyboard.KEY_L};
    private final KeyBinding[] keys;

    public GameMode() {
        keys = new KeyBinding[desc.length];
        for (int i = 0; i < desc.length; ++i) {
            keys[i] = new KeyBinding(desc[i], keyValues[i], "Streamer Mod");
            ClientRegistry.registerKeyBinding(keys[i]);
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (keys[CUSTOM_INV].isPressed() && FMLClientHandler.instance().getClient().inGameHasFocus) {
            if (Minecraft.getMinecraft().playerController.getCurrentGameType().equals(WorldSettings.GameType.CREATIVE)) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gamemode 3");
            } else if (Minecraft.getMinecraft().playerController.getCurrentGameType().equals(WorldSettings.GameType.SPECTATOR)) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gamemode 1");
            }
        }
    }
}
