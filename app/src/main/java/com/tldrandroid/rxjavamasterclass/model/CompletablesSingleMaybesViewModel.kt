package com.tldrandroid.rxjavamasterclass.model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes.CompletableTask
import com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes.MaybeTask
import com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes.SingleTask
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
@SuppressLint("CheckResult") // Discussed in a later lesson
class CompletablesSingleMaybesViewModel @Inject constructor(
    private val completableTask: CompletableTask,
    private val maybeTask: MaybeTask,
    private val singleTask: SingleTask,
    private val viewModelState: MutableStateFlow<CompletablesSingleMaybesUiState>
) : ViewModel() {
    // region Initialization

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CompletablesSingleMaybesUiState()
        )

    init {
        completableComplete()
        completableError()
        completableSometimesComplete()

        singleSuccess()
        singleError()
        singleSometimesSuccess()

        maybeSuccess()
        maybeEmpty()
        maybeError()
        maybeSometimesSuccess()
    }

    // endregion

    // region Lesson Code

    private fun completableComplete() {
//        Completable
//            .complete()
//            .subscribe {
//                viewModelState.markCompletableComplete()
//            }

        Completable
            .create { emitter ->
                completableTask.doTaskAlwaysComplete {
                    emitter.onComplete()
                }
            }
            .subscribe {
                viewModelState.markCompletableComplete()
            }
    }

    private fun completableError() {
//        Completable
//            .error(GenericThrowable)
//            .subscribe({
//            }, { error ->
//                viewModelState.markCompletableError(error)
//            })

        Completable
            .create { emitter ->
                completableTask.doTaskAlwaysError { error ->
                    emitter.onError(error)
                }
            }
            .subscribe({
                // Nothing to do
            }, { error ->
                viewModelState.markCompletableError(error)
            })
    }

    private fun completableSometimesComplete() {
        Completable
            .create { emitter ->
                completableTask.doTaskSometimesComplete(
                    onComplete = { emitter.onComplete() },
                    onError = { error -> emitter.onError(error) }
                )
            }
            .subscribe({
                viewModelState.markCompletableSometimesComplete("Completed this time!")
            }, {
                viewModelState.markCompletableSometimesComplete("Did not complete this time :(")
            })
    }

    private fun singleSuccess() {
//        Single
//            .just("I finished :)")
//            .subscribe { outcome ->
//                viewModelState.markSingleSuccess(outcome)
//            }

        Single
            .create { emitter ->
                singleTask.doTaskAlwaysSuccess { outcome ->
                    emitter.onSuccess(outcome)
                }
            }
            .subscribe({ outcome ->
                viewModelState.markSingleSuccess(outcome)
            }, {

            })
    }

    private fun singleError() {
//        Single
//            .error<String>(GenericThrowable)
//            .subscribe({
//                // Nothing to do
//            }, { error ->
//                viewModelState.markSingleError(error)
//            })

        Single
            .create<String> { emitter ->
                singleTask.doTaskAlwaysError { outcome ->
                    emitter.onError(outcome)
                }
            }
            .subscribe({
                // Nothing to do
            }, { error ->
                viewModelState.markSingleError(error)
            })
    }

    private fun singleSometimesSuccess() {
        Single
            .create { emitter ->
                singleTask.doTaskSometimesSuccess(
                    onSuccess = { outcome -> emitter.onSuccess(outcome) },
                    onError = { error -> emitter.onError(error) }
                )
            }
            .subscribe({ outcome ->
                viewModelState.markSingleSometimesSuccess("Completed this time with: $outcome!")
            }, {
                viewModelState.markSingleSometimesSuccess("Did not complete this time :(")
            })
    }

    private fun maybeSuccess() {
//        Maybe
//            .just("I definitely finished :)")
//            .subscribe { outcome ->
//                viewModelState.markMaybeSuccess(outcome)
//            }

        Maybe
            .create { emitter ->
                maybeTask.doTaskAlwaysSuccess { outcome ->
                    emitter.onSuccess(outcome)
                }
            }
            .subscribe({ outcome ->
                viewModelState.markMaybeSuccess(outcome)
            }, {

            })
    }

    private fun maybeEmpty() {
//        Maybe
//            .empty<String>()
//            .subscribe({
//                // Nothing to do
//            }, {
//                // Nothing to do
//            }, {
//                viewModelState.markMaybeEmpty()
//            })

        Maybe
            .create { emitter ->
                maybeTask.doTaskAlwaysSuccessWithNull { outcome ->
                    if (outcome == null) {
                        emitter.onComplete()
                    } else {
                        emitter.onSuccess(outcome) // Never happens, for demonstration only
                    }
                }
            }
            .subscribe({
                // Nothing to do
            }, {
                // Nothing to do
            }, {
                viewModelState.markMaybeEmpty()
            })
    }

    private fun maybeError() {
//        Maybe
//            .error<String>(GenericThrowable)
//            .subscribe({
//                // Nothing to do
//            }, { error ->
//                viewModelState.markMaybeError(error)
//            })

        Maybe
            .create<String> { emitter ->
                maybeTask.doTaskAlwaysError { outcome ->
                    emitter.onError(outcome)
                }
            }
            .subscribe({
                // Nothing to do
            }, { error ->
                viewModelState.markMaybeError(error)
            })
    }

    private fun maybeSometimesSuccess() {
        Maybe
            .create { emitter ->
                maybeTask.doTaskSometimesSuccess(
                    onSuccess = { outcome ->
                        if (outcome == null) {
                            emitter.onComplete()
                        } else {
                            emitter.onSuccess(outcome)
                        }
                    },
                    onError = { error -> emitter.onError(error) }
                )
            }
            .subscribe({ outcome ->
                viewModelState.markMaybeSometimesSuccess("Completed this time with: $outcome")
            }, {
                viewModelState.markMaybeSometimesSuccess("Did not complete this time :(")
            }, {
                viewModelState.markMaybeSometimesSuccess("Completed this time without data!")
            })
    }

    // endregion

    // region View Model Updaters

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markCompletableComplete() =
        update {
            it.copy(
                completableCompleteOutcome = "Completed!"
            )
        }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markCompletableError(
        throwable: Throwable
    ) = update {
        it.copy(
            completableErrorOutcome = throwable.message ?: "Error!"
        )
    }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markCompletableSometimesComplete(
        outcomeMessage: String
    ) = update {
        it.copy(
            completableSometimesCompleteOutcome = outcomeMessage
        )
    }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markSingleSuccess(outcome: String) =
        update {
            it.copy(
                singleSuccessOutcome = outcome
            )
        }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markSingleError(
        throwable: Throwable
    ) = update {
        it.copy(
            singleErrorOutcome = throwable.message ?: "Error!"
        )
    }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markSingleSometimesSuccess(
        outcomeMessage: String
    ) = update {
        it.copy(
            singleSometimesSuccessOutcome = outcomeMessage
        )
    }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markMaybeSuccess(outcome: String) =
        update {
            it.copy(
                maybeSuccessOutcome = outcome
            )
        }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markMaybeEmpty() =
        update {
            it.copy(
                maybeEmptyOutcome = "As expected, it's empty."
            )
        }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markMaybeError(
        throwable: Throwable
    ) = update {
        it.copy(
            maybeErrorOutcome = throwable.message ?: "Error!"
        )
    }

    private fun MutableStateFlow<CompletablesSingleMaybesUiState>.markMaybeSometimesSuccess(
        outcomeMessage: String
    ) = update {
        it.copy(
            maybeSometimesSuccessOutcome = outcomeMessage
        )
    }

    // endregion
}

data class CompletablesSingleMaybesUiState(
    val completableCompleteOutcome: String = "",
    val completableErrorOutcome: String = "",
    val completableSometimesCompleteOutcome: String = "",

    val singleSuccessOutcome: String = "",
    val singleErrorOutcome: String = "",
    val singleSometimesSuccessOutcome: String = "",

    val maybeSuccessOutcome: String = "",
    val maybeEmptyOutcome: String = "",
    val maybeErrorOutcome: String = "",
    val maybeSometimesSuccessOutcome: String = ""
)
