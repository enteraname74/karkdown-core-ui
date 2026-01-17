package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.enteraname74.karkdowncore.markdownelement.MarkdownElement
import com.github.enteraname74.karkdowncoreui.theme.KarkdownCoreUiTheme
import java.nio.file.Path

@Composable
fun BlockquoteView(
    onChange: (TextFieldValue) -> Unit,
    onDone: (nextLine: String) -> Unit,
    onDeleteLine: (textForPreviousLine: String) -> Unit,
    onClick: () -> Unit,
    textFieldValue: TextFieldValue,
    innerContent: MarkdownElement,
    shouldFocus: Boolean,
    filePath: Path?
) {
    Row(
        modifier = Modifier
            .background(color = KarkdownCoreUiTheme.colorScheme.secondaryContainer.container)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Divider(
            color = KarkdownCoreUiTheme.colorScheme.accent,
            modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
        )

        MarkdownViewBuilder(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = KarkdownCoreUiTheme.spacing.medium,
                    horizontal = KarkdownCoreUiTheme.spacing.medium
                ),
            markdownElement = innerContent,
            shouldFocus = shouldFocus,
            filePath = filePath,
            textFieldValue = textFieldValue,
            onChange = onChange,
            onDeleteLine = onDeleteLine,
            onDone = onDone,
            onClick = onClick,
        )
    }
}