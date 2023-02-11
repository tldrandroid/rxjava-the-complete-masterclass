package com.tldrandroid.rxjavamasterclass.utils

import java.util.Calendar
import java.util.Random

object RandomGenerator {
    fun nextFloat(): Float {
        val time = Calendar.getInstance().timeInMillis
        val random = Random(time)

        return random.nextFloat()
    }
}
