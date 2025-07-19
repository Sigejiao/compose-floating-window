package com.github.only52607.compose.window.app.ui

import androidx.compose.ui.graphics.Color
import kotlin.math.min
import kotlin.math.max

/**
 * 颜色工具类
 */
object ColorUtils {
    
    /**
     * 计算颜色的亮度
     * 使用标准的亮度公式: 0.299*R + 0.587*G + 0.114*B
     */
    fun calculateBrightness(color: Color): Float {
        return color.red * 0.299f + color.green * 0.587f + color.blue * 0.114f
    }
    
    /**
     * 调整颜色亮度
     * @param color 原始颜色
     * @param brightnessAdjustment 亮度调整值 (-1.0 到 1.0)
     * @return 调整后的颜色
     */
    fun adjustBrightness(color: Color, brightnessAdjustment: Float): Color {
        val currentBrightness = calculateBrightness(color)
        val newBrightness = min(1f, max(0f, currentBrightness + brightnessAdjustment))
        
        // 如果当前亮度为0，直接返回调整后的颜色
        if (currentBrightness == 0f) {
            return Color(
                red = newBrightness,
                green = newBrightness,
                blue = newBrightness,
                alpha = color.alpha
            )
        }
        
        // 按比例调整RGB值
        val ratio = newBrightness / currentBrightness
        return Color(
            red = min(1f, color.red * ratio),
            green = min(1f, color.green * ratio),
            blue = min(1f, color.blue * ratio),
            alpha = color.alpha
        )
    }
    
    /**
     * 增加颜色亮度
     * @param color 原始颜色
     * @param increment 增加的亮度值 (0.0 到 1.0)
     * @return 调整后的颜色
     */
    fun increaseBrightness(color: Color, increment: Float): Color {
        return adjustBrightness(color, increment)
    }
    
    /**
     * 减少颜色亮度
     * @param color 原始颜色
     * @param decrement 减少的亮度值 (0.0 到 1.0)
     * @return 调整后的颜色
     */
    fun decreaseBrightness(color: Color, decrement: Float): Color {
        return adjustBrightness(color, -decrement)
    }
    
    /**
     * 预定义的颜色列表
     */
    val predefinedColors = listOf(
        Color.Red to "红色",
        Color.Green to "绿色", 
        Color.Blue to "蓝色",
        Color.Yellow to "黄色",
        Color.Cyan to "青色",
        Color.Magenta to "洋红",
        Color.Gray to "灰色",
        Color.Black to "黑色",
        Color.White to "白色",
        Color(0xFF8B4513) to "马鞍棕色", // Saddle Brown
        Color(0xFF32CD32) to "酸橙绿",   // Lime Green
        Color(0xFF00CED1) to "深青色",   // Dark Turquoise
        Color(0xFF9370DB) to "中等紫色",  // Medium Purple
        Color(0xFFFF6347) to "番茄红",   // Tomato
        Color(0xFF20B2AA) to "浅海绿",   // Light Sea Green
        Color(0xFFDDA0DD) to "梅红色"    // Plum
    )
}

// 扩展函数，方便使用
fun Color.increaseBrightness(increment: Float): Color {
    return ColorUtils.increaseBrightness(this, increment)
}

fun Color.decreaseBrightness(decrement: Float): Color {
    return ColorUtils.decreaseBrightness(this, decrement)
}

fun Color.getBrightness(): Float {
    return ColorUtils.calculateBrightness(this)
} 