package com.github.enteraname74.karkdowncoreui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.github.enteraname74.karkdowncore.textutils.isURL
import com.github.enteraname74.karkdowncoreui.theme.KarkdownCoreUiTheme
import java.io.File
import java.net.URL
import java.nio.file.Path
import kotlin.io.path.Path

@Composable
fun ImageView(
    filePath: Path?,
    currentText: String,
    imagePath: String,
    imageName: String,
    shouldFocus: Boolean,
) {
    // TODO: Improve whole image view system
//    var imageLoadingState by remember {
//        mutableStateOf<LoadingState>(ImageLoading())
//    }
//
//    val density = LocalDensity.current
//
//    LaunchedEffect(key1 = imagePath) {
//        CoroutineScope(Dispatchers.IO).launch {
//            imageLoadingState = ImageLoading(message = appStrings.loadingImageAtPath(imagePath = imagePath))
//
//
//            if (imagePath.isSvgPath()) {
//                val result = getSVGDataFromGenericPath(
//                    imagePath = imagePath,
//                    filePath = filePath,
//                    density = density
//                )
//                imageLoadingState =
//                    if (result == null) ErrorImageLoading(message = appStrings.couldNotLoadImageAtPath(imagePath = imagePath))
//                    else SuccessSVGImageLoading(data = result)
//            } else {
//                val result = getImageDataFromGenericPath(
//                    imagePath = imagePath,
//                    filePath = filePath
//                )
//                imageLoadingState =
//                    if (result == null) ErrorImageLoading(message = appStrings.couldNotLoadImageAtPath(imagePath = imagePath))
//                    else SuccessImageLoading(data = result)
//            }
//        }
//    }
//
//    if (userPosition != markdownElementPosition) {
//        when (imageLoadingState) {
//            is ErrorImageLoading -> ErrorView(
//                message = (imageLoadingState as ErrorImageLoading).message,
//                onClick = onClick
//            )
//
//            is ImageLoading -> LoadingView(
//                message = (imageLoadingState as ImageLoading).message,
//                onClick = onClick
//            )
//
//            is SuccessSVGImageLoading -> LoadedImageView(
//                painter = (imageLoadingState as SuccessSVGImageLoading).data,
//                imageName = imageName,
//                onClick = onClick
//            )
//
//            is SuccessImageLoading -> LoadedImageView(
//                bitmap = (imageLoadingState as SuccessImageLoading).data,
//                imageName = imageName,
//                onClick = onClick
//            )
//        }
//    } else {
//        TextView(
//            text = currentText,
//            viewText = currentText,
//            shouldFocus = true,
//            uiElementSpec = uiElementSpec,
//        )
//    }
}

/**
 * Check if a string is a svg path
 */
private fun String.isSvgPath(): Boolean {
    val regex = Regex(""".+.svg""")
    return regex.matches(this)
}

@Composable
private fun LoadedImageView(
    bitmap: ImageBitmap,
    imageName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Image(
            bitmap = bitmap,
            contentDescription = null
        )
        Text(
            text = imageName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = KarkdownCoreUiTheme.typography.small
        )
    }
}

@Composable
fun LoadedImageView(
    painter: Painter,
    imageName: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null
        )
        Text(
            text = imageName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = KarkdownCoreUiTheme.typography.small
        )
    }
}

@Composable
fun LoadingView(
    message: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        CircularProgressIndicator(
            color = KarkdownCoreUiTheme.colorScheme.secondary.content
        )
        Text(
            text = message,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = KarkdownCoreUiTheme.typography.small
        )
    }
}

@Composable
fun ErrorView(
    message: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Image(
            modifier = Modifier
                .size(size = KarkdownCoreUiTheme.imageSize.medium),
            imageVector = Icons.Rounded.Error,
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = KarkdownCoreUiTheme.colorScheme.secondary.content)
        )
        Text(
            text = message,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = KarkdownCoreUiTheme.typography.small
        )
    }
}

/**
 * Retrieve an image data depending on the type of the given image path.
 * Returns null if the data couldn't be retrieved.
 */
private fun getImageDataFromGenericPath(
    imagePath: String,
    filePath: Path?
): ImageBitmap? {
    return if (imagePath.isURL()) imageFromUrl(url = imagePath)
    else {
        val absolutePathResult = imageFromFile(filePath = imagePath)
        if (absolutePathResult == null && filePath != null) {
            val relativePath = Path(filePath.parent.toString(), imagePath)
            imageFromFile(filePath = relativePath.toString())
        } else {
            absolutePathResult
        }
    }
}

/**
 * Retrieve an SVG data depending on the type of the given image path.
 * Returns null if the data couldn't be retrieved.
 */
private fun getSVGDataFromGenericPath(
    imagePath: String,
    filePath: Path?,
    density: Density
): Painter? {
    return if (imagePath.isURL()) svgFromUrl(url = imagePath, density = density)
    else {
        val absolutePathResult = svgFromFile(filePath = imagePath, density = density)
        if (absolutePathResult == null && filePath != null) {
            val relativePath = Path(filePath.parent.toString(), imagePath)
            svgFromFile(filePath = relativePath.toString(), density = density)
        } else {
            absolutePathResult
        }
    }
}

/**
 * Fetch image data from its file path, return null if the data couldn't be retrieved.
 */
private fun imageFromFile(filePath: String): ImageBitmap? {
    return try {
        val file = File(filePath)
        file.inputStream().buffered().use { loadImageBitmap(it) }
    } catch (_: Exception) {
        null
    }
}

/**
 * Fetch image data from a URL, return null if the data couldn't be retrieved.
 */
private fun imageFromUrl(url: String): ImageBitmap? {
    return try {
        URL(url).openStream().buffered().use { loadImageBitmap(it) }
    } catch (e: Exception) {
        null
    }
}

/**
 * Fetch SVG data from its file path, return null if the data couldn't be retrieved.
 */
private fun svgFromFile(filePath: String, density: Density): Painter? {
    return try {
        val file = File(filePath)
        file.inputStream().buffered().use { loadSvgPainter(it, density) }
    } catch (_: Exception) {
        null
    }
}

/**
 * Fetch SVG data from a URL, return null if the data couldn't be retrieved.
 */
private fun svgFromUrl(url: String, density: Density): Painter? {
    return try {
        URL(url).openStream().buffered().use { loadSvgPainter(it, density) }
    } catch (e: Exception) {
        null
    }
}