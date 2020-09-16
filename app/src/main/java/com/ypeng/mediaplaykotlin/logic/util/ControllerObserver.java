package com.ypeng.mediaplaykotlin.logic.util;

/**
 * 接口
 * &lt;功能详细描述&gt;
 *
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ControllerObserver
{
    //回调方法
    void onDataChanged(BaseMessage msg);
}