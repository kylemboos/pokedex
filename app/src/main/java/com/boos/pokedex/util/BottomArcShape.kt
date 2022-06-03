package com.boos.pokedex.util

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BottomArcShape(private val arcJuice: Float = 0.18f) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(drawBottomArcPath(size, arcJuice))
    }

    private fun drawBottomArcPath(size: Size, arcJuice: Float): Path {
        return Path().apply {
            val juiceDistance = size.height * arcJuice
            reset()
            lineTo(x = size.width, y = 0f)
            lineTo(x = size.width, y = size.height - juiceDistance)
            quadraticBezierTo(x1 = size.width/2, y1 = size.height, x2 = 0f, y2 = size.height - juiceDistance)
            close()
        }
    }
}