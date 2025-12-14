package me.rerere.virtualtag.util

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.coloring(colorCode: Char = '&'): String =
    ChatColor.translateAlternateColorCodes(colorCode, with(HexColorUtil) { this@coloring.toHex() })


object HexColorUtil {
    private val PATTERN = Pattern.compile("&(#[A-Fa-f0-9]{6})")

    fun String.toHex(): String {
        var text = this
        var matcher: Matcher = PATTERN.matcher(text)
        while (matcher.find()) {
            val color = text.substring(matcher.start(), matcher.end())
            text = text.replace(color, ChatColor.of(color.substring(1)).toString())
            matcher = PATTERN.matcher(text) // 更新matcher
        }
        return text
    }
}

inline fun allPlayers(handler: (Player) -> Unit) {
    Bukkit.getOnlinePlayers().forEach {
        handler(it)
    }
}