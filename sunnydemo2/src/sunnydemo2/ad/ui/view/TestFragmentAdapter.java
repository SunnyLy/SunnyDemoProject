package sunnydemo2.ad.ui.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view1.SunnyFragmentPagerAdapter;

class TestFragmentAdapter extends SunnyFragmentPagerAdapter {
	protected static final String[] CONTENT = new String[] { "This", "Is Is", "A A A", "Test", };

	public TestFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return TestFragment.newInstance(CONTENT[position]);
	}

	@Override
	public int getCount() {
		return CONTENT.length;
	}
}