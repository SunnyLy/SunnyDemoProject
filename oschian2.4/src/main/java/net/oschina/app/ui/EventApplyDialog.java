package net.oschina.app.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.oschina.app.AppContext;
import net.oschina.app.R;
import net.oschina.app.bean.EventApplyData;
import net.oschina.app.ui.dialog.CommonDialog;
import net.oschina.app.util.DialogHelp;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EventApplyDialog extends CommonDialog implements
		android.view.View.OnClickListener {
	
	@InjectView(R.id.et_name) EditText mName;
	
	@InjectView(R.id.tv_gender) TextView mGender;
	
	private String[] genders;
	
	@InjectView(R.id.et_phone) EditText mMobile;
	
	@InjectView(R.id.et_company) EditText mCompany;
	
	@InjectView(R.id.et_job) EditText mJob;

	private EventApplyDialog(Context context, boolean flag, OnCancelListener listener) {
		super(context, flag, listener);
	}

	@SuppressLint("InflateParams")
	private EventApplyDialog(Context context, int defStyle) {
		super(context, defStyle);
		View shareView = getLayoutInflater().inflate(
				R.layout.dialog_event_apply, null);
		ButterKnife.inject(this, shareView);
		setContent(shareView, 0);
		
		initView();
	}
	
	private void initView() {
		genders = getContext().getResources().getStringArray(
				R.array.gender);
		
		mGender.setText(genders[0]);
		
		mGender.setOnClickListener(this);
	}
	
	public EventApplyDialog(Context context) {
		this(context, R.style.dialog_bottom);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_gender:
			selectGender();
			break;

		default:
			break;
		}
	}
	
	private void selectGender() {
		String gender = mGender.getText().toString();
		int idx = 0;
		for (int i = 0; i < genders.length; i++) {
			if (genders[i].equals(gender)) {
				idx = i;
				break;
			}
		}
		DialogHelp.getSelectDialog(getContext(), genders, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				mGender.setText(genders[i]);
			}
		}).show();
	}
	
	public EventApplyData getApplyData() {
		String name = mName.getText().toString();
		String gender = mGender.getText().toString();
		String phone = mMobile.getText().toString();
		String company = mCompany.getText().toString();
		String job = mJob.getText().toString();
		
		if (TextUtils.isEmpty(name)) {
			AppContext.showToast("请填写姓名");
			return null;
		}
		
		if (TextUtils.isEmpty(phone)) {
			AppContext.showToast("请填写电话");
			return null;
		}
		
		EventApplyData data = new EventApplyData();
		
		data.setName(name);
		data.setGender(gender);
		data.setPhone(phone);
		data.setCompany(company);
		data.setJob(job);
		
		return data;
	}
}
