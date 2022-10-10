package com.shc.north;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.neusoft.library.utils.JsonUtils;
import com.neusoft.library.utils.ToastUtils;

import static com.shc.north.WebSocketHelper.SOURCE;
import static com.shc.north.WebSocketHelper.TARGET;

/**
 * @author shaohch
 * @date 2021/8/11
 */

public class SmsSendAct extends AppCompatActivity implements View.OnClickListener {

    private EditText etContent, etNumber;
    private Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sms_send);
        initView();
    }

    private void initView() {
        etNumber = (EditText) findViewById(R.id.et_number);
        etContent = (EditText) findViewById(R.id.et_content);
        btnSend = (Button) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                if (!TextUtils.equals(etNumber.getText().toString(), TARGET)) {
                    ToastUtils.showToast(App.getApp(), "信息发送失败");
                    return;
                }
                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    ToastUtils.showToast(App.getApp(), "请输入内容");
                    return;
                }
                SmsBody smsBody = new SmsBody();
                smsBody.setMessageId(String.valueOf(System.currentTimeMillis()));
                smsBody.setContent(etContent.getText().toString());
                smsBody.setSourcePhone(SOURCE);
                smsBody.setTargetPhone(TARGET);
                smsBody.setMessageType("1");
                smsBody.setSendTime(Utils.getCurTime());
                WebSocketHelper.getInstance().sendMsg(JsonUtils.jsonToStr(smsBody));
                break;
        }
    }
}
