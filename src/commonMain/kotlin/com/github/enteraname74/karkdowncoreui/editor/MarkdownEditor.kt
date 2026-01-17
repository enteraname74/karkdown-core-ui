package com.github.enteraname74.karkdowncoreui.editor

import com.github.enteraname74.karkdowncore.FileManager
import com.github.enteraname74.karkdowncoreui.holder.ViewHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MarkdownEditor() {
    @OptIn(ExperimentalUuidApi::class)
    private val _lines: MutableStateFlow<List<ViewHolder>> = MutableStateFlow(
        with(Uuid.random().toString()) {
            listOf(
                ViewHolder(
                    id = this,
                    initialValue = "",
                    onDone = { addLine(this, it) },
                    onDeleteLine = { deleteLine(this, it) }
                )
            )
        }
    )
    val lines = _lines.asStateFlow()

    @OptIn(ExperimentalUuidApi::class)
    constructor(fileManager: FileManager) : this() {
        _lines.value = fileManager.content.mapIndexed { index, line ->
            val id = Uuid.random().toString()
            ViewHolder(
                id = id,
                initialValue = line.rowData,
                onDone = { addLine(id, it) },
                onDeleteLine = { deleteLine(id, it) }
            )
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    constructor(lines: List<String>): this() {
        _lines.value = lines.map { line ->
            val id = Uuid.random().toString()
            ViewHolder(
                id = id,
                initialValue = line,
                onDone = { addLine(id, it) },
                onDeleteLine = { deleteLine(id, it) }
            )
        }
    }

    private fun deleteLine(id: String, textForPreviousLine: String) {
        val currentIndex: Int = _lines.value.indexOfFirst { it.id == id }.takeIf { it > 0 } ?: return
        val previousIndex = currentIndex - 1

        val mutableList = _lines.value.toMutableList()
        val previousElement = mutableList[previousIndex]
        previousElement.setValue(
            value = "${previousElement.getValue()}$textForPreviousLine",
            cursor = previousElement.getValue().length,
        )
        previousElement.shouldFocus = true

        mutableList.removeAt(currentIndex)

        _lines.value = mutableList
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun addLine(id: String, line: String) {
        val currentIndex = _lines.value.indexOfFirst { it.id == id }.takeIf { it != -1 } ?: return
        _lines.value[currentIndex].shouldFocus = false

        val mutableList = _lines.value.toMutableList()

        val newId = Uuid.random().toString()

        mutableList.add(
            index = currentIndex + 1,
            element = ViewHolder(
                initialValue = line,
                id = newId,
                onDone = { addLine(newId, it) },
                onDeleteLine = { deleteLine(newId, it) }
            ).apply {
                shouldFocus = true

            }
        )

        _lines.value = mutableList
    }
}