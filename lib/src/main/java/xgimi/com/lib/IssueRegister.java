package xgimi.com.lib;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Author: 彩笔学长
 * Time: created at 2016/12/14.
 * Description: IssueReguster
 */

public class IssueRegister extends IssueRegistry {

    @NotNull
    @Override
    public List<Issue> getIssues() {
        System.out.println("*******XSF LINT RULES WORKS*******");
        return Arrays.asList(
                MethodDetector.ISSUE
        );
    }

}
