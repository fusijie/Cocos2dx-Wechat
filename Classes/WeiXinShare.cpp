//
//  WeiXinShare.cpp
//  WeiXinShare
//
//  Created by Jacky on 8/5/14.
//
//

#include "WeiXinShare.h"

WeiXinShare::WeiXinShare(){}
WeiXinShare::~WeiXinShare(){}

void WeiXinShare::sendToFriend()
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID) //判断当前是否为Android平台
    JniMethodInfo minfo;
    
    bool isHave = JniHelper::getStaticMethodInfo(minfo,"org/cocos2dx/cpp/AppActivity","sendMsgToFriend", "()V");
    
    if (!isHave) {
        log("jni:sendMsgToFriend is null");
    }else{
        //调用此函数
        minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID);
    }
#endif
}

void WeiXinShare::sendToTimeLine()
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID) //判断当前是否为Android平台
    JniMethodInfo minfo;
    
    bool isHave = JniHelper::getStaticMethodInfo(minfo,"org/cocos2dx/cpp/AppActivity","sendMsgToTimeLine", "()V");
    
    if (!isHave) {
        log("jni:sendMsgToTimeLine is null");
    }else{
        //调用此函数
        minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID);
    }
#endif
}