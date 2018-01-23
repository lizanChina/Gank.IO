package com.sunfusheng.gank.http;

import android.support.annotation.NonNull;

import com.sunfusheng.gank.MainApplication;
import com.sunfusheng.gank.util.Util;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author by sunfusheng on 2017/2/3.
 *
 * get方式缓存拦截器
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        if (!Util.isNetworkAvailable(MainApplication.context)) {
            // 没网强制从缓存读取
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);

        if (Util.isNetworkAvailable(MainApplication.context)) {
            // 有网时候读接口上的@Headers里的配置
            String cacheControl = request.cacheControl().toString();
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 30; // 没网一个月后失效
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }

}
