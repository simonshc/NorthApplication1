package com.shc.north;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class SmsDetailAct extends AppCompatActivity {

    private TextView tvDate;
    private TextView tvNumber;
    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sms_detail);
        initView();
    }

    private void initView() {
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvContent = (TextView) findViewById(R.id.tv_content);

        SmsBody smsBean = getIntent().getParcelableExtra("sms");
        if (smsBean != null) {
            tvDate.setText(Utils.msgTime(Utils.getTimeS(smsBean.getSendTime())));
            tvNumber.setText(smsBean.getSourcePhone());
            tvContent.setText(smsBean.getContent());
        }
    }
}
