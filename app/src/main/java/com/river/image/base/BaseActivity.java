package com.river.image.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2016/9/12.
 */
public abstract class BaseActivity extends AppCompatActivity{
  //布局文件Id
  protected  abstract int getContentViewId();
  //fragment布局Id
  protected  abstract int getFragmentContentId();


  //添加Fragment
  protected void addFragment(BaseFragment fragment){
      if(null!=fragment){
        getSupportFragmentManager().beginTransaction()
            .replace(getFragmentContentId(),fragment,fragment.getClass().getSimpleName())
            .addToBackStack(fragment.getClass().getSimpleName())
            .commitAllowingStateLoss();
      }
  }
  //移除fragment
  protected  void removeFragment()
  {
    if(getSupportFragmentManager().getBackStackEntryCount()>1){
      getSupportFragmentManager().popBackStack();
    }else{
      finish();
    }
  }



  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getAction()==keyCode){
        if(getSupportFragmentManager().getBackStackEntryCount()==1)
        {
          finish();
          return true;
        }
    }
    return super.onKeyDown(keyCode, event);
  }
}
