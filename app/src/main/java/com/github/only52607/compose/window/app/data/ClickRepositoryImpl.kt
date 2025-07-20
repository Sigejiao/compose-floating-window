package com.github.only52607.compose.window.app.data

import kotlinx.coroutines.flow.Flow
import java.util.Calendar

/**
 * 点击事件仓库实现类
 * 实现ClickRepository接口，通过DAO操作数据库
 */
class ClickRepositoryImpl(
    private val clickEventDao: ClickEventDao
) : ClickRepository {
    
    override suspend fun insertClick(
        colorIndex: Int,
        colorName: String,
        xPosition: Float,
        yPosition: Float,
        iconSize: Int,
        note: String
    ) {
        val clickEvent = ClickEvent(
            timestamp = System.currentTimeMillis(),
            colorIndex = colorIndex,
            colorName = colorName,
            xPosition = xPosition,
            yPosition = yPosition,
            iconSize = iconSize,
            note = note
        )
        clickEventDao.insertClickEvent(clickEvent)
    }
    
    override fun getAllClicks(): Flow<List<ClickEvent>> {
        return clickEventDao.getAllClickEvents()
    }
    
    override fun getClicksByTimeRange(startTime: Long, endTime: Long): Flow<List<ClickEvent>> {
        return clickEventDao.getClickEventsByTimeRange(startTime, endTime)
    }
    
    override fun getTotalClickCount(): Flow<Int> {
        return clickEventDao.getTotalClickCount()
    }
    
    override fun getClickCountByColor(colorIndex: Int): Flow<Int> {
        return clickEventDao.getClickCountByColor(colorIndex)
    }
    
    override fun getTodayClickCount(): Flow<Int> {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startOfDay = calendar.timeInMillis
        return clickEventDao.getTodayClickCount(startOfDay)
    }
    
    override fun getClickStatistics(): Flow<List<ColorClickStat>> {
        return clickEventDao.getClickStatistics()
    }
    
    override suspend fun deleteAllClicks() {
        clickEventDao.deleteAllClickEvents()
    }
    
    override suspend fun deleteClicksBefore(timestamp: Long) {
        clickEventDao.deleteClickEventsBefore(timestamp)
    }
} 