package sunnydemo2.mvp.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartbracelet.sunny.sunnydemo2.R;

/**
 * Created by sunny on 2016/3/9.
 * Annotion：登录界面
 */
public class LoginView extends AbsView {

    private EditText mEditTextAccount;
    private EditText mEditTextPassword;

    private Button mBtnLogin;
    @Override
    public int getLayoutId() {
        return R.layout.presenter_activity_login;
    }

    @Override
    public View getRootView() {
        return super.getRootView();
    }

    @Override
    public void initWidget() {
        mEditTextAccount = (EditText) getRootView().findViewById(R.id.presenter_account);
        mEditTextPassword = (EditText) getRootView().findViewById(R.id.presenter_password);
        mBtnLogin = (Button) getRootView().findViewById(R.id.presenter_login);

        bindView(mBtnLogin);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.presenter_login:
                String account = mEditTextAccount.getText().toString();
                String password = mEditTextPassword.getText().toString();
                if(TextUtils.isEmpty(account)){
                    Toast.makeText(context,mEditTextAccount.getHint().toString(),Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(context,mEditTextPassword.getHint().toString(),Toast.LENGTH_SHORT).show();
                    return;
                }

                mEditTextPassword.setText("");
                mEditTextAccount.setText("");
                Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
