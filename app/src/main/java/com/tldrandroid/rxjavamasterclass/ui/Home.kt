package com.tldrandroid.rxjavamasterclass.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tldrandroid.rxjavamasterclass.ui.navigation.Destinations
import com.tldrandroid.rxjavamasterclass.ui.navigation.LessonScreen
import com.tldrandroid.rxjavamasterclass.ui.theme.RxJavaTheCompleteMasterclassTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    onButtonTap: (String) -> Unit,
    destinations: List<LessonScreen>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "RxJava Masterclass") }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            LazyColumn {
                items(destinations) { lesson ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Button(
                            onClick = { onButtonTap(lesson.route) },
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(lesson.name)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    RxJavaTheCompleteMasterclassTheme {
        Home(
            onButtonTap = { },
            destinations = Destinations.LESSONS
        )
    }
}
