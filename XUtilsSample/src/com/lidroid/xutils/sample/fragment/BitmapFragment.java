package com.lidroid.xutils.sample.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.sample.BitmapHelp;
import com.lidroid.xutils.sample.ImageActivity;
import com.lidroid.xutils.sample.R;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: wyouflf
 * Date: 13-9-14
 * Time: 下午3:35
 */
public class BitmapFragment extends Fragment {

    public static BitmapUtils bitmapUtils;

    private String[] imgSites = {
            "http://image.baidu.com/",
            "http://www.22mm.cc/",
            "http://www.moko.cc/",
            "http://eladies.sina.com.cn/photo/",
            "http://www.youzi4.com/"
    };

    private HashMap<String, Integer> temp = new HashMap<String, Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bitmap_fragment, container, false); // 加载fragment布局
        ViewUtils.inject(this, view); //注入view和事件

        bitmapUtils = BitmapHelp.getBitmapUtils(this.getActivity().getApplicationContext());
        bitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.bitmap);
        bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);

        //bitmapUtils.configMemoryCacheEnabled(false);
        //bitmapUtils.configDiskCacheEnabled(false);

        //bitmapUtils.configDefaultAutoRotation(true);

        //ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
        //        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //animation.setDuration(800);

        // AlphaAnimation 在一些android系统上表现不正常, 造成图片列表中加载部分图片后剩余无法加载, 目前原因不明.
        // 可以模仿下面示例里的fadeInDisplay方法实现一个颜色渐变动画。
        //AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        //animation.setDuration(1000);
        //bitmapUtils.configDefaultImageLoadAnimation(animation);

        // 设置最大宽高, 不设置时更具控件属性自适应.
        bitmapUtils.configDefaultBitmapMaxSize(BitmapCommonUtils.getScreenSize(getActivity()).scaleDown(3));

        // 滑动时加载图片，快速滑动时不加载图片
        //imageListView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));

        imageListAdapter = new ImageListAdapter(inflater.getContext());
        imageListView.setAdapter(imageListAdapter);

        // 加载url请求返回的图片连接给listview
        // 这里只是简单的示例，并非最佳实践，图片较多时，最好上拉加载更多...
        for (String url : imgSites) {
            loadImgList(url);
        }

        /*for (int i = 0; i < 162; i++) {
            imageListAdapter.addSrc("/sdcard/pic/" + i);
        }
        imageListAdapter.notifyDataSetChanged();//通知listview更新数据*/

        return view;
    }

    @OnItemClick(R.id.img_list)
    public void onImageItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.getActivity(), ImageActivity.class);
        intent.putExtra("url", imageListAdapter.getItem(position).toString());
        this.getActivity().startActivity(intent);
    }

    private void loadImgList(String url) {
        new HttpUtils().send(HttpRequest.HttpMethod.GET, url,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        imageListAdapter.addSrc(getImgSrcList(responseInfo.result));
                        imageListAdapter.notifyDataSetChanged();//通知listview更新数据
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                    }
                });
    }

    @ViewInject(R.id.img_list)
    private ListView imageListView;

    private ImageListAdapter imageListAdapter;

    private class ImageListAdapter extends BaseAdapter {

        private Context mContext;
        private final LayoutInflater mInflater;
        private ArrayList<String> imgSrcList;

        public ImageListAdapter(Context context) {
            super();
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
            imgSrcList = new ArrayList<String>();
        }

        public void addSrc(List<String> imgSrcList) {
            this.imgSrcList.addAll(imgSrcList);
        }

        public void addSrc(String imgUrl) {
            this.imgSrcList.add(imgUrl);
        }

        @Override
        public int getCount() {
            return imgSrcList.size();
        }

        @Override
        public Object getItem(int position) {
            return imgSrcList.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            ImageItemHolder holder = null;
            if (view == null) {
                view = mInflater.inflate(R.layout.bitmap_item, null);
                holder = new ImageItemHolder();
                ViewUtils.inject(holder, view);
                view.setTag(holder);
            } else {
                holder = (ImageItemHolder) view.getTag();
            }
            holder.imgPb.setProgress(0);
            bitmapUtils.display(holder.imgItem, imgSrcList.get(position), new CustomBitmapLoadCallBack(holder));
            //bitmapUtils.display((ImageView) view, imgSrcList.get(position), displayConfig);
            //bitmapUtils.display((ImageView) view, imgSrcList.get(position));
            return view;
        }
    }

    private class ImageItemHolder {
        @ViewInject(R.id.img_item)
        private ImageView imgItem;

        @ViewInject(R.id.img_pb)
        private ProgressBar imgPb;
    }

    public class CustomBitmapLoadCallBack extends DefaultBitmapLoadCallBack<ImageView> {
        private final ImageItemHolder holder;

        public CustomBitmapLoadCallBack(ImageItemHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onLoading(ImageView container, String uri, BitmapDisplayConfig config, long total, long current) {
            this.holder.imgPb.setProgress((int) (current * 100 / total));
        }

        @Override
        public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
            //super.onLoadCompleted(container, uri, bitmap, config, from);
            fadeInDisplay(container, bitmap);
            this.holder.imgPb.setProgress(100);
        }
    }

    private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(android.R.color.transparent);

    private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {
        final TransitionDrawable transitionDrawable =
                new TransitionDrawable(new Drawable[]{
                        TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap)
                });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }

    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgSrcList(String htmlStr) {
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*?src=\"http://(.*?).jpg\""; // 图片链接地址
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            String src = m_image.group(1);
            if (src.length() < 100) {
                pics.add("http://" + src + ".jpg");
            }
        }
        return pics;
    }

}
