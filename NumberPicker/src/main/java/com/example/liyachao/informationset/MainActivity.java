package com.example.liyachao.informationset;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private PopupWindow pw;
    private List<EditText> editTexts;
    private View view;
    private ScrollView scrollView;
    ViewGroup.LayoutParams layoutParams;

    private Button pw_btn_done, pw_btn_last, pw_btn_next;
    private EditText sex;
    private EditText birth;
    private EditText weight;
    private EditText height;
    private EditText step;
    private EditText sensitive;

    private TextView one, two, three, four, five, six,
            seven, eight, nine, zero, delete, point;
    private List<TextView> number;
    private String myNumber = "";

    private NumberPicker numberPicker, sexPicker;
    private TableLayout tableLayout;

    private String sexStr;
    private String birthStr = "1990";
    private String weightStr = "50 kg";
    private String heightStr = "175 cm";
    private String stepStr = "35 cm";
    private String sensitiveStr = "5";

    private String strValue[] = {sexStr, birthStr, weightStr, heightStr, stepStr, sensitiveStr};

    private String str[] = new String[]{"男", "女"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        view = LayoutInflater.from(this).inflate(R.layout.pwpupwindow, null);
        layoutParams = scrollView.getLayoutParams();
        editTexts = new ArrayList<EditText>();
        initView();
        setPWView();
        setListener();
    }

    private void setListener() {

        for (int i = 0; i < editTexts.size(); i++) {
            editTexts.get(i).setOnFocusChangeListener(new myFocusChangeListener());
        }

        pw_btn_done.setOnClickListener(new myClickListener());
        pw_btn_next.setOnClickListener(new myClickListener());
        pw_btn_last.setOnClickListener(new myClickListener());

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal, EditText editText) {
                for (int i = 0; i < editTexts.size(); i++) {
                    if (editTexts.get(i).isFocused()) {
                        editTexts.get(i).setText(picker.getValue() + "");
                        editTexts.get(i).setSelection(editTexts.get(i).getText().length());
                        strValue[i] = picker.getValue() + "";
                        break;
                    }
                }
            }
        });
        sexPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal, EditText editText) {
                sex.setText(str[picker.getValue()]);
                sex.setSelection(str[picker.getValue()].length());
                strValue[0] = str[picker.getValue()];
            }
        });
    }

    private void setPWView() {

        number = new ArrayList<TextView>();
        pw = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                dp2px(350));
        pw_btn_done = (Button) view.findViewById(R.id.done);
        pw_btn_last = (Button) view.findViewById(R.id.last);
        pw_btn_next = (Button) view.findViewById(R.id.next);
        numberPicker = (NumberPicker) view.findViewById(R.id.numberpicker);
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);

        one = (TextView) view.findViewById(R.id.number_1);
        number.add(one);
        two = (TextView) view.findViewById(R.id.number_2);
        number.add(two);
        three = (TextView) view.findViewById(R.id.number_3);
        number.add(three);
        four = (TextView) view.findViewById(R.id.number_4);
        number.add(four);
        five = (TextView) view.findViewById(R.id.number_5);
        number.add(five);
        six = (TextView) view.findViewById(R.id.number_6);
        number.add(six);
        seven = (TextView) view.findViewById(R.id.number_7);
        number.add(seven);
        eight = (TextView) view.findViewById(R.id.number_8);
        number.add(eight);
        nine = (TextView) view.findViewById(R.id.number_9);
        number.add(nine);
        zero = (TextView) view.findViewById(R.id.number_0);
        number.add(zero);
        point = (TextView) view.findViewById(R.id.number_);
        number.add(point);
        delete = (TextView) view.findViewById(R.id.number_reduce);
        number.add(delete);

        for (int i = 0; i < number.size(); i++) {
            number.get(i).setOnClickListener(new NumberListener());
        }


        sexPicker = (NumberPicker) view.findViewById(R.id.numberpicker1);
        numberPicker.setWrapSelectorWheel(false);

    }

    private class NumberListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.number_0:
                    myNumber += "0";
                    break;
                case R.id.number_1:
                    myNumber += "1";
                    break;
                case R.id.number_2:
                    myNumber += "2";
                    break;
                case R.id.number_3:
                    myNumber += "3";
                    break;
                case R.id.number_4:
                    myNumber += "4";
                    break;
                case R.id.number_5:
                    myNumber += "5";
                    break;
                case R.id.number_6:
                    myNumber += "6";
                    break;
                case R.id.number_7:
                    myNumber += "7";
                    break;
                case R.id.number_8:
                    myNumber += "8";
                    break;
                case R.id.number_9:
                    myNumber += "9";
                    break;
                case R.id.number_:
                    myNumber += ".";
                    break;
                case R.id.number_reduce:
                    myNumber = myNumber.substring(0, myNumber.length() - 1);
                    break;
            }

            for (int i = 0; i < editTexts.size(); i++) {
                if (editTexts.get(i).isFocused()) {
                    String str = strValue[i].substring(strValue[i].length() - 3, strValue[i].length());
                    Log.i("tag", strValue[i]);
                    editTexts.get(i).setText(myNumber + str);
                    editTexts.get(i).setSelection(myNumber.length());
                    break;
                }
            }
        }
    }

    private class myFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId()) {
                case R.id.edit_sex:
                    sexStr = sex.getText().toString().trim();
                    if (hasFocus) {
                        tableLayout.setVisibility(View.GONE);
                        sexPicker.setVisibility(View.VISIBLE);
                        numberPicker.setVisibility(View.GONE);
                        sexPicker.setWrapSelectorWheel(false);
                        sexPicker.setMaxValue(1);
                        sexPicker.setMinValue(0);
                        sexPicker.setDisplayedValues(str);
                        sex.setCursorVisible(true);
                        sex.setSelection(1);
                    } else {
                        sex.setText(str[sexPicker.getValue()]);
                    }
                    break;
                case R.id.edit_birthday:
                    if (hasFocus) {
                        tableLayout.setVisibility(View.GONE);
                        numberPicker.setVisibility(View.VISIBLE);
                        sexPicker.setVisibility(View.GONE);
                        numberPicker.setValue(Integer.parseInt(strValue[1]));
                        numberPicker.setMaxValue(2015);
                        numberPicker.setMinValue(1970);
                        birth.setSelection(4);

                    } else {
                        birth.setText(strValue[1]);
                    }
                    break;
                case R.id.edit_weight:
                    if (hasFocus) {
                        tableLayout.setVisibility(View.VISIBLE);
                        numberPicker.setVisibility(View.GONE);
                        sexPicker.setVisibility(View.GONE);
                        weight.setText(" kg");
                    } else {
                        weight.setText(myNumber + " kg");
                        myNumber = "";
                    }
                    break;
                case R.id.edit_height:
                    if (hasFocus) {
                        tableLayout.setVisibility(View.VISIBLE);
                        numberPicker.setVisibility(View.GONE);
                        sexPicker.setVisibility(View.GONE);
                        height.setText(" cm");
                    } else {
                        height.setText(myNumber + " cm");
                        myNumber = "";
                    }
                    break;
                case R.id.edit_step:
                    if (hasFocus) {
                        tableLayout.setVisibility(View.VISIBLE);
                        numberPicker.setVisibility(View.GONE);
                        sexPicker.setVisibility(View.GONE);
                        step.setText(" cm");
                    } else {
                        step.setText(myNumber + " cm");
                        myNumber = "";
                    }
                    break;
                case R.id.edit_sensitive:
                    if (hasFocus) {
                        tableLayout.setVisibility(View.GONE);
                        numberPicker.setVisibility(View.VISIBLE);
                        sexPicker.setVisibility(View.GONE);

                        numberPicker.setValue(Integer.parseInt(strValue[5]));
                        numberPicker.setMaxValue(10);
                        numberPicker.setMinValue(1);
                        sensitive.setText(sensitiveStr);
                        sensitive.setSelection(sensitiveStr.length());
                    } else {
                        sensitive.setText(strValue[5]);
                    }
                    break;
            }
        }
    }

    private class myClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.done:
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    scrollView.setLayoutParams(layoutParams);
                    pw.dismiss();
                    break;
                case R.id.last:
                    for (int i = 0; i < editTexts.size(); i++) {
                        if (editTexts.get(i).isFocused() && i != 0) {
                            editTexts.get(i).clearFocus();
                            editTexts.get(i - 1).requestFocus();
                            break;
                        }
                    }
                    break;
                case R.id.next:
                    for (int i = 0; i < editTexts.size(); i++) {
                        if (editTexts.get(i).isFocused() && i != (editTexts.size() - 1)) {
                            editTexts.get(i).clearFocus();
                            editTexts.get(i + 1).requestFocus();
                            break;
                        }
                    }

                    break;
            }
        }
    }


    private int dp2px(float value) {
        float v = getResources().getDisplayMetrics().density;
        return (int) (v * value + 0.5f);
    }

    private void initView() {

        sex = (EditText) findViewById(R.id.edit_sex);
        birth = (EditText) findViewById(R.id.edit_birthday);
        weight = (EditText) findViewById(R.id.edit_weight);
        height = (EditText) findViewById(R.id.edit_height);
        step = (EditText) findViewById(R.id.edit_step);
        sensitive = (EditText) findViewById(R.id.edit_sensitive);
        editTexts.add(sex);
        editTexts.add(birth);
        editTexts.add(weight);
        editTexts.add(height);
        editTexts.add(step);
        editTexts.add(sensitive);
        for (int i = 0; i < editTexts.size(); i++) {
            hideSoftInputMethod(editTexts.get(i));
            editTexts.get(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (pw.isShowing()) {
                        layoutParams.height = dp2px(200);
                        scrollView.setLayoutParams(layoutParams);
                    } else {
                        pw.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                    }
                    return false;
                }
            });
        }
    }


    public void hideSoftInputMethod(EditText ed) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
        Log.i("tag", methodName + "123");
        if (methodName == null) {
            ed.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (Exception e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            }
        }
    }


}
