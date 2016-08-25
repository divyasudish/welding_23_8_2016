package welding.taal.com.welding_23_08_2016.Connection;


/**
 * Created by LiuJinqi on 2015-07-30.
 */
public interface ConnectListener {
    byte [] data = new byte[100000];
    /**
     * connect success
     */
    public void connectSuccess(ConnectConfiguration configuration);

    /**
     * connect error
     */
    public void connectBreak(ConnectConfiguration configuration);


    public void onReceviceData(ConnectConfiguration configurations, byte[] data);
}
