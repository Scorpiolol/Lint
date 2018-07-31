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
class XGimiPrintDetector : Detector(), SourceCodeScanner {
    companion object Issues {
        private val IMPLEMENTATION = Implementation(
                XGimiPrintDetector::class.java, Scope.JAVA_FILE_SCOPE)
        
        @JvmField
        val ISSUE = Issue.create(
                LogIssue.ID + ":print",
                LogIssue.DESCRIPTION,
                LogIssue.EXPLANATION,
                LogIssue.CATEGORY,
                LogIssue.LEVEL,
                LogIssue.SEVERITY,
                IMPLEMENTATION)
        
        private const val LOG_CLS = "java.io.PrintStream"
    }
    
    override fun getApplicableMethodNames(): List<String>? =
            Arrays.asList(
                    "printf",
                    "println",
                    "print")
    
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