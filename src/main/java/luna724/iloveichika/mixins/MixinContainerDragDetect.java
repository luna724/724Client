package luna724.iloveichika.mixins;

import luna724.iloveichika.lunadayo.utils.DragStateTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Container.class)
public class MixinContainerDragDetect {
    @Inject(method = "slotClick", at = @At("HEAD"))
    private void onSlotClickHead(int slotId, int mouseButton, int mode, EntityPlayer player,
                                 CallbackInfoReturnable<ItemStack> cir) {
        if (!DragStateTracker.isSafetyEnable()) { return; }

        // 何かしらの入力イベントが起こった場合は即座に入力を停止
        DragStateTracker.setDraggingWithCooldown(true);

        Minecraft mc = Minecraft.getMinecraft();
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindLeft.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
    }
}