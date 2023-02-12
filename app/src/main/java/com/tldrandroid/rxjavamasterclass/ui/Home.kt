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

@Composable
@ExperimentalMaterial3Api
fun Home(
    onButtonTap: (String) -> Unit,
    destinations: List<LessonScreen>
) {
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

@Composable
@Preview(showBackground = true)
@ExperimentalMaterial3Api
fun PreviewHome() {
    RxJavaTheCompleteMasterclassTheme {
        Home(
            onButtonTap = { },
            destinations = Destinations.LESSONS
        )
    }
}
