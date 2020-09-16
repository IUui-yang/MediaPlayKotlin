package com.ypeng.mediaplaykotlin.logic.util;
/*
 * Copyright (C) Skypine
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *---
 *      http://www.apache.org/licenses/LICENSE-2.0
 *---
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;


import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Task工具类
 * 功能：
 * 1. 判断应用是否前台显示
 * 2.判断界面是否前台显示
 *
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RunningTaskUtil {
    private static LogTrace logTrace = new LogTrace("Common", "RunningTaskUtil");

    /**
     * 判断应用是否前台运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppForeground(Context context, String packageName) {
        boolean result = false;
        ActivityManager.RunningTaskInfo topTask = getTopTask(context);
        if (topTask != null) {
            ComponentName topActivity = topTask.topActivity;
            if (topActivity != null) {
                String topPackageName = topActivity.getPackageName();
                if (topPackageName.equals(packageName)) {
                    return true;
                }
            }
        }
        logTrace.d("isAppForeground", "packageName=" + packageName + "; result=" + result);
        return result;
    }

    /**
     * 判断界面是否前台运行
     *
     * @param context
     * @param activityName
     * @return
     */
    public static boolean isActivityForeground(Context context, String activityName) {
        boolean result = false;
        ActivityManager.RunningTaskInfo topTask = getTopTask(context);
        if (topTask != null) {
            ComponentName topActivity = topTask.topActivity;
            if (topActivity != null) {
                String topActivityName = topActivity.getClassName();
                if (topActivityName.equals(activityName)) {
                    return true;
                }
            }
        }
        logTrace.d("isActivityForeground", "activityName=" + activityName + "; result=" + result);
        return result;
    }

    /**
     * 获取运行中应用
     *
     * @param context
     * @param count
     * @return
     */
    public static List<ActivityManager.RunningTaskInfo> getRunningTasks(Context context, int count) {
        logTrace.d("getRunningTasks", "count:" + count);
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = am.getRunningTasks(count);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfos) {
            //Log.i(TAG, "getRunningTask--->id:" + runningTaskInfo.id);
            // Log.i(TAG, "getRunningTask--->activity num:" + runningTaskInfo.numActivities);
            logTrace.d("getRunningTask", "top activity:" + runningTaskInfo);
            if (runningTaskInfo.id == -1) {
                logTrace.d("getRunningTask", "removed");
            }
        }
        return runningTaskInfos;
    }

    /**
     * 获取最上层应用
     *
     * @param context
     * @return
     */
    public static ActivityManager.RunningTaskInfo getTopTask(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = getRunningTasks(context, 1);
        ActivityManager.RunningTaskInfo topTask = null;
        if (runningTaskInfos != null && !runningTaskInfos.isEmpty()) {
            topTask = runningTaskInfos.get(0);
            if (topTask != null) {
                logTrace.d("getTopTask",
                        topTask.topActivity.getPackageName() + " ," + topTask.topActivity.getClassName());
            }
        }
        return topTask;
    }
}