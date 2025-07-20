package com.github.only52607.compose.window.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 点击事件数据实体
 * 用于记录悬浮窗的每次点击事件
 */
@Entity(tableName = "click_events")
data class ClickEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    /**
     * 点击时间戳（毫秒）
     */
    val timestamp: Long,
    
    /**
     * 点击时的颜色索引
     */
    val colorIndex: Int,
    
    /**
     * 颜色名称
     */
    val colorName: String,
    
    /**
     * 悬浮窗X位置
     */
    val xPosition: Float,
    
    /**
     * 悬浮窗Y位置
     */
    val yPosition: Float,
    
    /**
     * 点击时的图标大小
     */
    val iconSize: Int,
    
    /**
     * 额外备注信息
     */
    val note: String = ""
) 