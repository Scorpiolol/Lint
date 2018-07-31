package com.xgimi.lintlib.detector;

import com.android.annotations.Nullable;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;
import com.xgimi.lintlib.util.ConfigurationTitle;

import org.w3c.dom.Element;

import java.util.Collection;
import java.util.Collections;

/**
 * @author chongyang.zhang
 * @date 2018/7/27
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
public class TextViewStyleDetector extends ResourceXmlDetector {

    private static final String ID = "miss Text";
    private static final String DESCRIPTION = "你确定不输入Text吗?";
    private static final String EXPLANATION = "输入文本信息";
    private static final Category CATEGORY = Category.TYPOGRAPHY;
    private static final int PRIORITY = 4;
    private static final Severity SEVERITY = Severity.WARNING;

    public static final Issue ISSUE = Issue.create(
            ConfigurationTitle.addTitle(ID),
            DESCRIPTION,
            EXPLANATION,
            CATEGORY,
            PRIORITY,
            SEVERITY,
            new Implementation(
                    TextViewStyleDetector.class,
                    Scope.RESOURCE_FILE_SCOPE)
    );

    private static final String SCHEMA = "http://schemas.android.com/apk/res/android";
    private static final String TEXT_APPEARANCE = "text";
    private static final String TEXT_VIEW = "TextView";

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(TEXT_VIEW);
    }

    @Override
    public void visitElement(XmlContext context, Element element) {
        if (!element.hasAttributeNS(SCHEMA, TEXT_APPEARANCE)) {
            context.report(
                    ISSUE,
                    context.getLocation(element),
                    EXPLANATION);
        }
    }
}