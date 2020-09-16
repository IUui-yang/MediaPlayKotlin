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

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 控制类
 * 功能：
 * 1.提供通用的注册方法
 * 2.提供通用的解注册方法
 * 3.发送消息
 * 4.处理回调
 *
 * @param <T>
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseController<T> implements ControllerObserver, BaseControllerInterface<T>
{
    private BaseDataSourceInterface dataSource;

    protected CopyOnWriteArrayList<T> observerList = new CopyOnWriteArrayList<>();
    //new ArrayList<>();  修改ConcurrentModificationException

    private static int gMessageId = 1;

    //获取ID
    private static synchronized int generateMessageId()
    {
        if (gMessageId == Integer.MAX_VALUE - 1)
        {
            gMessageId = 1;
        }
        else
        {
            gMessageId++;
        }
        return gMessageId;
    }

    //添加回调
    protected final void attachDataSource(BaseDataSourceInterface dataSource)
    {
        this.dataSource = dataSource;
        this.dataSource.addControllerObserver(this);
    }

    @Override
    public final void addObserver(T observer)
    {
        //添加观察者
        if (!observerList.contains(observer))
        {
            observerList.add(observer);
        }
    }

    @Override
    public final void removeObserver(T observer)
    {
        //删除观察者
        if (observerList.contains(observer))
        {
            observerList.remove(observer);
        }
    }

    /**
     * 发送消息
     *
     * @param msg         消息
     * @param isCanCancel 是否取消
     */
    private void sendMessage(BaseMessage msg, boolean isCanCancel)
    {
        if (dataSource != null)
        {
            dataSource.handleMessage(msg, isCanCancel);
        }
    }

    //发送消息
    protected final int sendMessage(int what)
    {
        return sendMessageWithCancel(what, false);
    }

    //发送消息
    private final int sendMessageWithCancel(int what, boolean isCanCancel)
    {
        int messageId = generateMessageId();
        sendMessage(new BaseMessage(messageId, what), isCanCancel);
        return messageId;
    }

    //发送消息
    protected final int sendMessage(int what, Object object)
    {
        return sendMessageWithCancel(what, object, false);
    }

    //发送消息
    protected final int sendMessageWithCancel(int what, Object object, boolean isCanCancel)
    {
        int messageId = generateMessageId();
        sendMessage(new BaseMessage(messageId, what, object), isCanCancel);
        return messageId;
    }
}