Red screen
---

Just show entire red screen without any toolbars, with maximum brightness. Useful for signaling some emergency.

Features:

* Hiding everything else (immersive mode)
* Setting brightness to maximum
* Showing the application atop of lock screen (when activated from some gesture, etc.)
* Overridable colour (as intent parameter)

You can trigger it from console with `am start -n org.vi_server.red_screen/org.vi_server.red_screen.RedScreenActivity` command. For overriding colour use parameter like `-e colour yellow`.

Exit with "Back" button, not with "Home" (due to brightness wakelock).

See Github releases for pre-built `redscreen.apk` signed with a debugging key.

Implemented by Vitaly "_Vi" Shukela in 2015. License: MIT.
