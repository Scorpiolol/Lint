package com.xgimi.lintlib;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.xgimi.lintlib.detector.MethodDetector;
import com.xgimi.lintlib.detector.TextViewStyleDetector;
import com.xgimi.lintlib.detector.XGimiLog2Detector;
import com.xgimi.lintlib.detector.XGimiLogDetector;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class IssueRegister extends IssueRegistry {

    @NotNull
    @Override
    public List<Issue> getIssues() {
        System.out.println("*******XSF LINT RULES WORKS*******");
        return Arrays.asList(
                MethodDetector.ISSUE,
//                Method2Detector.ISSUE,
                TextViewStyleDetector.ISSUE,
                XGimiLogDetector.ISSUE,
                XGimiLog2Detector.CONDITIONAL,
                XGimiLog2Detector.WRONG_TAG,
                XGimiLog2Detector.LONG_TAG);
    }

    @Override
    public int getApi() {
        return com.android.tools.lint.detector.api.ApiKt.CURRENT_API;
    }
}
