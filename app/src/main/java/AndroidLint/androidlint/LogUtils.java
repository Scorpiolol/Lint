package AndroidLint.androidlint;

import android.util.Log;

/**
 * @author zcy.
 * @date 2017/12/29.
 */
public class LogUtils {

    private static final String TAG = "way";
    /**
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     */
    public static boolean isDebug = true;

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 下面四个是默认tag的函数
     */
    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, buildLogString(msg));
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, buildLogString(msg));
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, buildLogString(msg));
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, buildLogString(msg));
        }
    }

    /**
     * 下面是传入自定义tag的函数
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, buildLogString(msg));
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, buildLogString(msg));
        }
    }


    public static void e(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        Log.i(tag, buildLogString(msg));
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, buildLogString(msg));
        }
    }

    private static String buildLogString(String str) {
        StackTraceElement caller = (new Throwable()).fillInStackTrace()
                .getStackTrace()[2];
        return "(" + caller.getFileName() + ":" + caller.getLineNumber() + ")." + caller
                .getMethodName() + "():" + str;
    }
}
