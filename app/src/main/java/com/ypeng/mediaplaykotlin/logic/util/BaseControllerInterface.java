package com.ypeng.mediaplaykotlin.logic.util;

/**
 * &lt;一句话功能简述&gt;
 * 接口
 *
 * @param <T>
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface BaseControllerInterface<T>
{
    void addObserver(T observer);

    void removeObserver(T observer);

}
