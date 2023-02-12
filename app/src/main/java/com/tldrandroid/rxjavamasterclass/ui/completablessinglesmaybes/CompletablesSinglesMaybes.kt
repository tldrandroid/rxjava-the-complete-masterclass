package com.tldrandroid.rxjavamasterclass.ui.completablessinglesmaybes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tldrandroid.rxjavamasterclass.model.CompletablesSingleMaybesUiState
import com.tldrandroid.rxjavamasterclass.model.CompletablesSingleMaybesViewModel
import com.tldrandroid.rxjavamasterclass.ui.theme.RxJavaTheCompleteMasterclassTheme

@Composable
@ExperimentalMaterial3Api
fun CompletablesSinglesMaybes(
    completablesSingleMaybesViewModel: CompletablesSingleMaybesViewModel
) {
    val uiState by completablesSingleMaybesViewModel.uiState.collectAsState()

    CompletablesSinglesMaybes(
        completablesSingleMaybesUiState = uiState
    )
}

@Composable
@ExperimentalMaterial3Api
private fun CompletablesSinglesMaybes(
    completablesSingleMaybesUiState: CompletablesSingleMaybesUiState
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(state = scrollState, enabled = true)
    ) {
        Text(
            text = "Completables",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.completableCompleteOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Complete") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.completableErrorOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Error") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.completableSometimesCompleteOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Sometimes complete (50% success rate)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        //////////////////////////////////////////

        Text(
            text = "Singles",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.singleSuccessOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Success") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.singleErrorOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Error") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.singleSometimesSuccessOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Sometimes success (50% success rate)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        //////////////////////////////////////////

        Text(
            text = "Maybes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.maybeSuccessOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Success") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.maybeEmptyOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Empty") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.maybeErrorOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Error") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = completablesSingleMaybesUiState.maybeSometimesSuccessOutcome,
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Sometimes success (25% success rate, 25% empty rate)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
@ExperimentalMaterial3Api
fun PreviewCompletablesSinglesMaybes() {
    RxJavaTheCompleteMasterclassTheme {
        CompletablesSinglesMaybes(
            completablesSingleMaybesUiState = CompletablesSingleMaybesUiState()
        )
    }
}
