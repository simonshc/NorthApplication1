package com.shc.north;

import android.util.Log;

import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.WebSocketManager;
import com.zhangke.websocket.WebSocketSetting;
import com.zhangke.websocket.response.ErrorResponse;

import org.java_websocket.framing.Framedata;

import java.nio.ByteBuffer;

public class WebSocketHelper {
    public static final String SOURCE = "18317047004";
    public static final String TARGET = "13478841024";
    private static volatile WebSocketHelper singleton;
    private OnMsgResultListener mOnMsgResultListener;

    WebSocketManager manager1;

    private WebSocketHelper() {
        //设置连接超时时间
        WebSocketSetting setting = new WebSocketSetting();
        //连接地址，必填，例如 测试：10.10.126.81:8086  服务器：10.10.129.143:8086
        setting.setConnectUrl("ws://10.10.129.143:8086/websocket/" + SOURCE);
        setting.setConnectTimeout(10 * 1000);
        setting.setConnectionLostTimeout(60);
        //设置断开后的重连次数，可以设置的很大，不会有什么性能上的影响
        setting.setReconnectFrequency(40);
        //接收到数据后是否放入子线程处理，只有设置了 ResponseProcessDispatcher 才有意义
        setting.setProcessDataOnBackground(true);
        //网络状态发生变化后是否重连，
        //需要调用 WebSocketHandler.registerNetworkChangedReceiver(context) 方法注册网络监听广播
        setting.setReconnectWithNetworkChanged(true);
        //通过 init 方法初始化默认的 WebSocketManager 对象
        manager1 = WebSocketHandler.init(setting);
        manager1.addListener(new SocketListener() {
            @Override
            public void onConnected() {
                Log.i("SHC", "NorthApp onConnected");
            }

            @Override
            public void onConnectFailed(Throwable e) {
                Log.i("SHC", "NorthApp onConnectFailed");
            }

            @Override
            public void onDisconnect() {
                Log.i("SHC", "NorthApp onDisconnect");
            }

            @Override
            public void onSendDataError(ErrorResponse errorResponse) {
                Log.i("SHC", "NorthApp onSendDataError");
                if (mOnMsgResultListener != null) {
                    mOnMsgResultListener.onSendError();
                }
            }

            @Override
            public <T> void onMessage(String message, T data) {
                Log.i("SHC", "NorthApp onMessage = " + message);
                if (mOnMsgResultListener != null) {
                    mOnMsgResultListener.onMsgResult(message);
                }
            }

            @Override
            public <T> void onMessage(ByteBuffer bytes, T data) {

            }

            @Override
            public void onPing(Framedata framedata) {

            }

            @Override
            public void onPong(Framedata framedata) {

            }
        });
    }

    public static WebSocketHelper getInstance() {
        if (singleton == null) {
            synchronized (WebSocketHelper.class) {
                if (singleton == null) {
                    singleton = new WebSocketHelper();
                }
            }
        }
        return singleton;
    }

    public void sendMsg(String content) {
        if (manager1 != null && manager1.isConnect()) {
            manager1.send(content);
        }
    }

    public void connect() {
        try {
            manager1.start();
            new Thread(runnable).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!manager1.isConnect()) {
                    Log.i("SHC", "NorthUser reconnect = ");
                    manager1.reconnect();
                }
            }
        }
    };

    public void disconnect() {
        try {
            manager1.disConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebSocketHelper setOnMsgResultListener(OnMsgResultListener listener) {
        mOnMsgResultListener = listener;
        return this;
    }

    public interface OnMsgResultListener {
        void onMsgResult(String msg);
        void onSendError();
    }
}