package com.github.enteraname74.karkdowncoreui.holder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.github.enteraname74.karkdowncoreui.composable.MarkdownViewBuilder
import com.github.enteraname74.karkdowncoreui.utils.MarkdownUtils

data class ViewHolder(
    val id: String,
    val initialValue: String,
    val onDone: (nextLine: String) -> Unit,
    val onDeleteLine: (textForPreviousLine: String) -> Unit,
) {

    private var rowText: TextFieldValue by mutableStateOf(
        TextFieldValue(initialValue)
    )

    var shouldFocus: Boolean by mutableStateOf(
        false
    )

    fun getValue(): String =
        rowText.text

    fun setValue(value: String, cursor: Int) {7
        rowText = TextFieldValue(
            text = value,
            selection = TextRange(cursor, cursor)
        )
    }

    @Composable
    fun View(modifier: Modifier) {
        MarkdownViewBuilder(
            modifier = modifier,
            onChange = {
                rowText = it
            },
            textFieldValue = rowText,
            markdownElement = MarkdownUtils.buildMarkdownElement(rowText.text),
            shouldFocus = shouldFocus,
            filePath = null,
            onDeleteLine = onDeleteLine,
            onDone = onDone,
            onClick = {
                shouldFocus = true
            }
        )
    }
}