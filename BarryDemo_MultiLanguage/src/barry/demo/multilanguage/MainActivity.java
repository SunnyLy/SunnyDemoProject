package barry.demo.multilanguage;

import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Configuration config = getResources().getConfiguration();
		switch (item.getItemId()) {
		case R.id.action_english:
			config.locale = Locale.ENGLISH;
			break;
		case R.id.action_simple_chinses:
			config.locale = Locale.SIMPLIFIED_CHINESE;
			break;
		case R.id.action_traditional_chinese:
			config.locale = Locale.TRADITIONAL_CHINESE;
			break;
		default:
			return true;
		}
		getResources().updateConfiguration(config, getResources().getDisplayMetrics());
		((TextView)findViewById(R.id.textViewHello)).setText(R.string.hello_world);;
		return true;
	}


}
