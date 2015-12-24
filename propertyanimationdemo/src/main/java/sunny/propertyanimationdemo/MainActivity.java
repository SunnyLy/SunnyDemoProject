package sunny.propertyanimationdemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import sunny.propertyanimationdemo.view.ColorEvaluator;
import sunny.propertyanimationdemo.view.PropertyAnimationView;

public class MainActivity extends AppCompatActivity {

    private Button mBtnStartAnimation;
    private PropertyAnimationView mAnimationView;
    private ObjectAnimator animator;

    private TextView mCount;
    private int mTotalTime = 20;
    private CountTimeThread countTimeThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bindView();
        startCountTime();
    }

    private void startCountTime() {
        if(countTimeThread == null){
            countTimeThread = new CountTimeThread();
        }

        countTimeThread.start();
    }

    private void bindView() {
        mBtnStartAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animator != null)
                    animator.cancel();
                    animator.reverse();
            }
        });
    }

    private void initView() {
        mBtnStartAnimation = (Button) findViewById(R.id.startAnimation);
        mAnimationView = (PropertyAnimationView) findViewById(R.id.animationView);
        mCount = (TextView) findViewById(R.id.count);
        startAniamtion();
    }

    private void startAniamtion(){

         animator = ObjectAnimator.ofObject(mAnimationView,"color",new ColorEvaluator(),"#0000FF","#FF0000");
        animator.setDuration(5000);
        animator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class CountTimeThread extends Thread{
        @Override
        public void run() {

            while (mTotalTime > 0){

                mTotalTime -- ;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCount.setText("倒计时："+mTotalTime);
                    }
                });

                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
    }
}
