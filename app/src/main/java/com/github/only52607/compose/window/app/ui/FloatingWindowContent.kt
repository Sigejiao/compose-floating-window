package com.github.only52607.compose.window.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SystemAlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.only52607.compose.window.LocalFloatingWindow
import com.github.only52607.compose.window.dragFloatingWindow
import kotlin.math.min
import kotlin.math.max

@Composable
fun FloatingWindowContent(
    model: FloatingWindowViewModel = viewModel()
) {
    val floatingWindow = LocalFloatingWindow.current
    
    // 使用预定义的颜色列表
    val colorList = ColorUtils.predefinedColors
    
    // 当前选中的颜色索引
    var currentColorIndex by remember { mutableStateOf(0) }
    
    // 获取当前颜色
    val currentColor = colorList[currentColorIndex].first
    
    // 计算外部颜色（半透明状态）
    val outerColor = remember(currentColor) {
        Color(
            red = currentColor.red,
            green = currentColor.green,
            blue = currentColor.blue,
            alpha = 0.5f // 恰好半透明
        )
    }
    
    if (model.dialogVisible) {
        SystemAlertDialog(
            onDismissRequest = { model.dismissDialog() },
            confirmButton = {
                TextButton(onClick = { model.dismissDialog() }) {
                    Text(text = "确定")
                }
            },
            text = {
                Text(text = "当前颜色: ${colorList[currentColorIndex].second}")
            }
        )
    }
    
    FloatingActionButton(
        modifier = Modifier
            .dragFloatingWindow()
            .size(56.dp), // 固定悬浮窗大小为标准FAB大小
        onClick = {
            // 点击时切换到下一个颜色
            currentColorIndex = (currentColorIndex + 1) % colorList.size
            model.showDialog()
        }) {
        // 使用自定义圆形图标，保持较大尺寸
        CustomCircleIcon(
            innerColor = currentColor,
            outerColor = outerColor,
            size = 40.dp,
            innerRadiusRatio = 0.7f
        )
    }
}



@Preview
@Composable
fun FloatingWindowContentPreview() {
    FloatingWindowContent()
}