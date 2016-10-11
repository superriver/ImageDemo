package com.river.image.module.view.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.river.image.R;
import com.river.image.base.AppActivity;
import com.river.image.base.BaseFragment;

/**
 * Created by Administrator on 2016/9/12.
 */
public class HomeActivity extends AppActivity {
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.fab)
  FloatingActionButton mFab;
  @Override protected int getContentViewId() {
    return R.layout.activity_main;
  }

  @Override protected int getFragmentContentId() {
    return R.id.image_fragment;
  }

  @Override protected BaseFragment getFirstFragment() {
    return ImageFragment.getInstance();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    initView();
  }

  private void initView() {
    mToolbar.setTitle("Girls");
    setSupportActionBar(mToolbar);
  }
}
