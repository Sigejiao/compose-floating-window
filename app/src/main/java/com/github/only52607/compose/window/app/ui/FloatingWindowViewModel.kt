package com.github.only52607.compose.window.app.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.only52607.compose.window.app.data.ClickEvent
import com.github.only52607.compose.window.app.data.ClickRepository
import com.github.only52607.compose.window.app.data.ColorClickStat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FloatingWindowViewModel(
    private val clickRepository: ClickRepository
) : ViewModel() {
    
    private var _dialogVisible by mutableStateOf(false)
    val dialogVisible: Boolean get() = _dialogVisible
    
    // 点击事件数据
    private val _clickEvents = MutableStateFlow<List<ClickEvent>>(emptyList())
    val clickEvents: StateFlow<List<ClickEvent>> = _clickEvents.asStateFlow()
    
    // 点击统计
    private val _clickStatistics = MutableStateFlow<List<ColorClickStat>>(emptyList())
    val clickStatistics: StateFlow<List<ColorClickStat>> = _clickStatistics.asStateFlow()
    
    // 总点击次数
    private val _totalClickCount = MutableStateFlow(0)
    val totalClickCount: StateFlow<Int> = _totalClickCount.asStateFlow()
    
    // 今日点击次数
    private val _todayClickCount = MutableStateFlow(0)
    val todayClickCount: StateFlow<Int> = _todayClickCount.asStateFlow()

    fun showDialog() {
        _dialogVisible = true
    }

    fun dismissDialog() {
        _dialogVisible = false
    }
    
    /**
     * 记录点击事件
     */
    fun recordClick(
        colorIndex: Int,
        colorName: String,
        xPosition: Float,
        yPosition: Float,
        iconSize: Int,
        note: String = ""
    ) {
        viewModelScope.launch {
            clickRepository.insertClick(
                colorIndex = colorIndex,
                colorName = colorName,
                xPosition = xPosition,
                yPosition = yPosition,
                iconSize = iconSize,
                note = note
            )
        }
    }
    
    /**
     * 加载所有点击事件
     */
    fun loadClickEvents() {
        viewModelScope.launch {
            clickRepository.getAllClicks().collect { events ->
                _clickEvents.value = events
            }
        }
    }
    
    /**
     * 加载点击统计
     */
    fun loadClickStatistics() {
        viewModelScope.launch {
            clickRepository.getClickStatistics().collect { stats ->
                _clickStatistics.value = stats
            }
        }
    }
    
    /**
     * 加载总点击次数
     */
    fun loadTotalClickCount() {
        viewModelScope.launch {
            clickRepository.getTotalClickCount().collect { count ->
                _totalClickCount.value = count
            }
        }
    }
    
    /**
     * 加载今日点击次数
     */
    fun loadTodayClickCount() {
        viewModelScope.launch {
            clickRepository.getTodayClickCount().collect { count ->
                _todayClickCount.value = count
            }
        }
    }
    
    /**
     * 删除所有点击事件
     */
    fun deleteAllClicks() {
        viewModelScope.launch {
            clickRepository.deleteAllClicks()
        }
    }
}