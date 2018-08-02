package com.xgimi.lintlib.detector.log

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Severity
import com.xgimi.lintlib.util.ConfigurationTitle

/**
 * @author chongyang.zhang
 * @date 2018/7/30
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
object LogConstant {
    
    var ID: String = ConfigurationTitle.addTitle("错误的使用:")
    const val DESCRIPTION = "用能够区分debug环境的Log工具"
    const val EXPLANATION = "请使用由极米提供的Log工具或自己封装好的,以便能正确区分debug环境"
    val CATEGORY = Category.CORRECTNESS
    val SEVERITY = Severity.WARNING
    const val LEVEL = 5
    val MESSAGE = ConfigurationTitle.addTitle("使用错误的Log方式")
    
    const val LOG = "LogUtils"
    
}
