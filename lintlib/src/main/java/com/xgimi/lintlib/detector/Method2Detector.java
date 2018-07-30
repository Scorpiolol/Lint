package com.xgimi.lintlib.detector;

import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.xgimi.lintlib.util.ConfigurationTitle;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UExpression;
import org.jetbrains.uast.UQualifiedReferenceExpression;

import java.util.Arrays;
import java.util.List;

/**
 * @author chongyang.zhang
 * @date 2018/7/27
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
public class Method2Detector extends Detector implements SourceCodeScanner {

    private static final String LOG_CLS = "android.util.Log";
    private static final String PRINTLN = "println";
    private static final String IS_LOGGABLE = "isLoggable";

    public static final Issue ISSUE = Issue.create(ConfigurationTitle.addTitle("Log测试"),
            "123123测试",
            "cosadifsdf",
            Category.TYPOGRAPHY, 4, Severity.WARNING,
            new Implementation(Method2Detector.class, Scope.JAVA_FILE_SCOPE));


    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList(
                "d",
                "e",
                "i",
                "v",
                "w",
                PRINTLN,
                IS_LOGGABLE);
    }

    @Override
    public void visitMethod(JavaContext context, UCallExpression node, PsiMethod method) {

        JavaEvaluator javaEvaluator = context.getEvaluator();
        if (!javaEvaluator.isMemberInClass(method, LOG_CLS)) {
            return;
        }
        String name = method.getName();
    }

    @Override
    public void visitMethod(JavaContext context, JavaElementVisitor visitor, PsiMethodCallExpression call, PsiMethod
            method) {
        boolean b = context.getEvaluator().isMemberInClass(method, "android.util.Log");
        if (b) {
            context.report(
                    ISSUE,
                    call,
                    context.getLocation(call.getMethodExpression()),
                    "请使用极米提供的Log");
        }
        b = context.getEvaluator().isMemberInClass(method, "java.io");
        if (b && method.getName().endsWith("print")) {
            context.report(
                    ISSUE,
                    call,
                    context.getLocation(call.getMethodExpression()),
                    "请使用自定义的输出格式");
        }
    }

    private UExpression getLastInQualifiedChain(UQualifiedReferenceExpression node) {
        UExpression last = node.getSelector();
        while (last instanceof UQualifiedReferenceExpression) {
            last = ((UQualifiedReferenceExpression) last).getSelector();
        }
        return last;
    }
}
