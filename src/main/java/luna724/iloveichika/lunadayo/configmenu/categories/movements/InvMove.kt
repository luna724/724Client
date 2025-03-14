package luna724.iloveichika.lunadayo.configmenu.categories.movements

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorDropdown
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorSlider
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption

class InvMove {
    @Expose
    @ConfigOption(name = "toggle", desc = "(default = True)")
    @ConfigEditorBoolean
    var enabled = true

    @Expose
    @ConfigOption(name = "Mode", desc = "(default = Legit)")
    @ConfigEditorDropdown(values = ["Vannila", "Legit"])
    var mode: Int = 1

    @Expose
    @ConfigOption(name = "afterMove Delay", desc = "Only works in Legit mode\nSelect as Ticks (default = 2)")
    @ConfigEditorSlider(minValue = 0f, maxValue = 15f, minStep = 1f)
    var afterMoveDelay = 2f

    @Expose
    @ConfigOption(name = "KeepSprint", desc = "KeepSprint while inventory open\n(default = True)")
    @ConfigEditorBoolean
    var keepSprint = true
}