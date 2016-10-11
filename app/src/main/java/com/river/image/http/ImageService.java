package com.river.image.http;

import com.river.image.bean.GirlsBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/9.
 */
public interface ImageService {
  @GET("api/data/{type}/{count}/{page}")
  Observable<GirlsBean> getImageInfo(@Path("type") String type,@Path("count") int count,@Path("page") int page);
}
