package io.github.homchom.recode.mod.mixin.game;

import blue.endless.jankson.annotation.Nullable;
import io.github.homchom.recode.sys.networking.State;
import io.github.homchom.recode.sys.player.DFInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MClient {

    @Inject(method = "openScreen(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"), cancellable = true)
    public void openScreen(@Nullable Screen screen, CallbackInfo cbi) {
        if(Minecraft.getInstance().player == null) {
            DFInfo.currentState.setMode(State.Mode.OFFLINE);
        }
    }

}