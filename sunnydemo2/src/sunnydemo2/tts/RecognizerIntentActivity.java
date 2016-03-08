package sunnydemo2.tts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sunny on 2016/3/7.
 * Annotion:语音识别
 */
public class RecognizerIntentActivity extends Activity {

    public static final String TAG = RecognizerIntentActivity.class.getSimpleName();

    public static void startRecognizerIntentActivity(Context context){
        Intent targetIntent = new Intent(context,RecognizerIntentActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
