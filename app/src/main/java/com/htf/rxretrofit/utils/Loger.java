package com.htf.rxretrofit.utils;

import android.util.Log;

import com.htf.rxretrofit.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loger {
	
    private static final String TAG = BuildConfig.APPLICATION_ID;

    /**
     * 打印字符超过4K会直接截断
     * @param msg
     */
    public static void d(String msg) {
    	if(BuildConfig.DEBUG) {
            msg = msg.trim();
            int index = 0;
            int maxLength = 4000;
            String sub;
            while (index < msg.length()) {
                // java的字符不允许指定超过总的长度end  
                if (msg.length() <= index + maxLength) {
                    sub = msg.substring(index);
                } else {
                    sub = msg.substring(index, index + maxLength);
                }

                index += maxLength;
                Log.d(TAG, sub);
            }
        }
    }

    public static void i(String msg) {
    	if(BuildConfig.DEBUG)
        Log.i(TAG, msg);
    }

    public static void e(String msg) {
    	if(BuildConfig.DEBUG)
        Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
    	if(BuildConfig.DEBUG)
        Log.e(TAG, msg, tr);
    }

    public static void v(String msg) {
    	if(BuildConfig.DEBUG)
        Log.v(TAG, msg);
    }

    public static void w(String msg) {
    	if(BuildConfig.DEBUG)
        Log.w(TAG, msg);
    }

    public static void w(String msg, Throwable tr) {
    	if(BuildConfig.DEBUG)
        Log.w(TAG, msg, tr);
    }

    /**
     * 读取打印的打印错误日志
     * @author Dannny
     * @throws IOException cmd error
     */
    public static String readErrorLog() {
        StringBuffer sb = new StringBuffer();
        //array of String by CMD
        ArrayList<String> cmdLine = new ArrayList<String>();
        cmdLine.add("logcat");
        cmdLine.add("-d");
        cmdLine.add("-s");
        cmdLine.add(TAG);//打印的TAG过滤
        //run the cmd
        Process exec = null;
        try {
            exec = Runtime.getRuntime().exec(cmdLine.toArray(new String[cmdLine.size()]));
            InputStream inputStream = exec.getInputStream();
            InputStreamReader isReader = new InputStreamReader(inputStream);//装饰器模式
            BufferedReader reader = new BufferedReader(isReader);//缓存reader
            String str = null;
            while((str = reader.readLine())!=null){
                sb.append(str);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
