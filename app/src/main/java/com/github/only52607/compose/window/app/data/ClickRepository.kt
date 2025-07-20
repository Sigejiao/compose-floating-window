package com.github.only52607.compose.window.app.data

import kotlinx.coroutines.flow.Flow

/**
 * 点击事件仓库接口
 * 定义数据操作的高层接口
 */
interface ClickRepository {
    
    /**
     * 记录点击事件
     */
    suspend fun insertClick(
        colorIndex: Int,
        colorName: String,
        xPosition: Float,
        yPosition: Float,
        iconSize: Int,
        note: String = ""
    )
    
    /**
     * 获取所有点击事件
     */
    fun getAllClicks(): Flow<List<ClickEvent>>
    
    /**
     * 根据时间范围获取点击事件
     */
    fun getClicksByTimeRange(startTime: Long, endTime: Long): Flow<List<ClickEvent>>
    
    /**
     * 获取总点击次数
     */
    fun getTotalClickCount(): Flow<Int>
    
    /**
     * 获取指定颜色的点击次数
     */
    fun getClickCountByColor(colorIndex: Int): Flow<Int>
    
    /**
     * 获取今日点击次数
     */
    fun getTodayClickCount(): Flow<Int>
    
    /**
     * 获取点击统计信息
     */
    fun getClickStatistics(): Flow<List<ColorClickStat>>
    
    /**
     * 删除所有点击事件
     */
    suspend fun deleteAllClicks()
    
    /**
     * 删除指定时间之前的点击事件
     */
    suspend fun deleteClicksBefore(timestamp: Long)
} 