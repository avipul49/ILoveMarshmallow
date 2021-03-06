package com.zapoos.main.zappos.utils;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by vipulmittal on 13/08/15.
 */
public class ImageLoaderUtil {
    public static void displayImage(Context context, String url, ImageView imageView) {
        if (!ImageLoader.getInstance().isInited()) {
            ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context)
                    .memoryCacheSize(41943040)
                    .threadPoolSize(10)
                    .build();
            ImageLoader.getInstance().init(imageLoaderConfiguration);
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoader.getInstance().displayImage(url, imageView, options);
    }
}
