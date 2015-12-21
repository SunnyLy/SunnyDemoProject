package sunnydemo2.bargraph;

import android.app.Activity;
import android.os.Bundle;

import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.bargraph.view.DivisionCircle2;


/**
 * Created by sunny on 2015/11/13.
 * Annotion:刻度盘
 */
public class DivisionCircle2Activity extends Activity {

    private DivisionCircle2 divisionCircle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divistion_circle2);
    }

    @Override
    public void onContentChanged() {
        divisionCircle2 = (DivisionCircle2) findViewById(R.id.divsion_circle2);
        divisionCircle2.setCurrentValue(90);
    }
}
