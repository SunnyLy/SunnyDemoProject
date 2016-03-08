package sunnydemo2.danmuku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;
import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2016/3/8.
 * Annotion:Android弹幕
 */
public class DanmuKuActivity extends Activity {

    private Button mBtnDanmakuSend;
    private DanmakuView mDanmaku;
    private EditText mEditText;

    public static void startDanmuKuActivity(Context context){
        Intent targetIntent = new Intent(context,DanmuKuActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmuku);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDanmaku.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mDanmaku != null){
            mDanmaku.hide();
            mDanmaku.clear();
        }
    }

    @Override
    public void onContentChanged() {
        mEditText = (EditText) findViewById(R.id.danmuku_eidttext);
        mDanmaku = (DanmakuView) findViewById(R.id.danmakuView);
        mBtnDanmakuSend = (Button) findViewById(R.id.danmaku_send);

        initDamaku();

    }

    private void initDamaku() {
        List<IDanmakuItem> danmakuItems = getDamakuItems();
        mDanmaku.addItem(danmakuItems,true);
    }

    private List<IDanmakuItem> getDamakuItems() {
        List<IDanmakuItem> list = new ArrayList<>();
        list.clear();
        for (int i = 0; i < 100; i++) {
            IDanmakuItem item = new DanmakuItem(this, i + " : plain text danmuku", mDanmaku.getWidth());
            list.add(item);
        }

        String msg = " : text with image   ";
        for (int i = 0; i < 100; i++) {
            ImageSpan imageSpan = new ImageSpan(this, R.drawable.ic_launcher);
            SpannableString spannableString = new SpannableString(i + msg);
            spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            IDanmakuItem item = new DanmakuItem(this, spannableString, mDanmaku.getWidth(), 0, 0, 0, 1.5f);
            list.add(item);
        }
        return list;
    }

    /**
     * 开始发送弹幕
     * @param view
     */
    public void sendDanmaku(View view){
        String info = mEditText.getText().toString();
        if(TextUtils.isEmpty(info)){
            Toast.makeText(this,mEditText.getHint().toString(),Toast.LENGTH_SHORT).show();
        }else{
            DanmakuItem danmakuItem = new DanmakuItem(this,new SpannableString(info),mDanmaku.getWidth(),0,R.color.red_03,0,1);
            mDanmaku.addItemToHead(danmakuItem);
            mEditText.setText("");
        }

    }
}
