package sunnydemo2.databinding;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.smartbracelet.sunny.sunnydemo2.databinding.ActivityDataBindingBinding;

/**
 * Created by sunny on 2016/3/14.
 * Annotion:Android中使用DataBinding案例
 */
public class DataBindingActivity extends AppCompatActivity {

    public static final String TAG = "DataBindingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Don't known why DataBinderUtils cannot be found.
      // ActivityDataBindingBinding binding = DataBinderUtils.
    }
}
