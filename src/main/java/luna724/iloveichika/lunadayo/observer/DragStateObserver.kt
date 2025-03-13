package luna724.iloveichika.lunadayo.observer

import luna724.iloveichika.lunadayo.Main.Companion.mc
import luna724.iloveichika.lunadayo.utils.DragStateTracker
import net.minecraft.client.entity.EntityPlayerSP
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

class DragStateObserver {
    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        // player.inventory.itemStack が存在する場合、アイテムをドラッグ中と判断する
        val player: EntityPlayerSP = mc.thePlayer ?: return
        val itemStack: ItemStack = player.inventory?.itemStack ?: return

        DragStateTracker.setDragging(true)
    }
}