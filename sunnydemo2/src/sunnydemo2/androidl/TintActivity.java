package sunnydemo2.androidl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.smartbracelet.sunny.sunnydemo2.R;


/**
 * Created by sunny on 2015/12/15.
 * Annotion:
 */
public class TintActivity extends Activity {

    private static final String TAG = TintActivity.class.getSimpleName();

    private Button mBtnTint;

    public static void startTintActivity(Context context){
        Intent targetIntent = new Intent(context,TintActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tint);
    }

    @Override
    public void onContentChanged() {
        mBtnTint = (Button) findViewById(R.id.btn_bg_tint);
    }
}
