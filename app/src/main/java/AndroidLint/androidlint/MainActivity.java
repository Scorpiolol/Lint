package AndroidLint.androidlint;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private String lint2 = "Log";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "D");
        Log.i(TAG, "D");
        Log.v(TAG, "D");

        System.out.print("1231231ahkdjfaisduoifazxjhcuadfaasdfasdf");
        String s = "say lint";
        String lint = "lint";
        String s1 = lint;

        // // 这是一个注释
        // getActionBar();
        // test("123");
        // test(null);
        // test01("123", 1);
        // test01("1233", 2);
        // AlarmManager alarmManager = null;
        // alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, 0, 0, null);
    }

    private void test(@Nullable String test) {
        String s = test;
    }

    private void test01(@NonNull String test, Integer integer) {
        String s = test;
        Integer integer1 = integer;
    }

    public static void test03(String test) {
        String s = test;
    }

}
