package com.shc.north;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.neusoft.library.utils.JsonUtils;
import com.neusoft.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv_sms_list;
    private Button btn_create;

    private SmsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        lv_sms_list = (ListView) findViewById(R.id.lv_sms_list);
        btn_create = (Button) findViewById(R.id.btn_create);

        btn_create.setOnClickListener(this);

        mAdapter = new SmsAdapter(this, CacheUtils.getDataList(this, "msgList", SmsBody.class));
        lv_sms_list.setAdapter(mAdapter);

        WebSocketHelper.getInstance()
                .setOnMsgResultListener(new WebSocketHelper.OnMsgResultListener() {
                    @Override
                    public void onMsgResult(String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                SmsBody smsBody = JsonUtils.strToJson(msg, SmsBody.class);
                                if (!TextUtils.isEmpty(smsBody.getCode()) && !TextUtils.equals(smsBody.getCode(), "200")) {
                                    ToastUtils.showToast(App.getApp(), "信息发送失败");
                                    return;
                                }
                                if (!TextUtils.isEmpty(smsBody.getCode()) && TextUtils.equals(smsBody.getCode(), "200")) {
                                    ToastUtils.showToast(App.getApp(), "发送成功");
                                    return;
                                }
                                if (TextUtils.isEmpty(smsBody.getCode())){
                                    addSmsBody(smsBody);
                                }
                            }
                        });
                    }

                    @Override
                    public void onSendError() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(App.getApp(), "信息发送失败");
                            }
                        });
                    }
                })
                .connect();
    }

    public void addSmsBody(SmsBody smsBody){
        if (smsBody != null) {
            mAdapter.addItem(smsBody);
        }
        CacheUtils.setSharePre(this, "msgList", JsonUtils.jsonToStr(mAdapter.getDataSource()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                startActivity(new Intent(this, SmsSendAct.class));
                break;
        }
    }
}