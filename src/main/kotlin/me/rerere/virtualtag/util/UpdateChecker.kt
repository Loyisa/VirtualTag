package me.rerere.virtualtag.util

import me.rerere.virtualtag.virtualTag
import java.net.HttpURLConnection
import java.net.URL

object UpdateChecker {
    fun getLatestVersion(): String = try {
        val url = URL("https://cdn.jsdelivr.net/gh/re-ovo/VirtualTag/build.gradle.kts")
        val connection = url.openConnection() as HttpURLConnection
        val inputStream = connection.inputStream
        val responseBody = inputStream.bufferedReader().use { it.readText() }
        responseBody.substring(
            responseBody.indexOf("version = \"") + "version = \"".length,
            responseBody.indexOf('"', responseBody.indexOf("version = \"") + "version = \"".length)
        ).trim()
    } catch (e: Exception) {
        e.printStackTrace()
        virtualTag().description.version
    }
}