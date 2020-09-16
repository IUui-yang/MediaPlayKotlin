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

import android.util.Log;

/**
 * Log日志工具类, 测试时才打印日志
 * &lt;功能详细描述&gt;
 *
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LogUtil
{
    private static final String PLUS = "--->";

    //打印i级别日志
    protected static void i(String module, String tag, String msg)
    {
        // if (isLogOut())
        Log.i(module, tag + PLUS + msg);
    }

    //打印d级别日志
    protected static void d(String module, String tag, String msg)
    {
        // if (isLogOut())
        Log.d(module, tag + PLUS + msg);
    }

    //打印w级别日志
    protected static void w(String module, String tag, String msg)
    {
        //if (isLogOut())
        Log.w(module, tag + PLUS + msg);
    }

    //打印e级别日志
    protected static void e(String module, String tag, String msg)
    {
        // if (isLogOut())
        Log.e(module, tag + PLUS + msg);
    }

    //打印v级别日志
    protected static void v(String module, String tag, String msg)
    {
        // if (isLogOut())
        Log.v(module, tag + PLUS + msg);
    }

    //是否打印日志
    private static boolean isLogOut()
    {
        return true;
    }
}
