package com.xgimi.lintlib;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.xgimi.lintlib.detector.TextViewStyleDetector;
import com.xgimi.lintlib.detector.log.XGimiLogDetector;
import com.xgimi.lintlib.detector.log.XGimiPrintDetector;
import com.xgimi.lintlib.detector.sample.TestDetector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class IssueRegister extends IssueRegistry {

    @NotNull
    @Override
    public List<Issue> getIssues() {
        System.out.println("*******XSF LINT RULES WORKS*******");
        return Arrays.asList(
                TextViewStyleDetector.ISSUE,
                XGimiLogDetector.ISSUE,
                XGimiPrintDetector.ISSUE,
                TestDetector.ISSUE);
    }

    @Override
    public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }
}
