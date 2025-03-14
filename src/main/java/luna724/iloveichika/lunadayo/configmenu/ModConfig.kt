package luna724.iloveichika.lunadayo.configmenu

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.Config
import io.github.notenoughupdates.moulconfig.annotations.Category
import luna724.iloveichika.lunadayo.Main.Companion.configManager
import luna724.iloveichika.lunadayo.configmenu.categories.CategoryMovements
import luna724.iloveichika.lunadayo.configmenu.categories.CategoryRenderer

class ModConfig : Config() {
    override fun getTitle(): String {
        return "§d724Client §rby §dluna724§r, config menu by §channibal2§r, §5Moulberry §rand §5nea89"
    }

    override fun saveNow() {
        configManager.save()
    }

    @Expose
    @Category(name = "Movements", desc = "")
    val movements = CategoryMovements()

    @Expose
    @Category(name = "Render", desc = "")
    val render = CategoryRenderer()
}