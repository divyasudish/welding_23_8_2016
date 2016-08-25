package welding.taal.com.welding_23_08_2016.Connection;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015-07-30.
 */
public class ConnectManager implements Connect.RemoveSocketListener{
    private final String TAG = ConnectManager.class.getSimpleName();
    private Handler handler = new Handler(Looper.getMainLooper());
    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private static ConnectManager instance;

//    private final CopyOnWriteArrayList<Connect> connectsList = new CopyOnWriteArrayList<>();

    private ConnectManager(){
    }

    public static ConnectManager getInstance(){
        if (instance == null)
            instance = new ConnectManager();
        return instance;
    }


    public Connect createTcpClient(String host, int port, ConnectListener connectListener){
        Connect tcpClientConnect = new TcpClientConnect(port,host,handler,connectListener);
//        tcpClientConnect.setRemoveSocketListener(this);
//        connectsList.add(tcpClientConnect);
        Log.d(TAG, "------------------>add");
        pool.execute(tcpClientConnect);
        return tcpClientConnect;
    }

    @Override
    public void onRemove(Connect connect) {
//        int index = connectsList.indexOf(connect);
//        if (index != -1){
//            connectsList.remove(index);
//            Log.d(TAG, "------------------>remove");
//        }
//
//        System.out.println("ConnectManager---------------->size:"+connectsList.size());
    }
}
