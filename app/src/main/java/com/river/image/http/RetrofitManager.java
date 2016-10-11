package com.river.image.http;

import com.river.image.bean.GirlsBean;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/9.
 */
public class RetrofitManager {
  private static ImageService mService;
  private volatile static OkHttpClient mOkHttpClient;
  private static RetrofitManager instance=new RetrofitManager();
  public static RetrofitManager getInstance(){
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConfig.GANHUO_API)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    mService=retrofit.create(ImageService.class);
    return instance;
  }

  private static OkHttpClient getOkHttpClient(){
    if(mOkHttpClient==null){
      synchronized (RetrofitManager.class){
        if(mOkHttpClient==null){
          mOkHttpClient = new OkHttpClient.Builder()
              .connectTimeout(3, TimeUnit.SECONDS)
              .writeTimeout(3,TimeUnit.SECONDS)
              .readTimeout(3,TimeUnit.SECONDS)
              .build();
        }
      }
    }
    return  mOkHttpClient;
  }

  public  Observable<GirlsBean> getImageInfoObservable(String type,int count,int page){
    return mService.getImageInfo(type,count,page).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
  }
}
