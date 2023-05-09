package com.jenschen.messagehandler;

import com.jenschen.enumeration.SendTypeEnum;
import com.jenschen.messagehandler.impl.EmailMessageHandler;
import com.jenschen.messagehandler.impl.LogMessageHandler;
import com.jenschen.messagehandler.impl.SmsMessageHandler;
import com.jenschen.messagehandler.impl.WechatMessageHandler;

public class MessageHandlerFactory {

    private MessageHandlerFactory(){}

    private static WechatMessageHandler wechatMessageHandler;
    private static SmsMessageHandler smsMessageHandler;
    private static EmailMessageHandler emailMessageHandler;
    private static LogMessageHandler logMessageHandler;

    static {
        wechatMessageHandler = new WechatMessageHandler();
        smsMessageHandler = new SmsMessageHandler();
        emailMessageHandler = new EmailMessageHandler();
        logMessageHandler = new LogMessageHandler();
    }


    public static MessageHandler getMessageHandler(SendTypeEnum sendTypeEnum){
        if(SendTypeEnum.WECHAT.equals(sendTypeEnum)){
            return wechatMessageHandler;
        }else if(SendTypeEnum.SMS.equals(sendTypeEnum)){
            return smsMessageHandler;
        }else if(SendTypeEnum.EMAIL.equals(sendTypeEnum)){
            return emailMessageHandler;
        }

        return logMessageHandler;
    }
}
