package com.tldrandroid.rxjavamasterclass.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tldrandroid.rxjavamasterclass.ui.navigation.Destinations
import com.tldrandroid.rxjavamasterclass.ui.theme.RxJavaTheCompleteMasterclassTheme

@Composable
fun RxJavaMasterclass() {
    RxJavaTheCompleteMasterclassTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Destinations.START) {
            composable(Destinations.HOME) {
                Home(
                    onButtonTap = { route -> navController.navigate(route) },
                    destinations = Destinations.LESSONS
                )
            }
        }
    }
}
