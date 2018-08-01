package com.xgimi.lintlib

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.xgimi.lintlib.detector.CheckNullDetector
import com.xgimi.lintlib.detector.TextViewStyleDetector
import com.xgimi.lintlib.detector.log.XGimiLogDetector
import com.xgimi.lintlib.detector.log.XGimiPrintDetector
import java.util.*

class IssueRegister : IssueRegistry() {
    
    override val issues: List<Issue>
        get() {
            println("*******XGimi Lint Start*******")
            return Arrays.asList(
                    TextViewStyleDetector.ISSUE,
                    XGimiLogDetector.ISSUE,
                    XGimiPrintDetector.ISSUE,
                    CheckNullDetector.ISSUE)
        }
    
    override val api: Int
        get() = CURRENT_API
}
