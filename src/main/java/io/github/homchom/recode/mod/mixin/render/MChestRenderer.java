package io.github.homchom.recode.mod.mixin.render;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.homchom.recode.Recode;
import io.github.homchom.recode.mod.config.Config;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestRenderer.class)
public abstract class MChestRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    @Inject(method = "render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At("HEAD"), cancellable = true)
    public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, CallbackInfo ci) {
        if (Config.getBoolean("chestReplacement") && entity instanceof ChestBlockEntity) {
            ci.cancel();

            BlockState state = Blocks.BARREL.defaultBlockState();
            Recode.MC.getBlockRenderer().renderSingleBlock(state, matrices, vertexConsumers, light, overlay);
        }
    }
}