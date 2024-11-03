package com.ptk671.mfpu.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow public WorldClient world;
    @Shadow public GuiScreen currentScreen;
    @Shadow public GameSettings gameSettings;
    @Inject(at = @At("RETURN"), method = "getLimitFramerate", cancellable = true)	private void getLimitFramerate(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue( world == null && currentScreen != null ? 120: gameSettings.limitFramerate);
    }
}
