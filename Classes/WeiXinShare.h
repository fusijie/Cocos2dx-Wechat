//
//  WeiXinShare.h
//  WeiXinShare
//
//  Created by Jacky on 8/5/14.
//
//

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include <jni.h>
#include "platform/android/jni/JniHelper.h"
#include <cocos2d.h>
#endif

using namespace cocos2d;

class WeiXinShare
{
public:
    WeiXinShare();
    virtual ~WeiXinShare();
    static void sendToFriend();
    static void sendToTimeLine();
};
