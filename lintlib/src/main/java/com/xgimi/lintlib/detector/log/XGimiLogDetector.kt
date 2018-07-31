package com.xgimi.lintlib.detector.log

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiMethod
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
                LogIssue.ID + ":Log",
                LogIssue.DESCRIPTION,
                LogIssue.EXPLANATION,
                LogIssue.CATEGORY,
                LogIssue.LEVEL,
                LogIssue.SEVERITY,
                IMPLEMENTATION)
        
        private const val IS_LOGGABLE = "isLoggable"
        private const val LOG_CLS = "android.util.Log"
        private const val PRINTLN = "println"
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
            context.report(ISSUE, node, context.getLocation(node), LogIssue.MESSAGE)
        }
    }
}