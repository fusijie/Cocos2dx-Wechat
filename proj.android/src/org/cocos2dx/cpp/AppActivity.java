/****************************************************************************
Copyright (c) 2008-2010 Ricardo Quesada
Copyright (c) 2010-2012 cocos2d-x.org
Copyright (c) 2011      Zynga Inc.
Copyright (c) 2013-2014 Chukong Technologies Inc.
 
http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
****************************************************************************/
package org.cocos2dx.cpp;

import org.cocos2dx.lib.Cocos2dxActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;

import com.fusijie.cocos2dxwechat.R;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class AppActivity extends Cocos2dxActivity {
	
	private static final String APP_ID = "wx5ae65380cf0ed26f";
	
	private static IWXAPI api;
	
	private static AppActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		instance = this;
		regToWX();
	}
	
	private void regToWX(){
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		api.registerApp(APP_ID);
	}
	
    public static void sendMsgToFriend(){
    	
    	System.out.println("sendMsgToFriend");
    	if(api.openWXApp())
    	{
    		WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = "http://www.baidu.com";
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = "Tittle";
            msg.description = "Description";
            
            Bitmap thumb = BitmapFactory.decodeResource(instance.getResources(), R.drawable.icon);
            msg.thumbData = Util.bmpToByteArray(thumb, true);
             
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = SendMessageToWX.Req.WXSceneSession;
            api.sendReq(req);
    	}
    	else
    	{
    		 Toast.makeText(instance, "未安装微信", Toast.LENGTH_SHORT).show();
    	}
    }
    
    public static void sendMsgToTimeLine(){
    	
    	System.out.println("sendMsgToTimeLine");
    	if(api.openWXApp())
    	{
        	if(api.getWXAppSupportAPI() >= 0x21020001)
        	{        		
        		WXWebpageObject webpage = new WXWebpageObject();
                webpage.webpageUrl = "http://www.fusijie.com";
                WXMediaMessage msg = new WXMediaMessage(webpage);
                msg.title = "Tittle";
                msg.description = "Description";
                
                Bitmap thumb = BitmapFactory.decodeResource(instance.getResources(), R.drawable.icon);
                msg.thumbData = Util.bmpToByteArray(thumb, true);
                 
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                api.sendReq(req);
        	}
        	else{
        		Toast.makeText(instance, "微信版本过低", Toast.LENGTH_SHORT).show();
        	}
        		
    	}
    	else
    	{
    		 Toast.makeText(instance, "未安装微信", Toast.LENGTH_SHORT).show();
    	}
    }
    
	private static String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
}
