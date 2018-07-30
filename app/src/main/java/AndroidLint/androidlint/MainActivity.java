package AndroidLint.androidlint;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author chongyang.zhang
 * @date 2018/7/27
 * maintainer chongyang.zhang
 * copyright 2018 www.xgimi.com Inc. All rights reserved.
 * desc:
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String lint = "lint";
    private String lint2 = "Log";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "test: ");
        Log.d(TAG, "D");
        Log.i(TAG, "D");
        Log.v(TAG, "D");
        System.out.print("123");
        String s = "say lint";
        String s1 = lint;

        getActionBar();
        AlarmManager alarmManager = null;
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, 0, 0, null);
    }

    private void test() {
        String s = lint;
    }
}
