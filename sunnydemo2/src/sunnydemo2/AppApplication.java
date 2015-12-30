package sunnydemo2;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.het.frescosupport.FrescoManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by sunny on 2015/11/25.
 * Annotion:
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initParams();
    }

    private void initParams() {
        initImageLoader();
        initFresco();
    }

    private void initFresco() {

        FrescoManager.getInstance(this).init();
    }

    private void initImageLoader() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.drawable.personal_default_icon)
                // .showImageForEmptyUri(R.drawable.personal_default_icon)
                // .showImageOnFail(R.drawable.personal_default_icon)
                .cacheOnDisc().cacheInMemory()
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 8L);
        MemoryCacheAware<String, Bitmap> memoryCache = new LRULimitedMemoryCache(
                memoryCacheSize);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).memoryCache(memoryCache)
                .defaultDisplayImageOptions(defaultOptions)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        // .writeDebugLogs()
                .tasksProcessingOrder(QueueProcessingType.FIFO).build();

        if(!imageLoader.isInited())//只有当它没有初始化时，才去进行init操作
        imageLoader.init(config);

    }
}
