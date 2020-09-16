package com.ypeng.mediaplaykotlin.logic.util;

/**
 * 消息封装
 * &lt;功能详细描述&gt;
 *
 * @author administrator
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class BaseMessage
{
    //消息ID
    private int messageId = -1;

    //消息信息
    private int messageWhat = -1;

    //消息对象
    private Object messageObject = null;

    /**
     * 构造函数
     *
     * @param messageWhat
     */
    public BaseMessage(int messageWhat)
    {
        this.messageWhat = messageWhat;
    }

    /**
     * 构造函数
     *
     * @param messageWhat
     * @param messageObject
     */
    public BaseMessage(int messageWhat, Object messageObject)
    {
        this.messageWhat = messageWhat;
        this.messageObject = messageObject;
    }

    /**
     * 构造函数
     *
     * @param messageId
     * @param messageWhat
     */
    public BaseMessage(int messageId, int messageWhat)
    {
        this.messageId = messageId;
        this.messageWhat = messageWhat;
    }

    /**
     * 构造函数
     *
     * @param messageId
     * @param messageWhat
     * @param messageObject
     */
    public BaseMessage(int messageId, int messageWhat, Object messageObject)
    {
        this.messageId = messageId;
        this.messageWhat = messageWhat;
        this.messageObject = messageObject;
    }

    //获取消息
    public int getMessageWhat()
    {
        return messageWhat;
    }

    //获取对象
    public Object getMessageObject()
    {
        return messageObject;
    }

    //获取ID
    public int getMessageId()
    {
        return messageId;
    }
}