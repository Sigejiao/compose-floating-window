package com.github.only52607.compose.window.app.data

import android.content.Context

/**
 * 简单的依赖注入提供者
 * 在没有使用Hilt的情况下提供依赖
 */
object DependencyProvider {
    
    private var database: AppDatabase? = null
    private var clickRepository: ClickRepository? = null
    
    /**
     * 初始化依赖
     */
    fun initialize(context: Context) {
        if (database == null) {
            database = AppDatabase.getDatabase(context)
        }
        if (clickRepository == null) {
            clickRepository = ClickRepositoryImpl(database!!.clickEventDao())
        }
    }
    
    /**
     * 获取点击事件仓库
     */
    fun getClickRepository(): ClickRepository {
        return clickRepository ?: throw IllegalStateException("DependencyProvider not initialized")
    }
    
    /**
     * 清理依赖
     */
    fun cleanup() {
        database = null
        clickRepository = null
    }
} 