package com.xgimi.lintlib.sample;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.xgimi.lintlib.util.ConfigurationTitle;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.ULiteralExpression;
import org.jetbrains.uast.UastLiteralUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author chongyang.zhang
 * @date 2018/7/28
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
public class NotesDetector extends Detector implements Detector.UastScanner {

    public static final Issue ISSUE = Issue.create(ConfigurationTitle.INSTANCE.addTitle("Notes"),
            "这是一个描述",
            " 这是展开 ",
            Category.CORRECTNESS, 6, Severity.WARNING,
            new Implementation(NotesDetector.class, Scope.JAVA_FILE_SCOPE));

    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(ULiteralExpression.class);
    }

    @Nullable
    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new UElementHandler() {
            @Override
            public void visitLiteralExpression(ULiteralExpression expression) {
                String string = UastLiteralUtils.getValueIfStringLiteral(expression);
                if (string == null) {
                    return;
                }
                if (string.contains("Log") && string.matches(".*\\bLog\\b.*")) {
                    context.report(ISSUE, expression, context.getLocation(expression),
                            "这是一个提示");
                }
            }
        };
    }
}
