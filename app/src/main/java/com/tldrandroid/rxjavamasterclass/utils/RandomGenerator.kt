package com.tldrandroid.rxjavamasterclass.utils

import java.util.Random

object RandomGenerator {
    fun nextFloat() = Random().nextFloat()

    fun sleepTime(): Long {
        val random = nextFloat()
        val timeSeparation =
            TaskConfig.BACKGROUND_SLEEP_DURATION_MAX_IN_MS - TaskConfig.BACKGROUND_SLEEP_DURATION_MIN_IN_MS
        val portionOfSeparation = timeSeparation * random

        return TaskConfig.BACKGROUND_SLEEP_DURATION_MIN_IN_MS + portionOfSeparation.toLong()
    }
}
