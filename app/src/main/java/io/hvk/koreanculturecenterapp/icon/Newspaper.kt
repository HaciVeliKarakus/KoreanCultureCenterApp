package io.hvk.koreanculturecenterapp.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Newspaper: ImageVector
	get() {
		if (_Newspaper != null) {
			return _Newspaper!!
		}
		_Newspaper = ImageVector.Builder(
            name = "Newspaper",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
			path(
    			fill = SolidColor(Color.Black),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.NonZero
			) {
				moveTo(160f, 840f)
				quadToRelative(-33f, 0f, -56.5f, -23.5f)
				reflectiveQuadTo(80f, 760f)
				verticalLineToRelative(-640f)
				lineToRelative(67f, 67f)
				lineToRelative(66f, -67f)
				lineToRelative(67f, 67f)
				lineToRelative(67f, -67f)
				lineToRelative(66f, 67f)
				lineToRelative(67f, -67f)
				lineToRelative(67f, 67f)
				lineToRelative(66f, -67f)
				lineToRelative(67f, 67f)
				lineToRelative(67f, -67f)
				lineToRelative(66f, 67f)
				lineToRelative(67f, -67f)
				verticalLineToRelative(640f)
				quadToRelative(0f, 33f, -23.5f, 56.5f)
				reflectiveQuadTo(800f, 840f)
				close()
				moveToRelative(0f, -80f)
				horizontalLineToRelative(280f)
				verticalLineToRelative(-240f)
				horizontalLineTo(160f)
				close()
				moveToRelative(360f, 0f)
				horizontalLineToRelative(280f)
				verticalLineToRelative(-80f)
				horizontalLineTo(520f)
				close()
				moveToRelative(0f, -160f)
				horizontalLineToRelative(280f)
				verticalLineToRelative(-80f)
				horizontalLineTo(520f)
				close()
				moveTo(160f, 440f)
				horizontalLineToRelative(640f)
				verticalLineToRelative(-120f)
				horizontalLineTo(160f)
				close()
			}
		}.build()
		return _Newspaper!!
	}

private var _Newspaper: ImageVector? = null
