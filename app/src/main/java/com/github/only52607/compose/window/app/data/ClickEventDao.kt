package com.github.only52607.compose.window.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * 点击事件数据访问对象
 * 定义数据库操作方法
 */
@Dao
interface ClickEventDao {
    
    /**
     * 插入点击事件
     */
    @Insert
    suspend fun insertClickEvent(clickEvent: ClickEvent)
    
    /**
     * 获取所有点击事件，按时间倒序排列
     */
    @Query("SELECT * FROM click_events ORDER BY timestamp DESC")
    fun getAllClickEvents(): Flow<List<ClickEvent>>
    
    /**
     * 根据时间范围获取点击事件
     */
    @Query("SELECT * FROM click_events WHERE timestamp BETWEEN :startTime AND :endTime ORDER BY timestamp DESC")
    fun getClickEventsByTimeRange(startTime: Long, endTime: Long): Flow<List<ClickEvent>>
    
    /**
     * 获取总点击次数
     */
    @Query("SELECT COUNT(*) FROM click_events")
    fun getTotalClickCount(): Flow<Int>
    
    /**
     * 获取指定颜色的点击次数
     */
    @Query("SELECT COUNT(*) FROM click_events WHERE colorIndex = :colorIndex")
    fun getClickCountByColor(colorIndex: Int): Flow<Int>
    
    /**
     * 获取今日点击次数
     */
    @Query("SELECT COUNT(*) FROM click_events WHERE timestamp >= :startOfDay")
    fun getTodayClickCount(startOfDay: Long): Flow<Int>
    
    /**
     * 获取点击事件统计信息
     */
    @Query("SELECT colorIndex, colorName, COUNT(*) as count FROM click_events GROUP BY colorIndex, colorName ORDER BY count DESC")
    fun getClickStatistics(): Flow<List<ColorClickStat>>
    
    /**
     * 删除所有点击事件
     */
    @Query("DELETE FROM click_events")
    suspend fun deleteAllClickEvents()
    
    /**
     * 删除指定时间范围之前的点击事件
     */
    @Query("DELETE FROM click_events WHERE timestamp < :timestamp")
    suspend fun deleteClickEventsBefore(timestamp: Long)
}

/**
 * 颜色点击统计数据类
 */
data class ColorClickStat(
    val colorIndex: Int,
    val colorName: String,
    val count: Int
) 