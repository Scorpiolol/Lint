package com.xgimi.lintlib.util

import com.android.annotations.Nullable

/**
 * @author chongyang.zhang
 * @date 2018/7/28
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
object ConfigurationTitle {
    
    fun addTitle(@Nullable title: String): String {
        return "XGimi_$title"
    }
}
