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

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制实现
 * 功能：
 * 1. 添加回调
 * 2. 删除回调
 * 3. 异步处理任务
 * 4. 消息处理回调
 * 5. 消息处理
 *
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseDataSource implements BaseDataSourceInterface
{
    //Context上下文
    private Context context;

    //回调列表
    private final List<ControllerObserver> observerList = new ArrayList<>();

    //任务列表
    private final SparseArray<List<BaseAsyncTask<Object>>> taskMap = new SparseArray<>();

    //函数
    public BaseDataSource(Context context)
    {
        this.context = context;
    }

    @Override
    public final void addControllerObserver(ControllerObserver observer)
    {
        //添加回调
        if (!observerList.contains(observer))
        {
            observerList.add(observer);
        }
    }

    @Override
    public final void removeControllerObserver(ControllerObserver observer)
    {
        //删除回调
        if (observerList.contains(observer))
        {
            observerList.remove(observer);
        }
    }

    /**
     * 取消任务
     *
     * @param what 任务ID
     */
    private void cancelTask(int what)
    {
        List<BaseAsyncTask<Object>> tasks = taskMap.get(what);
        if (tasks != null)
        {
            for (BaseAsyncTask<Object> task : tasks)
            {
                if (task != null && task.isCanCancel())
                {
                    Log.i("BaseDataSource", "cancel last task :" + what);
                    task.cancel(true);
                }
            }
        }
    }

    /**
     * 添加任务
     *
     * @param what ID
     * @param task 任务
     */
    private void addTask(int what, BaseAsyncTask<Object> task)
    {
        List<BaseAsyncTask<Object>> tasks = taskMap.get(what);
        if (tasks == null)
        {
            tasks = new ArrayList<>();
            tasks.add(task);
            taskMap.put(what, tasks);
        }
        else
        {
            tasks.add(task);
        }
    }

    /**
     * 移除任务
     *
     * @param what ID
     * @param task 任务
     */
    private void removeTask(int what, BaseAsyncTask<Object> task)
    {
        List<BaseAsyncTask<Object>> tasks = taskMap.get(what);
        if (tasks != null)
        {
            tasks.remove(task);
            if (tasks.isEmpty())
            {
                taskMap.remove(what);
            }
        }
    }

    @Override
    public final void handleMessage(final BaseMessage msg, final boolean isCanCancel)
    {
        //消息处理
        // if still has the same task ,cancel it
        final int what = msg.getMessageWhat();
        final int id = msg.getMessageId();
        cancelTask(what);
        BaseAsyncTask<Object> task = new BaseAsyncTask<Object>()
        {
            @Override
            public Object call() throws InterruptedException
            {
                //是否取消
                if (isCanceled())
                {
                    throw new InterruptedException("task id " + id + " ,what " + what + " is canceled!");
                }
                Object obj = fetchData(msg);
                if (isCanceled())
                {
                    throw new InterruptedException("task id " + id + " ,what " + what + " is canceled!");
                }
                return obj;
            }

            @Override
            protected void onSuccess(Object object)
            {
                //成功回调
                if (object != null)
                {
                    BaseMessage msgResult = new BaseMessage(msg.getMessageId(), msg.getMessageWhat(), object);
                    receiveMessage(msgResult);
                }
            }

            @Override
            protected void onInterrupted(Exception e)
            {
                //打断
                Log.i("BaseDataSource", "onInterrupted task id:" + id);
                super.onInterrupted(e);
            }

            @Override
            protected void onException(Exception e) throws RuntimeException
            {
                //异常
                Log.i("BaseDataSource", "onException task id:" + id);
                super.onException(e);
            }

            @Override
            protected void onFinally() throws RuntimeException
            {
                // Log.i("BaseDataSource", "onFinally task id:" + id);
                super.onFinally();
                removeTask(what, this);
            }

        };
        task.setCanCancel(isCanCancel);
        task.execute();
        addTask(what, task);
    }

    /**
     * 消息回调处理
     *
     * @param msgResult 内容
     */
    protected final void receiveMessage(BaseMessage msgResult)
    {
        for (ControllerObserver observer : observerList)
        {
            observer.onDataChanged(msgResult);
        }
    }

    public abstract Object fetchData(BaseMessage msg);
}