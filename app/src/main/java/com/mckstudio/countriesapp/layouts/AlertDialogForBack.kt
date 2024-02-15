package com.mckstudio.countriesapp.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mckstudio.countriesapp.common.Constants

@Composable
fun AlertDialogForBack(
    confirmButton: () -> Unit,
    dismissButton: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            dismissButton.invoke()
        },
        confirmButton = {
            Button(onClick = {
                confirmButton.invoke()
            }) {
                Text(text = Constants.CONTINUE)
            }
        },
        text = {
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Row {
                    Text(text = "Are you sure you want to finish the quiz ?")
                }
            }
        },
        dismissButton = {
            OutlinedButton(onClick = {
                dismissButton.invoke()
            }) {
                Text(text = Constants.FINISH)
            }
        })
}