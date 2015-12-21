package android.support.v4.view1;



public final class VerticalViewPagerCompat {
    private VerticalViewPagerCompat() {}

    public interface  DataSetObserver extends ViewPagerAdapter.ViewPagerDataSetObserver{

    }

    public static void setDataSetObserver(ViewPagerAdapter adapter, DataSetObserver observer) {
        adapter.setDataObserver(observer);

    }
}
