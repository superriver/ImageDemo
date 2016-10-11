package com.river.image.module.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewStub;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.river.image.ImageContract;
import com.river.image.R;
import com.river.image.base.BaseFragment;
import com.river.image.bean.GirlsBean;
import com.river.image.module.presenter.IImagePresenterImpl;
import com.river.image.module.view.image.GirlActivity;
import com.socks.library.KLog;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class ImageFragment extends BaseFragment implements ImageContract.View,
    android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{
  private IImagePresenterImpl mPresenter;
  private Unbinder unbinder;
  ArrayList<GirlsBean.ResultsEntity> dataList;
  @BindView(R.id.image_recycler_view) EasyRecyclerView mRecyclerView;
  @BindView(R.id.network_error_layout) ViewStub mNetworkErrorLayout;
  private View networkErrorView;
  private ImagesAdapter mImagesAdapter;
  private int page = 1;
  private int size = 20;
  public static ImageFragment getInstance() {
    return new ImageFragment();
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {
    unbinder = ButterKnife.bind(this, view);
    mPresenter = new IImagePresenterImpl(this);
    initRecyclerView();
    mPresenter.start();
  }

  private void initRecyclerView() {
    dataList = new ArrayList<>();
    StaggeredGridLayoutManager gridlayoutManager =
        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(gridlayoutManager);
    mImagesAdapter = new ImagesAdapter(getContext());
    mRecyclerView.setAdapterWithProgress(mImagesAdapter);
    mImagesAdapter.setMore(R.layout.load_more_layout,this);
    mImagesAdapter.setNoMore(R.layout.no_more_layout);
    mImagesAdapter.setError(R.layout.error_layout);
    mImagesAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
      @Override public void onItemClick(int position) {
        Intent intent = new Intent(mActivity, GirlActivity.class);
        intent.putParcelableArrayListExtra("girl", dataList);
        intent.putExtra("current", position);
        startActivity(intent);
      }
    });
  }
  @Override protected int getLayoutId() {
    return R.layout.fragment_home;
  }

  @Override public void refresh(List<GirlsBean.ResultsEntity> datas) {
    dataList.clear();
    dataList.addAll(datas);
    mImagesAdapter.clear();
    mImagesAdapter.addAll(datas);
  }

  @Override public void load(List<GirlsBean.ResultsEntity> datas) {
    dataList.addAll(datas);
    mImagesAdapter.addAll(datas);
  }

  @Override public void showError() {
    mRecyclerView.showError();
    if(null!=networkErrorView){
      networkErrorView.setVisibility(View.VISIBLE);
      return;
    }
    networkErrorView = mNetworkErrorLayout.inflate();

  }

  @Override public void showNormal() {
    if(null!=networkErrorView){
      networkErrorView.setVisibility(View.GONE);
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  //加载更多
  @Override public void onLoadMore() {
    KLog.d("TAG",dataList.size()+"");
    if(dataList.size()%20==0){
      page++;
      mPresenter.getGirls(page,size,false);
    }
  }

  //刷新
  @Override public void onRefresh() {
    mPresenter.getGirls(1,size,true);
    page=1;
  }
}
