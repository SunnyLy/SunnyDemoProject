package sunnydemo2.ad.ui.newproperties;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunny on 2015/11/23.
 * Annotion:
 */
public class NewPropertiesActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView mListviewNewproperties;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String,String >> mDatas = new ArrayList<>();
    private String[] mFrom;
    private int[] mTo;

    public static void startNewPropertiesActivity(Context context){
        Intent targetIntent = new Intent(context,NewPropertiesActivity.class);
        context.startActivity(targetIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newproperties);

        initParams();
    }

    private void initParams() {
        mDatas.clear();
        String[] names = getResources().getStringArray(R.array.new_properties);
        mFrom = new String[]{"text"};
        if(names!= null && names.length > 0){

            for (int i=0;i<names.length;i++){
                Map<String ,String > data = new HashMap<>();
                data.put("text",names[i]);
                mDatas.add(data);
            }
        }
        mTo = new int[]{R.id.item_btn};

        mSimpleAdapter = new SimpleAdapter(this, mDatas, R.layout.listview_item, mFrom, mTo);
        mListviewNewproperties.setAdapter(mSimpleAdapter);
    }

    @Override
    public void onContentChanged() {
        mListviewNewproperties = (ListView) findViewById(R.id.listivew_newproperties);
        mListviewNewproperties.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                PullToRefreshLayout.startPullToRefreshLayout(this);
                break;
        }

    }
}
