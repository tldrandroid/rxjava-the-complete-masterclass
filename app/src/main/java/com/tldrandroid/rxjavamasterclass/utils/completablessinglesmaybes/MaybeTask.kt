package com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes

import android.os.Handler
import android.os.Looper
import com.tldrandroid.rxjavamasterclass.utils.GenericThrowable
import com.tldrandroid.rxjavamasterclass.utils.RandomGenerator
import com.tldrandroid.rxjavamasterclass.utils.TaskConfig
import java.util.concurrent.Executors
import javax.inject.Inject

class MaybeTask @Inject constructor() {
    private val handler = Handler(Looper.getMainLooper())

    fun doTaskAlwaysSuccess(onSuccess: (String) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            Thread.sleep(RandomGenerator.sleepTime())

            handler.post {
                onSuccess("I definitely finished :)")
            }
        }
    }

    fun doTaskAlwaysSuccessWithNull(onSuccess: (String?) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            Thread.sleep(RandomGenerator.sleepTime())

            handler.post {
                onSuccess(null)
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

    fun doTaskSometimesSuccess(onSuccess: (String?) -> Unit, onError: (Throwable) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()
        val random = RandomGenerator.nextFloat()

        executor.execute {
            Thread.sleep(RandomGenerator.sleepTime())

            handler.post {
                if (random > TaskConfig.SUCCESS_RATIO) {
                    onError(GenericThrowable)
                } else {
                    val randomAgain = RandomGenerator.nextFloat()

                    if (randomAgain > TaskConfig.SUCCESS_RATIO) {
                        onSuccess(null)
                    } else {
                        onSuccess("I finished this time. :)")
                    }
                }
            }
        }
    }
}
