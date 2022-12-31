package com.tldrandroid.rxjavamasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tldrandroid.rxjavamasterclass.ui.RxJavaMasterclass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RxJavaMasterclass()
        }
    }
}
