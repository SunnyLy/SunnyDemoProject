package sunnydemo2.androidl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/28.
 * Palette:Android5.0新特性
 * 用于获取bitmap的颜色
 * 注：由于android设备对获取图片颜色是个耗时操作，
 * 所以用Palette得异步获取
 */
public class PaletteActivity extends Activity {

    public static final String TAG = PaletteActivity.class.getSimpleName();

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;

    public static void startPaletteActivity(Context context){
        Intent targetIntent = new Intent(context,PaletteActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        mTextView1 = (TextView) findViewById(R.id.palette_text);
        mTextView2 = (TextView) findViewById(R.id.palette_text1);
        mTextView3 = (TextView) findViewById(R.id.palette_text2);
        mTextView4 = (TextView) findViewById(R.id.palette_text3);
        mTextView5 = (TextView) findViewById(R.id.palette_text4);
        mTextView6 = (TextView) findViewById(R.id.palette_text5);

        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.pager2);

        getBitmapBgColor(bm);
    }

    private void getBitmapBgColor(Bitmap bm) {

        if(bm != null){
            Palette palette = Palette.generate(bm);
            Palette.Swatch swatch = palette.getVibrantSwatch();
            Palette.Swatch swatch1 = palette.getDarkVibrantSwatch();
            Palette.Swatch swatch2 = palette.getLightVibrantSwatch();
            Palette.Swatch swatch3 = palette.getMutedSwatch();
            Palette.Swatch swatch4 = palette.getDarkMutedSwatch();
            Palette.Swatch swatch5 = palette.getLightMutedSwatch();
            if(swatch != null){
                int contentcolor1 = swatch.getBodyTextColor();//内容颜色
                int titlecolor1 = swatch.getTitleTextColor();//标题颜色

                int contentColor2 = swatch1.getBodyTextColor();
                int titleColor2 = swatch1.getTitleTextColor();

                int contentColor3 = swatch2==null?swatch.getBodyTextColor():swatch2.getBodyTextColor();
                int titleColor3 = swatch2==null?swatch.getTitleTextColor():swatch2.getTitleTextColor();

                int contentColor4 = swatch3.getBodyTextColor();
                int titleColor4 = swatch3.getTitleTextColor();

                int contentColor5 = swatch4.getBodyTextColor();
                int titleColor5 = swatch4.getTitleTextColor();

                int contentColor6 = swatch5.getBodyTextColor();
                int titleColor6 = swatch5.getTitleTextColor();

                mTextView1.setTextColor(contentcolor1);
                mTextView1.setBackgroundColor(swatch.getRgb());

                mTextView2.setTextColor(contentColor2);
                mTextView2.setBackgroundColor(titleColor2);

                mTextView3.setTextColor(contentColor3);
                mTextView3.setBackgroundColor(titleColor3);

                mTextView4.setTextColor(contentColor4);
                mTextView4.setBackgroundColor(titleColor4);

                mTextView5.setTextColor(contentColor5);
                mTextView5.setBackgroundColor(titleColor5);

                mTextView6.setTextColor(contentColor6);
                mTextView6.setBackgroundColor(titleColor6);

            }
        }
    }
}
