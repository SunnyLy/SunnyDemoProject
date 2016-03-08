package sunnydemo2.tts;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sunny on 2016/3/7.
 * Annotion:TTS语音输入与语音识别
 */
public class TTSActivity extends Activity implements TextToSpeech.OnInitListener {

    private EditText mTTSEdittext;
    private TextToSpeech mTextToSpeech;

    public static void startTTSActivity(Context context) {
        Intent targetIntent = new Intent(context, TTSActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        initParams();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTextToSpeech.stop();
        mTextToSpeech.shutdown();
    }

    private void initParams() {
        mTextToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onContentChanged() {
        mTTSEdittext = (EditText) findViewById(R.id.tts_edittext);
    }

    /**
     * 开始把文字用语音读出
     *
     * @param view
     */
    public void startTTS(View view) {

        String things = mTTSEdittext.getText().toString();
        if (mTextToSpeech != null && !mTextToSpeech.isSpeaking()) {
            mTextToSpeech.setPitch(0.5f);//值越大，声音越尖,必须大于0.0f
            if (TextUtils.isEmpty(things)) {
                things = "Please input somethings";
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mTextToSpeech.speak(things, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                mTextToSpeech.speak(things, TextToSpeech.QUEUE_FLUSH, null);
            }
        }

    }

    /**
     * 开始语音识别
     *
     * @param view
     */
    public void startRecognize(View view) {
        try{
            Intent recognizeActivity =
                    //new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    new Intent(RecognizerIntent.ACTION_WEB_SEARCH);//通过网络检索识别的语音
            //传参
            //1:语音识别模式(语言模式、自由模式)
            recognizeActivity.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            //2:提示语音开始
            recognizeActivity.putExtra(RecognizerIntent.EXTRA_PROMPT, "Common,让我们躁起来！");
            //3:开始语音识别
            startActivityForResult(recognizeActivity, 0x01);
        }catch (ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "找不到语音设备", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            // int result = mTextToSpeech.setLanguage(Locale.US);//美式英语发音
            int result = mTextToSpeech.setLanguage(Locale.CHINESE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x01) {
                ArrayList<String > voicesList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                StringBuilder stringBuilder = new StringBuilder();
                if(voicesList != null && voicesList.size() > 0){

                    for(int i=0;i<voicesList.size();i++){
                        stringBuilder.append(voicesList.get(i));
                    }
                }else{
                    stringBuilder.append("请说话");
                }

                Toast.makeText(TTSActivity.this,stringBuilder.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
