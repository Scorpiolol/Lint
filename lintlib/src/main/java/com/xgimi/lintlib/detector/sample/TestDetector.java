package com.xgimi.lintlib.detector.sample;

import com.android.annotations.NonNull;
import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.xgimi.lintlib.util.ConfigurationTitle;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UAnnotation;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UMethod;
import org.jetbrains.uast.UParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chongyang.zhang
 * @date 2018/7/30
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
public class TestDetector extends Detector implements SourceCodeScanner {
    public static final Issue ISSUE = Issue.create(ConfigurationTitle.addTitle("参数"),
            "请标记 @Nullable 或 @NonNull",
            "请标记 @Nullable 或 @NonNull",
            Category.CORRECTNESS, 6, Severity.WARNING,
            new Implementation(TestDetector.class, Scope.JAVA_FILE_SCOPE));

    private String androidAnnotations = "com.android.annotations.";
    private String supportAnnotation = "android.support.annotation.";
    private String androidNonNull = androidAnnotations + "NonNull";
    private String androidNullable = androidAnnotations + "Nullable";
    private String supportNonNull = supportAnnotation + "NonNull";
    private String supportNullable = supportAnnotation + "Nullable";
    private String langAnnotation = "java.lang.";


    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("String", "Integer");
    }


    @Nullable
    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        List<Class<? extends UElement>> types = new ArrayList<>(3);
        types.add(UAnnotation.class);
        types.add(UParameter.class);
        types.add(UMethod.class);
        return types;
    }


    @Nullable
    @Override
    public UElementHandler createUastHandler(@NonNull JavaContext context) {
        return new AnnotationChecker(context);
    }

    private class AnnotationChecker extends UElementHandler {
        private final JavaContext mContext;

        AnnotationChecker(JavaContext context) {
            mContext = context;
        }

        @Override
        public void visitMethod(@NonNull UMethod node) {
            for (UParameter item : node.getUastParameters()) {
                UAnnotation supNonNullAnnotation = item.findAnnotation(supportNonNull);
                UAnnotation supNullableAnnotation = item.findAnnotation(supportNullable);
                UAnnotation androidNonNullAnnotation = item.findAnnotation(androidNonNull);
                UAnnotation androidNullableAnnotation = item.findAnnotation(androidNullable);
                if (supNonNullAnnotation == null
                        && supNullableAnnotation == null
                        && androidNonNullAnnotation == null
                        && androidNullableAnnotation == null) {
                    mContext.report(ISSUE, node,
                            mContext.getLocation(item.getSourceElement()), "请标记 @Nullable 或 @NonNull");
                }
            }
        }
    }
}
