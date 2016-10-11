package com.river.image.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/12.
 */
public abstract class BaseFragment extends Fragment{
  protected BaseActivity mActivity;
  protected abstract  void initView(View view, Bundle savedInstanceState);
  protected abstract int getLayoutId();

  private BaseActivity getHoldActvity(){
    return  mActivity;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    this.mActivity = (BaseActivity) context;
  }

  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutId(),container,false);
    initView(view,savedInstanceState);
    return view;

  }
  @Override public void onDestroy() {
    super.onDestroy();
  }
}
