package com.xgimi.lintlib.detector

import com.android.annotations.NonNull
import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.xgimi.lintlib.util.ConfigurationTitle
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UParameter
import java.util.*

/**
 * @author chongyang.zhang
 * @date 2018/7/30
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
class CheckNullDetector : Detector(), SourceCodeScanner {

    companion object {
        private const val charNonNull = "NonNull"
        private const val charNullable = "Nullable"
        private const val androidAnnotations = "com.android.annotations."
        private const val supportAnnotation = "android.support.annotation."
        private const val rxAnnotation = "io.reactivex.annotations."
        private const val androidX = "androidx.annotation."

        private const val androidNonNull = androidAnnotations + charNonNull
        private const val androidNullable = androidAnnotations + charNullable

        private const val supportNonNull = supportAnnotation + charNonNull
        private const val supportNullable = supportAnnotation + charNullable

        private const val rxNonNull = rxAnnotation + charNullable
        private const val rxNullable = rxAnnotation + charNullable
        private const val androidXNonNull = androidX + charNullable
        private const val androidXNullable = androidX + charNullable

        private val annotationArray = arrayOf(supportNonNull, supportNullable,
                androidNonNull, androidNullable,
                rxNonNull, rxNullable,
                androidXNonNull, androidXNullable)

        private val passArray = arrayOf("int", "double", "float", "short", "boolean", "byte", "long", "char")

        @JvmField
        val ISSUE = Issue.create(ConfigurationTitle.addTitle(ConfigurationTitle.addTitle("参数")),
                ConfigurationTitle.addTitle("请标记 @$charNullable 或 @$charNonNull"),
                "给参数标记 @$charNullable 或 @$charNonNull，可以使代码更健壮，也使之后转Kotlin容错更高",
                Category.CORRECTNESS, 4, Severity.WARNING,
                Implementation(CheckNullDetector::class.java, Scope.JAVA_FILE_SCOPE))
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        val types = ArrayList<Class<out UElement>>(1)
        types.add(UParameter::class.java)
        return types
    }


    override fun createUastHandler(@NonNull context: JavaContext): UElementHandler? {
        return AnnotationChecker(context)
    }

    private inner class AnnotationChecker internal constructor(
            @param:NonNull private val mContext: JavaContext) : UElementHandler() {

        override fun visitParameter(@NonNull node: UParameter) {
            var isCheckPass = false
            for (i in annotationArray.indices) {
                if (node.findAnnotation(annotationArray[i]) != null) {
                    isCheckPass = true
                    break
                }
            }
            val typeName = getTypeName(node)
            for (i in passArray.indices) {
                if (typeName.contains(passArray[i])) return
            }

            if (!isCheckPass) mContext.report(ISSUE, node.uastAnchor, mContext.getLocation(node.uastAnchor!!),
                    ConfigurationTitle.addTitle("请标记 @$charNullable 或 @$charNonNull"),
                    initFixGroup(mContext, node)
            )
        }
    }

    private fun createLintFix(mContext: JavaContext, node: UParameter, with: String): LintFix {
        val location = mContext.getLocation(node.typeElement!!)
        val name = getTypeName(node)
        return LintFix.create()
                .name(ConfigurationTitle.addTitle("添加@$with"))
                .replace()
                .all()
                .range(location)
                .with("@$with $name")
                .build()
    }

    private fun initFixGroup(mContext: JavaContext, node: UParameter): LintFix {
        return createFixGroup(
                createLintFix(mContext, node, charNullable),
                createLintFix(mContext, node, charNonNull)
        )
    }

    private fun createFixGroup(vararg lintFix: LintFix): LintFix {
        return LintFix.create().group(*lintFix)
    }

    private fun getTypeName(node: UParameter): String {
        return node.text!!.replace(" " + node.name!!, "")
    }
}
