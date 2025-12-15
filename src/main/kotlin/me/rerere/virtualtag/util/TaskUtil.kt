package me.rerere.virtualtag.util

import io.github.retrooper.packetevents.util.folia.FoliaScheduler
import me.rerere.virtualtag.virtualTag

object TaskUtil {
    fun timerTask(delay: Long = 0, interval: Long = 1, task: () -> Unit) =
        FoliaScheduler.getGlobalRegionScheduler().runAtFixedRate(virtualTag(), { _ -> task() }, delay, interval)

    fun asyncTimerTask(delay: Long = 0, interval: Long = 1, task: () -> Unit) =
        FoliaScheduler.getAsyncScheduler().runAtFixedRate(virtualTag(), { _ -> task() }, delay, interval)

    fun asyncTask(task: () -> Unit) {
        FoliaScheduler.getAsyncScheduler().runNow(virtualTag()) { _ -> task() }
    }

    fun cancelTasks() {
        FoliaScheduler.getAsyncScheduler().cancel(virtualTag())
        FoliaScheduler.getGlobalRegionScheduler().cancel(virtualTag())
    }
}
