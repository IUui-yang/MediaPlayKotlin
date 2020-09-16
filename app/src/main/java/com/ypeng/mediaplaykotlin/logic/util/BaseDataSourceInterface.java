package com.ypeng.mediaplaykotlin.logic.util;

/**
 * 通用回调接口
 * &lt;功能详细描述&gt;
 *
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface BaseDataSourceInterface
{
    //添加回调方法
    void addControllerObserver(ControllerObserver observer);

    //删除回调方法
    void removeControllerObserver(ControllerObserver observer);

    //添加消息处理
    void handleMessage(BaseMessage msg, boolean isCanCanceled);

}
