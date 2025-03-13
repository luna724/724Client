package luna724.iloveichika.lunadayo.configmenu

import luna724.iloveichika.lunadayo.Main.Companion.configManager
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

class ConfigMenuCommand : CommandBase() {
    override fun getCommandName(): String = "724cfg"
    override fun getCommandAliases(): List<String> = listOf("724config", "l7")
    override fun getCommandUsage(sender: ICommandSender): String = "/724cfg | /724config | /l7"
    override fun getRequiredPermissionLevel(): Int = 0

    override fun processCommand(sender: ICommandSender, args: Array<out String>) {
        configManager.openConfigGui()
    }
}