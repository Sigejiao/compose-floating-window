package com.github.only52607.compose.window.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.min

/**
 * 自定义圆形图标组件
 * 包含两个同心圆：大圆在下层（外部颜色），小圆在上层（内部颜色）
 * 
 * 大小选项：
 * - 24.dp: 小尺寸（默认大小）
 * - 32.dp: 中等尺寸（悬浮窗中使用）
 * - 40.dp: 大尺寸
 * - 48.dp: 超大尺寸
 * - 56.dp: 最大尺寸
 * 
 * @param innerColor 内部小圆的颜色
 * @param outerColor 外部大圆的颜色，如果为null则使用内部颜色
 * @param size 图标大小
 * @param innerRadiusRatio 内部圆相对于外部圆的比例，默认0.7
 */
@Composable
fun CustomCircleIcon(
    innerColor: Color,
    outerColor: Color? = null,
    size: Dp = 60.dp,
    innerRadiusRatio: Float = 0.7f
) {
    val calculatedOuterColor = outerColor ?: innerColor
    
    Canvas(
        modifier = Modifier.size(size)
    ) {
        val center = Offset(this.size.width / 2, this.size.height / 2)
        val radius = min(this.size.width, this.size.height) / 2
        
        // 绘制大圆（外部，下层）
        drawCircle(
            color = calculatedOuterColor,
            radius = radius,
            center = center
        )
        
        // 绘制小圆（内部，上层）
        drawCircle(
            color = innerColor,
            radius = radius * innerRadiusRatio,
            center = center
        )
    }
}

/**
 * 带颜色切换的自定义圆形图标
 * 
 * @param colorList 颜色列表，每个元素包含颜色和名称
 * @param currentIndex 当前选中的颜色索引
 * @param onColorChange 颜色改变时的回调
 * @param size 图标大小
 * @param innerRadiusRatio 内部圆相对于外部圆的比例
 */
@Composable
fun CustomCircleIconWithColorChange(
    colorList: List<Pair<Color, String>>,
    currentIndex: Int,
    onColorChange: (Int) -> Unit,
    size: Dp = 60.dp,
    innerRadiusRatio: Float = 0.7f
) {
    val currentColor = colorList[currentIndex].first
    
    CustomCircleIcon(
        innerColor = currentColor,
        size = size,
        innerRadiusRatio = innerRadiusRatio
    )
}

/**
 * 使用示例：在FloatingActionButton中使用自定义圆形图标
 */
@Composable
fun FloatingActionButtonWithCustomIcon(
    colorList: List<Pair<Color, String>>,
    currentIndex: Int,
    onColorChange: (Int) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.FloatingActionButton(
        modifier = modifier,
        onClick = {
            val nextIndex = (currentIndex + 1) % colorList.size
            onColorChange(nextIndex)
            onClick()
        }
    ) {
        CustomCircleIconWithColorChange(
            colorList = colorList,
            currentIndex = currentIndex,
            onColorChange = onColorChange,
            size = 24.dp
        )
    }
} 