package com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes

import android.os.Handler
import android.os.Looper
import com.tldrandroid.rxjavamasterclass.utils.GenericThrowable
import com.tldrandroid.rxjavamasterclass.utils.RandomGenerator
import com.tldrandroid.rxjavamasterclass.utils.TaskConfig
import java.util.concurrent.Executors

class CompletableTask {
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    fun doTaskAlwaysComplete(onComplete: () -> Unit) {
        handler.post {
            onComplete()
        }
    }

    fun doTaskAlwaysError(onError: (Throwable) -> Unit) {
        handler.post {
            onError(GenericThrowable)
        }
    }

    fun doTaskSometimesComplete(onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        val random = RandomGenerator.nextFloat()

        handler.post {
            if (random > TaskConfig.COMPLETABLE_SUCCESS_RATIO) {
                onError(GenericThrowable)
            } else {
                onComplete()
            }
        }
    }
}
