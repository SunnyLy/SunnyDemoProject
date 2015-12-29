package sunnydemo2.imageloader;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sunnydemo2.imageloader.adapter.FolderAdapter;
import sunnydemo2.imageloader.adapter.GridviewAdapter;
import sunnydemo2.imageloader.adapter.ImageGridAdapter;
import sunnydemo2.imageloader.model.Folder;
import sunnydemo2.imageloader.model.LocalImage;

/**
 * Created by sunny on 2015/12/21.
 * 仿微信选择照片、拍照
 */
public class ChoosePictureActivity extends Activity implements View.OnClickListener {
    public static final String TAG = ChoosePictureActivity.class.getSimpleName();

    private SparseArray<String> mImageList = new SparseArray<>();

    private GridView mGridViewPicture;
    private GridviewAdapter mGridViewAdapter;

    private static final int LOADER_ALL = 0x0;//加载全部
    private static final int LOADER_CATEGORY = 0x1;//根据文件夹加载

    private boolean hasFolderGened = false;
    private boolean mIsShowCamera = false;

    private ImageGridAdapter mImageAdapter;
    private FolderAdapter mFolderAdapter;


    // 结果数据
    private ArrayList<String> resultList = new ArrayList<>();
    // 文件夹数据
    private ArrayList<Folder> mResultFolder = new ArrayList<>();


    public static void startChoosePictureActivity(Context context) {
        Intent targetIntent = new Intent(context, ChoosePictureActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        //getLocalAllPhoto();

        mImageAdapter = new ImageGridAdapter(this,true);
        mFolderAdapter = new FolderAdapter(this);
        mGridViewPicture.setAdapter(mImageAdapter);
        getLoaderManager().initLoader(LOADER_ALL,null,mLoaderCallback);
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
        List<String> imges = getContentProvidedByContentProvider(uri, projectItems, orderBy);
        if (imges != null) {
            mGridViewAdapter = new GridviewAdapter(this, imges, this);
            mGridViewPicture.setAdapter(mGridViewAdapter);
            mGridViewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取由ContentProvider共享出来的数据
     *
     * @param uri
     * @param projectItems
     * @param orderBy
     */
    private List<String> getContentProvidedByContentProvider(Uri uri, String[] projectItems, String orderBy) {
        List<String> listImage = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
       /* Cursor cursor = contentResolver.query(uri, projectItems, null, null, orderBy);
        if (cursor == null) {
            return listImage;
        }
        int i = 0;
        listImage.clear();
        while (cursor.moveToNext()) {
            listImage.add(cursor.getString(2));
            LogUtils.e(i + ":" + cursor.getString(2));
            LogUtils.e("============================");
            i++;
        }*/

        //只查询jpeg与png的图片
        Cursor mCursor = contentResolver.query(uri,null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
        +MediaStore.Images.Media.MIME_TYPE + "=? or "+
                MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg","image/jpg","image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);
        if(mCursor == null){
            return listImage;
        }

        listImage.clear();
        while (mCursor.moveToNext()){
            //获取图片的路径
            String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
            LogUtils.e("图片名称:" + path);
            LogUtils.e("============================");
            //获取该图片的父路径名
            String parentName = new File(path).getParentFile().getName();
            if(!listImage.contains(parentName)){
                listImage.add(path);
            }
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

        switch (v.getId()) {
            case R.id.take_photo:
                Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
                break;
            case R.id.picture_show:
                Toast.makeText(this, "点击照片", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID };

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if(id == LOADER_ALL) {
                CursorLoader cursorLoader = new CursorLoader(ChoosePictureActivity.this,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        null, null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            }else if(id == LOADER_CATEGORY){
                CursorLoader cursorLoader = new CursorLoader(ChoosePictureActivity.this,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        IMAGE_PROJECTION[0]+" like '%"+args.getString("path")+"%'", null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            }

            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                List<LocalImage> images = new ArrayList<>();
                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    do{
                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                        LocalImage image = new LocalImage(path, name, dateTime);
                        images.add(image);
                        if( !hasFolderGened ) {
                            // 获取文件夹名称
                            File imageFile = new File(path);
                            File folderFile = imageFile.getParentFile();
                            Folder folder = new Folder();
                            folder.name = folderFile.getName();
                            folder.path = folderFile.getAbsolutePath();
                            folder.cover = image;
                            if (!mResultFolder.contains(folder)) {
                                List<LocalImage> imageList = new ArrayList<>();
                                imageList.add(image);
                                folder.images = imageList;
                                mResultFolder.add(folder);
                            } else {
                                // 更新
                                Folder f = mResultFolder.get(mResultFolder.indexOf(folder));
                                f.images.add(image);
                            }
                        }

                    }while(data.moveToNext());

                    mImageAdapter.setData(images);


                    // 设定默认选择
                    if( resultList != null && resultList.size()>0){
                        mImageAdapter.setDefaultSelected(resultList);
                    }

                    mFolderAdapter.setData(mResultFolder);
                    hasFolderGened = true;

                }
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
