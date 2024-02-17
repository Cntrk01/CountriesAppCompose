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
import androidx.compose.ui.res.stringResource
import com.mckstudio.countriesapp.common.Constants
import com.mckstuido.countriesapp.R

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
                Text(text = stringResource(R.string.continueString))
            }
        },
        text = {
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Row {
                    Text(text = stringResource(R.string.are_you_sure_you_want_to_finish_the_quiz))
                }
            }
        },
        dismissButton = {
            OutlinedButton(onClick = {
                dismissButton.invoke()
            }) {
                Text(text = stringResource(R.string.finish))
            }
        })
}