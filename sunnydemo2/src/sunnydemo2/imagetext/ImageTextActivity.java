package sunnydemo2.imagetext;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by sunny on 2015/11/26.
 * 测试图文混排
 */
public class ImageTextActivity extends Activity implements View.OnClickListener {

    private Button mBtnLocalPhoto;
    private Button mBtnTakePhoto;
    private EditText mEditText;

    private Uri originalUri;

    private int screeWidth;
    private int screeHeight;
    private int MAXSIZE;

    public static void startImageTextActivity(Context context) {
        Intent targetIntent = new Intent(context, ImageTextActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagetext);
        initParams();
    }

    private void initParams() {
        /** DisplayMetrics获取屏幕信息 */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screeWidth = displayMetrics.widthPixels;
        screeHeight = displayMetrics.heightPixels;

        // 本应用程序最高可用内存是多少
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        MAXSIZE = maxMemory / 8;
    }

    @Override
    public void onContentChanged() {
        mBtnLocalPhoto = (Button) findViewById(R.id.choose_photo);
        mBtnTakePhoto = (Button) findViewById(R.id.take_photo);
        mEditText = (EditText) findViewById(R.id.image_text_eidttext);
        mBtnTakePhoto.setOnClickListener(this);
        mBtnLocalPhoto.setOnClickListener(this);
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public void onClick(View v) {

       int id = v.getId();
        if(id == R.id.choose_photo){
            choosePhoto();
        }else if(id == R.id.take_photo){
            takePhoto();
        }
    }

    private void takePhoto() {
        Intent takePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePhoto,1);
    }

    private void choosePhoto(){
        Intent photoIntent = new Intent(Intent.ACTION_PICK);
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> listResolveInfos = packageManager
                .queryIntentActivities(photoIntent, 0);
        if (listResolveInfos.size() > 0) {
            photoIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*");
            startActivityForResult(photoIntent,
                    0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                originalUri = data.getData();
                if (originalUri != null) {
                    Editable eb = mEditText.getEditableText();
                    // 获得光标所在位置
                    int startPosition = mEditText.getSelectionStart();
                    eb.insert(
                            startPosition,
                            Html.fromHtml("<br/><img src='" + originalUri.toString()
                                    + "'/><br/>", imageGetter, null));
                }
            }else if(requestCode == 1){

                originalUri = data.getData();
                if (originalUri != null) {
                    Editable eb = mEditText.getEditableText();
                    // 获得光标所在位置
                    int startPosition = mEditText.getSelectionStart();
                    eb.insert(
                            startPosition,
                            Html.fromHtml("<br/><img src='" + originalUri.toString()
                                    + "'/><br/>", imageGetter, null));
                }
            }
        }
    }

    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            try {
                Uri uri = Uri.parse(source);
                Bitmap bitmap = getimage(getContentResolver(), uri);
                return getMyDrawable(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    private static Drawable getMyDrawable(Bitmap bitmap) {
        Drawable drawable = new BitmapDrawable(bitmap);

        int imgHeight = drawable.getIntrinsicHeight();
        int imgWidth = drawable.getIntrinsicWidth();

        drawable.setBounds(0, 0, imgWidth, imgHeight);
        return drawable;
    }


    private void insertIntoEdittext(SpannableString bitmapMine) {
        Editable editable = mEditText.getText();
        int start = mEditText.getSelectionStart();
        editable.insert(start, bitmapMine);
        mEditText.setText(editable.toString());
        mEditText.setSelection(mEditText.getText().toString().length());

    }

    private Bitmap getimage(ContentResolver cr, Uri uri) {
        try {
            Bitmap bitmap = null;
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // options.inJustDecodeBounds=true,图片不加载到内存中
            newOpts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(cr.openInputStream(uri), null, newOpts);

            newOpts.inJustDecodeBounds = false;
            int imgWidth = newOpts.outWidth;
            int imgHeight = newOpts.outHeight;
            // 缩放比,1表示不缩放
            int scale = 1;

            if (imgWidth > imgHeight && imgWidth > screeWidth) {
                scale = (int) (imgWidth / screeWidth);
            } else if (imgHeight > imgWidth && imgHeight > screeHeight) {
                scale = (int) (imgHeight / screeHeight);
            }
            newOpts.inSampleSize = scale;// 设置缩放比例
            bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri), null,
                    newOpts);
            return bitmap;
        } catch (Exception e) {
            System.out.println("文件不存在");
            return null;
        }
    }

    private SpannableString getBitmapMine(Bitmap resizeBitmap, Uri originalUri) {
        SpannableString spannableString = null;

        String path = originalUri.getPath();
        spannableString = new SpannableString(path);
        ImageSpan imageSpan = new ImageSpan(this, resizeBitmap);
        spannableString.setSpan(imageSpan, 0, path.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }

    private Bitmap resizeBitmap(Bitmap orginalBitmap, int reqsW, int reqsH) {
        if (orginalBitmap == null || reqsW == 0 || reqsH == 0) return orginalBitmap;
        if (orginalBitmap.getWidth() > reqsW || orginalBitmap.getHeight() > reqsH) {
            float scaleX = new BigDecimal(reqsW).divide(new BigDecimal(orginalBitmap.getWidth()), 4, RoundingMode.DOWN).floatValue();
            float scaleY = new BigDecimal(reqsH).divide(new BigDecimal(orginalBitmap.getHeight()), 4, RoundingMode.DOWN).floatValue();
            Matrix matrix = new Matrix();
            matrix.postScale(scaleX, scaleY);
            orginalBitmap = Bitmap.createBitmap(orginalBitmap, 0, 0, orginalBitmap.getWidth(), orginalBitmap.getHeight(), matrix, true);
        }
        return orginalBitmap;
    }
}
