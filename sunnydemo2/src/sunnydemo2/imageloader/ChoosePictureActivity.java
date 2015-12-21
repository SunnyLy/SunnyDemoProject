package sunnydemo2.imageloader;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.het.common.utils.LogUtils;
import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sunnydemo2.imageloader.adapter.GridviewAdapter;

/**
 * Created by sunny on 2015/12/21.
 * 仿微信选择照片、拍照
 */
public class ChoosePictureActivity extends Activity implements View.OnClickListener{
    public static final String TAG = ChoosePictureActivity.class.getSimpleName();

    private SparseArray<String> mImageList = new SparseArray<>();

    private GridView mGridViewPicture;
    private GridviewAdapter mGridViewAdapter;


    public static void startChoosePictureActivity(Context context) {
        Intent targetIntent = new Intent(context, ChoosePictureActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        getLocalAllPhoto();
        initView();
    }

    /**
     * 获取本地所有照片
     */
    private void getLocalAllPhoto() {

        //ContentProvider与ContentResolver是成配对关系的
        //应用通过ContentProvider向外界提供数据
        //而应用通过ContentResolver从外界获取数据
        //当然，只有请求的路径与提供的路径一样时，才能获取

        //先获取查询参数
        String[] projectItems = {MediaStore.Images.Media._ID
                , MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA};

        //查询条件
        String orderBy = MediaStore.Images.Media.DATE_MODIFIED;

        //获取ContentProvider指定的Uri
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        //开始获取第三方应用共享出来的数据
        SparseArray<String> imges = getContentProvidedByContentProvider(uri,projectItems,orderBy);
        if(imges != null){
            mGridViewAdapter = new GridviewAdapter(this,imges,this);
            mGridViewPicture.setAdapter(mGridViewAdapter);
        }
    }

    /**
     * 获取由ContentProvider共享出来的数据
     * @param uri
     * @param projectItems
     * @param orderBy
     */
    private SparseArray<String > getContentProvidedByContentProvider(Uri uri, String[] projectItems, String orderBy) {
        SparseArray<String > listImage = new SparseArray<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri,projectItems,null,null,orderBy);
        if(cursor == null){
            return listImage;
        }
        while (cursor.moveToNext()){
            listImage.clear();
            for (int i=0;i<projectItems.length;i++){
                listImage.put(i,cursor.getString(i));
                LogUtils.e(projectItems[i]+":"+cursor.getString(i));
            }
            LogUtils.e("============================");
        }

        return listImage;
    }

    private void initView() {
        //mGridViewPicture.set
    }

    @Override
    public void onContentChanged() {
        mGridViewPicture = (GridView) findViewById(R.id.choose_photo_gv);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.take_photo:
                Toast.makeText(this,"拍照",Toast.LENGTH_SHORT).show();
            break;
            case R.id.picture_show:
                Toast.makeText(this,"点击照片",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
