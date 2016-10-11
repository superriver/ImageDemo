package com.river.image.module.view.image;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.river.image.R;
import com.river.image.base.AppActivity;
import com.river.image.base.BaseFragment;

/**
 * Created by Administrator on 2016/9/22.
 */

public class GirlActivity extends AppActivity{

  private Unbinder mUnbinder;
  GirlFragment mGirlFragment;

  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @Override protected int getContentViewId() {
    return R.layout.activity_girl;
  }

  @Override protected int getFragmentContentId() {
    return R.id.girl_fragment;
  }

  @Override protected BaseFragment getFirstFragment() {
    mGirlFragment = GirlFragment.getInstance(getIntent().getParcelableArrayListExtra("girl"),getIntent().getIntExtra("current",0));
    return mGirlFragment;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mUnbinder = ButterKnife.bind(this);
    initView();
  }

  private void initView() {
    mToolbar.setTitle("福利");
    setSupportActionBar(mToolbar);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_girl,menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id==R.id.action_save){
      //调用保存的方法
      mGirlFragment.onSave();
      //调用分享的方法
    }else if(id==R.id.action_share){
        mGirlFragment.showShare();
    }
    return true;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
  }
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
      finishActivity();
      return false;
    } else {
      return super.onKeyDown(keyCode, event);
    }

  }

  private void finishActivity() {
    finish();
    overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
  }
}
