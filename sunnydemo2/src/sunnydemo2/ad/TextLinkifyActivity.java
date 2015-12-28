package sunnydemo2.ad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2015/12/28.
 * 文字添加链接
 */
public class TextLinkifyActivity extends Activity {
    public static final String TAG = TextLinkifyActivity.class.getSimpleName();

    public static void startTextLinkifyActivity(Context context){
        Intent targetIntent = new Intent(context,TextLinkifyActivity.class);
        context.startActivity(targetIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textlinkify);

        TextView textResource = (TextView) findViewById(R.id.textify);
        textResource.setText(Html.fromHtml(getResources().getString(R.string.text_from_html)));
        textResource.setMovementMethod(LinkMovementMethod.getInstance());

        TextView netResource = (TextView) findViewById(R.id.show_net_data);
        netResource.setText(Html.fromHtml("<b>text_html_program: Constructed from HTML programmatically.</b>\"\n" +
                "                                + \"  Text with a <a href=\\\"http://www.baidu.com\\\">link</a> \"\n" +
                "                                + \"created in the Java source code using HTML."));
        textResource.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
