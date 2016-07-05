package sunnydemo2.sharesdk;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.het.share.R;
import com.het.share.adapter.CommoShareViewAdapter;
import com.het.share.listener.ICommonShareListener;
import com.het.share.manager.CommonSharePlatform;
import com.het.share.model.CommonShareItemModel;
import com.het.share.widget.CircleFlowIndicator;
import com.het.share.widget.ViewFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 2016/3/11.
 * Annotion:
 */
public class ShareDialog extends BaseDialogFragment {


    private ViewFlow shareViewPager;
    private CircleFlowIndicator mFlowIndicator;
    private List<CommonShareItemModel> shareItemModels = new ArrayList<>();
    private CommoShareViewAdapter mShareViewAdapter;

    private static ICommonShareListener listener;

    public ShareDialog() {
    }

    public static void setListener(ICommonShareListener listener) {
        ShareDialog.listener = listener;
    }

    @Override
    protected int getStyle() {
        return R.style.CommonShareDialogStyleBottom;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_vertical_dialog_layout;
    }

    @Override
    protected void addView(View rootView) {

        //如果上层没有传内容View进来，则使用默认的
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.common_share_dialog, null);
        Button mBtnCancelShare = (Button) view.findViewById(R.id.cancel_share);
        mBtnCancelShare.setOnClickListener(this);
        shareViewPager = (ViewFlow) view.findViewById(R.id.share_viewpager);
        mFlowIndicator = (CircleFlowIndicator) view.findViewById(R.id.share_flow_indicator);
        shareViewPager.setFlowIndicator(mFlowIndicator);

        addShareViews();

        LinearLayout rootLayout = ((LinearLayout) rootView);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;
        rootLayout.setLayoutParams(layoutParams);
        rootLayout.addView(view);
    }

    /**
     * 添加分享平台
     */
    private void addShareViews() {
        addShareView(mContext.getResources().getDrawable(R.drawable.common_share_logo_wechatmoments),
                mContext.getResources().getString(R.string.share_plat_wechatmoments), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onStartShare(CommonSharePlatform.WeixinFriendCircle);
                    }
                });
        addShareView(mContext.getResources().getDrawable(R.drawable.common_share_logo_wechat),
                mContext.getResources().getString(R.string.share_plat_wechat), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onStartShare(CommonSharePlatform.WeixinFriend);
                    }
                });
        addShareView(mContext.getResources().getDrawable(R.drawable.common_share_logo_qzone),
                mContext.getResources().getString(R.string.share_plat_qqzone), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onStartShare(CommonSharePlatform.QQ_Zone);
                    }
                });
        addShareView(mContext.getResources().getDrawable(R.drawable.common_share_logo_qq),
                mContext.getResources().getString(R.string.share_plat_qq), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onStartShare(CommonSharePlatform.QQ_Friend);
                    }
                });

        addShareView(mContext.getResources().getDrawable(R.drawable.common_share_logo_sinaweibo),
                mContext.getResources().getString(R.string.share_plat_sina), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onStartShare(CommonSharePlatform.SinaWeibo);
                    }
                });

    }

    /**
     * 动态添加分享图标
     *
     * @param shareDrawable
     * @param shareText
     */
    public void addShareView(@NonNull Drawable shareDrawable,
                             @NonNull String shareText,
                             @Nullable View.OnClickListener onClickListener) {

        CommonShareItemModel itemModel = new CommonShareItemModel();
        itemModel.setmShareDrawable(shareDrawable);
        itemModel.setmShareTitle(shareText);
        itemModel.setOnClick(onClickListener);

        shareItemModels.add(itemModel);
        if (mShareViewAdapter != null) {
            mShareViewAdapter.notifyDataSetChanged();
        }

        initGridView();
    }

    /**
     * 根据item数目，生成相应的gridView
     */
    private void initGridView() {

        mShareViewAdapter = new CommoShareViewAdapter(mContext, shareItemModels);
        shareViewPager.setAdapter(mShareViewAdapter);
        mShareViewAdapter.notifyDataSetChanged();
        if (shareItemModels.size() <= 8) {
            mFlowIndicator.setVisibility(View.GONE);
        } else {
            mFlowIndicator.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onClick(View v) {

        int resId = v.getId();
        if (resId == R.id.cancel_share) {
            dismissDialog();
        }
    }

}
