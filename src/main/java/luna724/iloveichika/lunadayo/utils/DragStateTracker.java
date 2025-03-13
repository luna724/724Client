package luna724.iloveichika.lunadayo.utils;

import luna724.iloveichika.lunadayo.Main;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static luna724.iloveichika.lunadayo.Main.getConfig;

public class DragStateTracker {
    private static boolean isDragging = false;
    private static int dragRemainTicks = 0;

    public static boolean isDragging() {
        return isDragging;
    }

    public static void setDragging(boolean dragging) {
        isDragging = dragging;
    }

    // setTicks のデフォルト値を指定
    public static void setDraggingWithCooldown(boolean dragging) {
        setDragging(dragging);
        dragRemainTicks = (int) Math.round(getConfig().getMovements().getInvMove().getAfterMoveDelay());
    }

    // InvMove のモードを考慮したうえで返す
    public static boolean isMovable() {
        // InvMove がオフなら常に False
        // Mode が 1 (Legit) かつ、isDragging が True なら False
        // Mode が 0 (Vannila) なら True
        return getConfig().getMovements().getInvMove().getEnabled() && (
                (getConfig().getMovements().getInvMove().getMode() == 1 && !isDragging) || getConfig().getMovements().getInvMove().getMode() == 0);
    }

    public static boolean isSafetyEnable() {
        // InvMove がオン + Mode が 1 なら True
        // それ以外なら False
        return getConfig().getMovements().getInvMove().getEnabled() && getConfig().getMovements().getInvMove().getMode() == 1;
    }


    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (dragRemainTicks > 0) {
            isDragging = true;
            dragRemainTicks--;
        }
        else {
            isDragging = false;
        }
    }
}