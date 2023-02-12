package com.tldrandroid.rxjavamasterclass.model

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes.CompletableTask
import com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes.MaybeTask
import com.tldrandroid.rxjavamasterclass.utils.completablessinglesmaybes.SingleTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
@SuppressLint("CheckResult") // Reason discussed in a later lesson
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

    }

    private fun completableError() {

    }

    private fun completableSometimesComplete() {

    }

    private fun singleSuccess() {

    }

    private fun singleError() {

    }

    private fun singleSometimesSuccess() {

    }

    private fun maybeSuccess() {

    }

    private fun maybeEmpty() {

    }

    private fun maybeError() {

    }

    private fun maybeSometimesSuccess() {

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
