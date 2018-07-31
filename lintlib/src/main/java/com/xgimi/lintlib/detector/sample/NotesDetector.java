package com.xgimi.lintlib.detector.sample;

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

    public static final Issue ISSUE = Issue.create(ConfigurationTitle.addTitle("Notes"),
            "注释的双斜线与注释内容之间有且仅有一个空格",
            "// 这是示例注释，请注意在双斜线之后有一个空格  \n" +
                    "String test = new String(); ",
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
                if (string.contains("//") && string.matches(".*\\b//\\b.*")) {
                    context.report(ISSUE, expression, context.getLocation(expression),
                            "注释的双斜线与注释内容之间有且仅有一个空格");
                }
            }
        };
    }
}
