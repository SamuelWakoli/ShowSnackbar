package com.samwrotethecode.showsnackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.samwrotethecode.showsnackbar.home.HomeScreen
import com.samwrotethecode.showsnackbar.ui.theme.ShowSnackbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowSnackbarTheme {
                HomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
