package com.daniebeler.dailytasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.daniebeler.dailytasks.ui.composables.MyMainScreen
import com.daniebeler.dailytasks.ui.theme.DailyTasksTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DailyTasksTheme {
                MyMainScreen()
            }
        }
    }
}