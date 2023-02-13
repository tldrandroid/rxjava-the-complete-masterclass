package com.tldrandroid.rxjavamasterclass.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tldrandroid.rxjavamasterclass.model.CompletablesSingleMaybesViewModel
import com.tldrandroid.rxjavamasterclass.ui.completablessinglesmaybes.CompletablesSinglesMaybes
import com.tldrandroid.rxjavamasterclass.ui.navigation.Destinations
import com.tldrandroid.rxjavamasterclass.ui.theme.RxJavaTheCompleteMasterclassTheme

@Composable
@ExperimentalMaterial3Api
fun RxJavaMasterclass() {
    RxJavaTheCompleteMasterclassTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "RxJava Masterclass") }
                )
            }
        ) { contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {
                NavHost(navController = navController, startDestination = Destinations.START) {
                    composable(Destinations.HOME) {
                        Home(
                            onButtonTap = { route -> navController.navigate(route) },
                            destinations = Destinations.LESSONS
                        )
                    }

                    composable(Destinations.LESSONS[0].route) {
                        val viewModel: CompletablesSingleMaybesViewModel = hiltViewModel()

                        CompletablesSinglesMaybes(
                            completablesSingleMaybesViewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
