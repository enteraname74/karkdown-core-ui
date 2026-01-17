package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.enteraname74.karkdowncore.markdownelement.MarkdownElement
import com.github.enteraname74.karkdowncoreui.theme.KarkdownCoreUiTheme
import java.nio.file.Path

@Composable
fun UnorderedListView(
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
            .fillMaxWidth()
            .padding(start = KarkdownCoreUiTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(KarkdownCoreUiTheme.spacing.medium)
    ) {
        Spacer(
            modifier = Modifier
                .size(6.dp)
                .background(
                    color = KarkdownCoreUiTheme.colorScheme.secondary.content,
                    shape = CircleShape
                )
        )

        MarkdownViewBuilder(
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