package welding.taal.com.welding_23_08_2016.Connection;

import android.os.Handler;
import android.util.Log;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Administrator on 2015-07-31.
 */
public class TcpClientConnect extends Connect {
    private final String TAG = TcpClientConnect.class.getSimpleName();
    private String host;
    private int port;
    private final int DEFAULT_TIMEOUT = 3000;
    public TcpClientConnect(int port , String host, Handler handler, ConnectListener connectListener){
       connectType = CONNECT_TYPE.TCP_CLIENT;
       this.port = port;
       this.host = host;
       this.connectListener = connectListener;
       this.handler = handler;
       setConfiguration(new ConnectConfiguration(host,port));
    }

    @Override
    protected void build() {

        try {
            SocketAddress sa = new InetSocketAddress(host, port);
            socket = new Socket();
            socket.connect(sa,DEFAULT_TIMEOUT);
            connState = CONNECT_STATE_CONNECTED;
            is = socket.getInputStream();
            os = socket.getOutputStream();
            Log.d(TAG, "------------------>build");
        } catch (Exception e) {
            e.printStackTrace();
            breakConnect();
        }

        if (connState == CONNECT_STATE_CONNECTED){
            if (connectListener != null && handler != null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        connectListener.connectSuccess(configuration);
                    }
                });
            }
        }
    }
}
