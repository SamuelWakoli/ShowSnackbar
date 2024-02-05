package com.samwrotethecode.showsnackbar.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }
    var messageError by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            HomeTopBar()
        },
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                value = message,
                onValueChange = { value ->
                    message = value
                    messageError = false
                },
                label = { Text("Message") },
                isError = messageError,
                supportingText = if (messageError) {
                    { Text("Message cannot be empty") }
                } else null,
            )

            Spacer(modifier = Modifier.size(32.dp))

            Button(onClick = {
                if (message.isEmpty()) {
                    messageError = true
                } else {
                    // Show snackbar
                    coroutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message,
                            withDismissAction = true,
                            actionLabel = "Undo"
                        )

                        when (result) {
                            SnackbarResult.Dismissed -> {
                                // do nothing
                                Toast.makeText(context, "Dismissed", Toast.LENGTH_SHORT).show()
                            }

                            SnackbarResult.ActionPerformed -> {
                                // Undo action
                                Toast.makeText(context, "Undo", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }) {
                Text("Show Snackbar")
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}