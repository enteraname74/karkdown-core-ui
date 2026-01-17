package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.github.enteraname74.karkdowncoreui.theme.KarkdownCoreUiTheme

@Composable
fun HorizontalRuleView(
    textFieldValue: TextFieldValue,
    onChange: (TextFieldValue) -> Unit,
    onDeleteLine: (textForPreviousLine: String) -> Unit,
    onDone: (nextLine: String) -> Unit,
    onClick: () -> Unit,
    shouldFocus: Boolean,
) {
    // If we are not on the line, we should show the styled view of the horizontal rule :
    if (!shouldFocus) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(KarkdownCoreUiTheme.spacing.veryLarge)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = KarkdownCoreUiTheme.colorScheme.secondary.content,
            )
        }
    } else {
        TextView(
            textFieldValue = textFieldValue,
            shouldFocus = true,
            onChange = onChange,
            onDeleteLine = onDeleteLine,
            onDone = onDone,
            shouldForceAtBeginningOfLine = false,
        )
    }
}