package sunnydemo2.bargraph;

import android.app.Activity;
import android.os.Bundle;

import com.smartbracelet.sunny.sunnydemo2.R;

import java.util.ArrayList;
import java.util.List;

import sunnydemo2.bargraph.view.BarGraphView;


/**
 * Created by sunny on 2015/11/12.
 * Annotion:柱形图
 */
public class BarGraphActivity extends Activity {
    private BarGraphView mBarGrapView;
    private List<Integer> xLists = new ArrayList<>();
    private List<Integer> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
        initParams();
    }

    private void initParams() {
       for(int i=0;i<23;i++){
           xLists.add(i);
       }

        values.add(10);
        values.add(80);
        values.add(20);
        values.add(90);
        values.add(30);
        values.add(35);
        values.add(33);
        values.add(46);
        values.add(72);

        if(mBarGrapView != null){
            mBarGrapView.setxPoint(xLists);
            mBarGrapView.setValues(values);
        }
    }

    @Override
    public void onContentChanged() {
        mBarGrapView = (BarGraphView) findViewById(R.id.graph_view);
    }
}
