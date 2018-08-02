package com.xgimi.lintlib.detector

import com.android.annotations.NonNull
import com.android.annotations.Nullable
import com.android.tools.lint.detector.api.*
import com.xgimi.lintlib.util.ConfigurationTitle
import org.w3c.dom.Element

/**
 * @author chongyang.zhang
 * @date 2018/7/27
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
class TextViewStyleDetector : ResourceXmlDetector() {
    
    companion object {
        private const val ID = "属性缺失"
        private const val DESCRIPTION = "你确定该TextView不输入Text吗?"
        private const val EXPLANATION = "输入文本信息"
        private val CATEGORY = Category.TYPOGRAPHY
        private const val PRIORITY = 4
        private val SEVERITY = Severity.WARNING
        
        @JvmField
        val ISSUE = Issue.create(
                ConfigurationTitle.addTitle(ID),
                ConfigurationTitle.addTitle(DESCRIPTION),
                EXPLANATION,
                CATEGORY,
                PRIORITY,
                SEVERITY,
                Implementation(
                        TextViewStyleDetector::class.java,
                        Scope.RESOURCE_FILE_SCOPE)
        )
        
        private const val SCHEMA = "http://schemas.android.com/apk/res/android"
        private const val TEXT_APPEARANCE = "text"
        private const val TEXT_VIEW = "TextView"
    }
    
    @Nullable
    override fun getApplicableElements(): Collection<String>? {
        return listOf(TEXT_VIEW)
    }
    
    override fun visitElement(@NonNull context: XmlContext, @NonNull element: Element) {
        if (!element.hasAttributeNS(SCHEMA, TEXT_APPEARANCE)) {
            context.report(
                    ISSUE,
                    context.getLocation(element),
                    EXPLANATION)
        }
    }
    
    
}