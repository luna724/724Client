package luna724.iloveichika.lunadayo

import luna724.iloveichika.lunadayo.configmenu.ConfigManager
import luna724.iloveichika.lunadayo.configmenu.ConfigMenuCommand
import luna724.iloveichika.lunadayo.configmenu.ModConfig
import luna724.iloveichika.lunadayo.observer.DragStateObserver
import luna724.iloveichika.lunadayo.utils.DragStateTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.network.FMLNetworkEvent
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

@Mod(modid="simpletogglesprintedit", name="ty 724 <3", version="7.2.4", clientSideOnly = true)
class Main {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        configManager = ConfigManager()

    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {

        ClientCommandHandler.instance.registerCommand(ConfigMenuCommand())
        MinecraftForge.EVENT_BUS.register(configManager)
        MinecraftForge.EVENT_BUS.register(DragStateObserver())
        MinecraftForge.EVENT_BUS.register(DragStateTracker())
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onClientConnected(event: FMLNetworkEvent.ClientConnectedToServerEvent) {
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.START || currentGui == null) return
        mc.displayGuiScreen(currentGui)
        currentGui = null
    }

    companion object {
        var currentGui: GuiScreen? = null

        lateinit var configManager: ConfigManager

        @JvmStatic
        val config: ModConfig
            get() = configManager.config ?: throw IllegalArgumentException("config is null")

        @JvmField
        @NotNull
        val mc: Minecraft = Minecraft.getMinecraft()
    }
}