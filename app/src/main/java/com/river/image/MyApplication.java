package com.river.image;

import android.app.Application;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/9/26.
 */

public class MyApplication extends Application {
  private static MyApplication mApplication;

  @Override public void onCreate() {
    super.onCreate();
    ShareSDK.initSDK(this,"17804404bcf7e");
    mApplication = this;
  }
  public static MyApplication getInstance(){
    return  mApplication;
  }
}
