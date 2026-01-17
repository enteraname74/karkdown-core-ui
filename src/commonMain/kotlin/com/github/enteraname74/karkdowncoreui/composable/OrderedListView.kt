package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.github.enteraname74.karkdowncore.markdownelement.MarkdownElement
import com.github.enteraname74.karkdowncoreui.theme.KarkdownCoreUiTheme
import java.nio.file.Path

@Composable
fun OrderedListView(
    onDeleteLine: (textForPreviousLine: String) -> Unit,
    onDone: (nextLine: String) -> Unit,
    onChange: (TextFieldValue) -> Unit,
    onClick: () -> Unit,
    textFieldValue: TextFieldValue,
    innerContent: MarkdownElement,
    shouldFocus: Boolean,
    currentIndicator: Int,
    filePath: Path?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = KarkdownCoreUiTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(KarkdownCoreUiTheme.spacing.medium)
    ) {
        Text(
            text = "$currentIndicator. ",
            style = KarkdownCoreUiTheme.typography.body,
            color = KarkdownCoreUiTheme.colorScheme.secondary.content,
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