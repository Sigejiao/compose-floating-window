package com.github.only52607.compose.window.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.only52607.compose.window.app.data.ClickRepository

/**
 * FloatingWindowViewModel的工厂类
 * 用于创建带有依赖的ViewModel实例
 */
class FloatingWindowViewModelFactory(
    private val clickRepository: ClickRepository
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FloatingWindowViewModel::class.java)) {
            return FloatingWindowViewModel(clickRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 