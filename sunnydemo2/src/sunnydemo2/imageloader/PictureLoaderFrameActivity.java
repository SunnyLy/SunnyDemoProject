package sunnydemo2.imageloader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类主要用来验证图片加载框架ImageLoader与Fresco,glide等
 * 的优缺点，实现原理
 * Created by sunny on 2015/11/25.
 */
public class PictureLoaderFrameActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, String>> mDatas = new ArrayList<>();
    private String[] mFrom;
    private int[] mTo;

    public static void startPictureLoaderFrameActivity(Context context) {
        Intent targetIntent = new Intent(context, PictureLoaderFrameActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        TextView start_task = (TextView) findViewById(R.id.start_task);
        start_task.setText("StartActivity所在TaskId:" + getTaskId());
    }

    @Override
    public void onContentChanged() {
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        initDatas();


        mSimpleAdapter = new SimpleAdapter(this, mDatas, R.layout.listview_item, mFrom, mTo);
        mListView.setAdapter(mSimpleAdapter);

    }

    private void initDatas() {
        mDatas.clear();
        String[] names = getResources().getStringArray(R.array.image_loader);
        mFrom = new String[]{"text"};
        for (int i = 0; i < names.length; i++) {
            Map<String, String> data = new HashMap<>();
            data.put("text", names[i]);
            mDatas.add(data);
        }

        mTo = new int[]{R.id.item_btn};
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                ImageLoaderActivity.startImageLoaderActivity(PictureLoaderFrameActivity.this);
                break;
            case 1:
                FrescoActivity.startFrescoActivity(this);
                break;
            case 2:
                GlideActivity.startGlideActivity(this);
                break;
            case 3:
                SunnyImageLoaderActivity.startSunnyImageLoaderActivity(this);
                break;
            case 4:
                ChoosePictureActivity.startChoosePictureActivity(this);
                break;
        }

    }
}
