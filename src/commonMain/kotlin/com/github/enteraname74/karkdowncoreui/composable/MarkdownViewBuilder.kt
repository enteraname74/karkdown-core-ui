package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.github.enteraname74.karkdowncore.markdownelement.*
import com.github.enteraname74.karkdowncore.textutils.*
import java.nio.file.Path

/**
 * Used to build the correct markdown view element from a line.
 */
@Composable
fun MarkdownViewBuilder(
    onChange: (TextFieldValue) -> Unit,
    onDeleteLine: (textForPreviousLine: String) -> Unit,
    onDone: (nextLine: String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue,
    markdownElement: MarkdownElement,
    shouldFocus: Boolean,
    filePath: Path?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                modifier
            )
    ) {
        when (markdownElement) {
            is Header, is SimpleText -> TextView(
                textFieldValue = textFieldValue,
                shouldFocus = shouldFocus,
                onChange = onChange,
                onDeleteLine = onDeleteLine,
                onDone = onDone,
            )

            is Blockquote -> BlockquoteView(
                textFieldValue = textFieldValue.copy(text = textFieldValue.text.blockquoteInnerText()),
                innerContent = markdownElement.viewData,
                shouldFocus = shouldFocus,
                filePath = filePath,
                onDeleteLine = {
                    onChange(TextFieldValue(it))
                },
                onDone = {
                    onDone(
                        "> $it"
                    )
                },
                onClick = onClick,
                onChange = {
                    val lineToSave = it.copy(text = it.text.toBlockQuote())
                    onChange(lineToSave)
                },
            )

            is UnorderedList -> UnorderedListView(
                textFieldValue = textFieldValue.copy(text = textFieldValue.text.unorderedListContent()),
                innerContent = markdownElement.viewData,
                shouldFocus = shouldFocus,
                filePath = filePath,
                onDeleteLine = {
                    onChange(TextFieldValue(it))
                },
                onDone = {
                    onDone("${markdownElement.listIndicator} $it")
                },
                onChange = {
                    onChange(
                        it.copy(
                            text = it.text.toUnorderedList(
                                listIndicator = markdownElement.listIndicator
                            )
                        )
                    )
                },
                onClick = onClick,
            )

            is OrderedList -> OrderedListView(
                textFieldValue = textFieldValue.copy(text = textFieldValue.text.orderedListContent()),
                innerContent = markdownElement.viewData,
                shouldFocus = shouldFocus,
                currentIndicator = markdownElement.currentIndicator,
                filePath = filePath,
                onDeleteLine = {
                    onChange(TextFieldValue(it))
                },
                onDone = {
                    onDone("${markdownElement.rowData.orderedListIndicator() + 1}. $it")
                },
                onChange = {
                    onChange(
                        it.copy(
                            text = it.text.toOrderedList(
                                listIndicator = markdownElement.currentIndicator
                            )
                        )
                    )
                },
                onClick = onClick,
            )

            is HorizontalRule -> HorizontalRuleView(
                textFieldValue = textFieldValue,
                shouldFocus = shouldFocus,
                onChange = onChange,
                onDeleteLine = onDeleteLine,
                onDone = onDone,
                onClick = onClick,
            )

            is Image -> ImageView(
                imageName = markdownElement.imageName,
                imagePath = markdownElement.imagePath,
                currentText = markdownElement.rowData,
                shouldFocus = shouldFocus,
                filePath = filePath
            )

            is CheckList -> TODO()
        }
    }
}

