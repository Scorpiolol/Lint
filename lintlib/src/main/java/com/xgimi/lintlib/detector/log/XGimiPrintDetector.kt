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
class XGimiPrintDetector : Detector(), SourceCodeScanner {
    companion object Issues {
        private val IMPLEMENTATION = Implementation(
                XGimiPrintDetector::class.java, Scope.JAVA_FILE_SCOPE)
        
        @JvmField
        val ISSUE = Issue.create(
                LogConstant.ID + "print",
                LogConstant.DESCRIPTION,
                LogConstant.EXPLANATION,
                LogConstant.CATEGORY,
                LogConstant.LEVEL,
                LogConstant.SEVERITY,
                IMPLEMENTATION)
        
        private const val LOG_CLS = "java.io.PrintStream"
        
    }
    
    private fun initFix(replace: String): LintFix {
        return LintFix
                .create()
                .name(ConfigurationTitle.addTitle("使用${LogConstant.LOG}"))
                .replace()
                .all()
                .pattern("System\\.out\\.$replace\\(")
                .with("${LogConstant.LOG}.d(TAG,")
                .build()!!
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
            context.report(ISSUE, node, context.getLocation(node), LogConstant.MESSAGE, initFix(method.name))
        }
    }
}