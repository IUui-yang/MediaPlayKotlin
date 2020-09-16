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

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池管理
 * &lt;功能详细描述&gt;
 *
 * @param <T>
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseAsyncTask<T> extends SafeAsyncTask<T>
{
    //线程池数量
    private static int threadNum = 8;

    //是否可以取消
    private boolean isCanCancel = true;

    //状态
    private boolean isCanceled = false;

    private static ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

    /**
     * 构造函数
     */
    protected BaseAsyncTask()
    {
    }

    @Override
    public final void execute()
    {
        //执行
        executor(executorService);
        super.execute();
    }

    @Override
    public final SafeAsyncTask<T> executor(Executor executor)
    {
        //执行
        return super.executor(executorService);
    }

    @Override
    public final Executor executor()
    {
        return executorService;
    }

    /**
     * 是否取消
     *
     * @return
     */
    public boolean isCanceled()
    {
        return isCanceled;
    }

    @Override
    public final boolean cancel(boolean mayInterruptIfRunning)
    {
        //取消
        if (future == null)
        {
            return false;
        }
        isCanceled = true;
        return super.cancel(mayInterruptIfRunning);
    }

    /**
     * 是否允许取消
     *
     * @return
     */
    public boolean isCanCancel()
    {
        return isCanCancel;
    }

    /**
     * 设置是否允许取消
     *
     * @param canCancel
     */
    public void setCanCancel(boolean canCancel)
    {
        isCanCancel = canCancel;
    }
}