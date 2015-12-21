package sunnydemo2.bargraph;

import android.app.Activity;
import android.os.Bundle;

import com.smartbracelet.sunny.sunnydemo2.R;

import sunnydemo2.bargraph.view.CurveChartView;


/**
 * Created by sunny on 2015/11/13.
 * Annotion:刻度盘
 */
public class CurveChartActivity extends Activity {

    private CurveChartView divisionCircle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curvechart);
    }

    @Override
    public void onContentChanged() {
        divisionCircle2 = (CurveChartView) findViewById(R.id.curve_chart);
    }
}
