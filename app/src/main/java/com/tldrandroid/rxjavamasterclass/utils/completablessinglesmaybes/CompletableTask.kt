package com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes

import android.os.Handler
import android.os.Looper
import com.tldrandroid.rxjavamasterclass.utils.GenericThrowable
import com.tldrandroid.rxjavamasterclass.utils.RandomGenerator
import com.tldrandroid.rxjavamasterclass.utils.TaskConfig
import java.util.concurrent.Executors
import javax.inject.Inject

class CompletableTask @Inject constructor() {
    private val handler = Handler(Looper.getMainLooper())

    fun doTaskAlwaysComplete(onComplete: () -> Unit) {
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            Thread.sleep(RandomGenerator.sleepTime())

            handler.post {
                onComplete()
            }
        }
    }

    fun doTaskAlwaysError(onError: (Throwable) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            Thread.sleep(RandomGenerator.sleepTime())

            handler.post {
                onError(GenericThrowable)
            }
        }
    }

    fun doTaskSometimesComplete(onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()
        val random = RandomGenerator.nextFloat()

        executor.execute {
            Thread.sleep(RandomGenerator.sleepTime())

            handler.post {
                if (random > TaskConfig.SUCCESS_RATIO) {
                    onError(GenericThrowable)
                } else {
                    onComplete()
                }
            }
        }
    }
}
