package com.xgimi.lintlib.detector.sample;

import com.android.ddmlib.Log;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.ClassContext;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.xgimi.lintlib.util.ConfigurationTitle;

import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.List;

/**
 * @author chongyang.zhang
 * @date 2018/7/27
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
public class MethodDetector extends Detector implements Detector.ClassScanner {
    private static final String TAG = "MethodDetector";

    public static final Issue ISSUE = Issue.create(ConfigurationTitle.addTitle("LogUtils"),
            "用能够区分debug环境的Log工具",
            "请使用由极米提供的Log工具或自己封装好的",
            Category.TYPOGRAPHY, 4, Severity.WARNING,
            new Implementation(MethodDetector.class, Scope.CLASS_FILE_SCOPE));

    @Nullable
    @Override
    public List<String> getApplicableCallNames() {
        return Arrays.asList("v", "d", "i", "w", "e", "wtf");
    }

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        return Arrays.asList("v", "d", "i", "w", "e", "wtf");
    }

    @Override
    public void checkCall(ClassContext context, ClassNode classNode, MethodNode method, MethodInsnNode call) {
        String s = context.getSourceContents().toString();
        Log.d(TAG, "s==" + s);
        String owner = call.owner;
        if (owner.startsWith("android/util/Log")) {
            context.report(ISSUE,
                    method,
                    call,
                    context.getLocation(call),
                    "请使用由极米提供的Log工具或自己封装好的");
        }
    }


}
