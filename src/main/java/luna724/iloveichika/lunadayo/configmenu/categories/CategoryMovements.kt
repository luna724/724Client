package luna724.iloveichika.lunadayo.configmenu.categories

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.Accordion
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption
import luna724.iloveichika.lunadayo.configmenu.categories.movements.InvMove

class CategoryMovements {
    @Expose
    @ConfigOption(name = "InvMove", desc = "")
    @Accordion
    val invMove = InvMove()
}