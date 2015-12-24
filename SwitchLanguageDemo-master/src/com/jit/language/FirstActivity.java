package com.jit.language;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 描述：
 * 作者： Sunny
 * 日期： 2015-10-21 15:28
 * 版本： v1.0
 */
public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.next).setVisibility(View.VISIBLE);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recreate();
    }
}
