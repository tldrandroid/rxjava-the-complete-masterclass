package com.tldrandroid.rxjavamasterclass.ui.navigation

object Destinations {
    const val HOME = "home"
    val LESSONS: List<LessonScreen> = listOf()

    //////////////////////////////

    const val START = HOME
}

data class LessonScreen(
    val name: String,
    val route: String
)
