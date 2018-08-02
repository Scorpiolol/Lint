package com.xgimi.lintlib.detector.log

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
import com.xgimi.lintlib.util.ConfigurationTitle
import org.jetbrains.uast.UCallExpression
import java.util.*

/**
 * @author chongyang.zhang
 * @date 2018/7/30
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
class XGimiLogDetector : Detector(), SourceCodeScanner {
    companion object Issues {
        private val IMPLEMENTATION = Implementation(
                XGimiLogDetector::class.java, Scope.JAVA_FILE_SCOPE)
        
        @JvmField
        val ISSUE = Issue.create(
                LogConstant.ID + "Log",
                LogConstant.DESCRIPTION,
                LogConstant.EXPLANATION,
                LogConstant.CATEGORY,
                LogConstant.LEVEL,
                LogConstant.SEVERITY,
                IMPLEMENTATION)
        
        private const val IS_LOGGABLE = "isLoggable"
        private const val LOG_CLS = "android.util.Log"
        private const val PRINTLN = "println"
        
        val logFix: LintFix = LintFix
                .create()
                .name(ConfigurationTitle.addTitle("使用${LogConstant.LOG}"))
                .replace()
                .all()
                .pattern("Log")
                .with(LogConstant.LOG)
                .build()
        
    }
    
    override fun getApplicableMethodNames(): List<String>? =
            Arrays.asList(
                    "d",
                    "e",
                    "i",
                    "v",
                    "w",
                    PRINTLN,
                    IS_LOGGABLE)
    
    override fun visitMethod(context: JavaContext, node: UCallExpression,
                             method: PsiMethod) {
        val evaluator = context.evaluator
        if (!evaluator.isMemberInClass(method, LOG_CLS)) {
            return
        }
        if (context.isEnabled(ISSUE)) {
            context.report(ISSUE,
                    node,
                    context.getLocation(node),
                    LogConstant.MESSAGE,
                    logFix)
        }
    }
    
}