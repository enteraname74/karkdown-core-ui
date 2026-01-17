package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.github.enteraname74.karkdowncore.textutils.headerLevel
import com.github.enteraname74.karkdowncore.textutils.isHeader
import com.github.enteraname74.karkdowncoreui.theme.KarkdownCoreUiTheme
import com.github.enteraname74.karkdowncoreui.utils.buildCorrespondingTextStyle
import com.github.enteraname74.karkdowncoreui.visualtransformation.TextFieldMarkdownTransformation
import com.github.enteraname74.karkdowncoreui.visualtransformation.TextFieldViewMarkdownTransformation

/**
 * Text input for modifying file content
 */
@Composable
fun TextView(
    textFieldValue: TextFieldValue,
    onChange: (TextFieldValue) -> Unit,
    onDeleteLine: (textForPreviousLine: String) -> Unit,
    onDone: (nextLine: String) -> Unit,
    shouldFocus: Boolean,
    shouldForceAtBeginningOfLine: Boolean = true,
) {
    val focusRequester = remember { FocusRequester() }

    var isFirstComposition by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        if (isFirstComposition && shouldForceAtBeginningOfLine) {
            onChange(textFieldValue.copy(selection = TextRange(0)))
        }
        isFirstComposition = false
    }

    var backSpaceCountWhenAtBeginningOfLine by rememberSaveable {
        mutableStateOf(if (textFieldValue.text.isEmpty()) 2 else 0)
    }

    LaunchedEffect(shouldFocus) {
        if (shouldFocus) {
            focusRequester.requestFocus()
        } else {
            focusRequester.freeFocus()
        }
    }

    val interactionSource = remember { MutableInteractionSource() }

    var isFocused: Boolean by rememberSaveable {
        mutableStateOf(shouldFocus)
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect {
            if (it is FocusInteraction.Focus) {
                isFocused = true
            } else if (it is FocusInteraction.Unfocus) {
                isFocused = false
            }
        }
    }

    BasicTextField(
        visualTransformation = if (isFocused) {
            TextFieldMarkdownTransformation(
                codeContainerColor = KarkdownCoreUiTheme.colorScheme.secondaryContainer.container,
                linkColor = KarkdownCoreUiTheme.colorScheme.accent,
                imageLinkColor = KarkdownCoreUiTheme.colorScheme.accent,
            )
        } else {
            TextFieldViewMarkdownTransformation(
                codeContainerColor = KarkdownCoreUiTheme.colorScheme.secondaryContainer.container,
                linkColor = KarkdownCoreUiTheme.colorScheme.accent,
                imageLinkColor = KarkdownCoreUiTheme.colorScheme.accent,
            )
        },
        interactionSource = interactionSource,
        cursorBrush = SolidColor(value = KarkdownCoreUiTheme.colorScheme.primary.content),
        textStyle = buildCorrespondingTextStyle(line = textFieldValue.text),
        value = textFieldValue,
        singleLine = true,
        onValueChange = {
            if (it.text.isNotBlank()) {
                backSpaceCountWhenAtBeginningOfLine = 0
            }
            onChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = if (textFieldValue.text.isHeader()) {
                    KarkdownCoreUiTheme.spacing.textPadding(textFieldValue.text.headerLevel())
                } else {
                    KarkdownCoreUiTheme.spacing.body
                }
            )
            .focusRequester(focusRequester)
            .onKeyEvent { event ->
                when (event.key) {
                    Key.Enter -> {
                        val splittingPos: Int = textFieldValue.selection.start.takeIf {
                            textFieldValue.selection.collapsed
                        } ?: return@onKeyEvent false


                        val textToPutOnNextLine = textFieldValue.text.substring(splittingPos)
                        val textToKeep = textFieldValue.text.substring(0, splittingPos)

                        onDone(textToPutOnNextLine)
                        onChange(TextFieldValue(textToKeep))
                    }

                    Key.Backspace -> {
                        // We handle this case only when at the beginning of the text field
                        if (textFieldValue.selection.start != 0) return@onKeyEvent false

                        val atBeginWithText = textFieldValue.selection.collapsed
                                && textFieldValue.selection.start == 0
                                && textFieldValue.text.isNotBlank()

                        if (backSpaceCountWhenAtBeginningOfLine < 1 && !atBeginWithText) {
                            backSpaceCountWhenAtBeginningOfLine++
                            return@onKeyEvent false
                        }
                        onDeleteLine(textFieldValue.text)
                    }
                }
                false
            }
    )
}