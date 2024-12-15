package io.hvk.koreanculturecenterapp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

fun String.toAnnotatedString(): AnnotatedString {
    val document = Jsoup.parse(this)
    val builder = AnnotatedString.Builder()

    document.body().childNodes().forEach { node ->
        if (node is Element) {
            // Stilleri uygula
            val style = when (node.tagName()) {
                "span" -> parseStyle(node.attr("style"))
                else -> SpanStyle()
            }
            builder.withStyle(style) {
                append(node.text())
            }
        } else {
            // Düz metin
            builder.append(node.outerHtml())
        }
    }
    return builder.toAnnotatedString()
}

fun parseStyle(style: String): SpanStyle {
    val styleMap = style.split(";").associate {
        val parts = it.split(":").map { part -> part.trim() }
        parts[0] to parts.getOrElse(1) { "" }
    }

    return SpanStyle(
        color = styleMap["color"]?.toColor() ?: Color.Unspecified,
    )
}

fun String.toColor(): Color {
    return when (this.lowercase()) {
        "red" -> Color.Red
        "blue" -> Color.Blue
        "green" -> Color.Green
        else -> Color.Unspecified
    }
}
