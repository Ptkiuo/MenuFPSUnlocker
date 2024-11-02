package com.ptk671.menufpsunlocker.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow public ClientWorld world;
	@Shadow public Screen currentScreen;
	@Shadow public GameOptions options;
	@Inject(at = @At("RETURN"), method = "getMaxFramerate", cancellable = true)	private void getMaxFramerate(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue( world == null && currentScreen != null ? 120: options.maxFramerate);
    }
}
