package com.github.enteraname74.karkdowncoreui.utils

import com.github.enteraname74.karkdowncore.markdownelement.Blockquote
import com.github.enteraname74.karkdowncore.markdownelement.Header
import com.github.enteraname74.karkdowncore.markdownelement.HorizontalRule
import com.github.enteraname74.karkdowncore.markdownelement.Image
import com.github.enteraname74.karkdowncore.markdownelement.MarkdownElement
import com.github.enteraname74.karkdowncore.markdownelement.OrderedList
import com.github.enteraname74.karkdowncore.markdownelement.SimpleText
import com.github.enteraname74.karkdowncore.markdownelement.UnorderedList
import com.github.enteraname74.karkdowncore.textutils.blockquoteContent
import com.github.enteraname74.karkdowncore.textutils.isBlockquote
import com.github.enteraname74.karkdowncore.textutils.isHeader
import com.github.enteraname74.karkdowncore.textutils.isHorizontalRule
import com.github.enteraname74.karkdowncore.textutils.isImage
import com.github.enteraname74.karkdowncore.textutils.isOrderedList
import com.github.enteraname74.karkdowncore.textutils.isUnorderedList
import com.github.enteraname74.karkdowncore.textutils.orderedListContent
import com.github.enteraname74.karkdowncore.textutils.unorderedListContent

// TODO: Move to karkdown-core
object MarkdownUtils {
    fun buildMarkdownElement(string: String): MarkdownElement {
        return if (string.isHeader()) Header(rowData = string)
        else if (string.isUnorderedList()) UnorderedList(
            rowData = string,
            innerData = buildMarkdownElement(string.unorderedListContent())
        )
        else if (string.isOrderedList()) OrderedList(
            rowData = string,
            innerData = buildMarkdownElement(string.orderedListContent())
        )
        else if (string.isBlockquote()) Blockquote(
            rowData = string,
            innerData = buildMarkdownElement(string.blockquoteContent())
        )
        else if (string.isHorizontalRule()) HorizontalRule(
            rowData = string
        )
        else if (string.isImage()) Image(
            rowData = string
        )
        else SimpleText(rowData = string)
    }
}