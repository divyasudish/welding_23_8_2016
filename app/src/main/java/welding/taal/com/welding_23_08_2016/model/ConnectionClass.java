package welding.taal.com.welding_23_08_2016.model;

/**
 * Created by User on 23-08-2016.
 */
public class ConnectionClass {

    private String mIpAddress;
    private String mPort;
    private boolean mCheckBox;

    public ConnectionClass(boolean checkBox, String ipAddress, String port) {
        mIpAddress = ipAddress;
        mPort = port;
        mCheckBox = checkBox;
    }

    public String getmIpAddress() {
        return mIpAddress;
    }

    public void setmIpAddress(String mIpAddress) {
        this.mIpAddress = mIpAddress;
    }

    public String getmPort() {
        return mPort;
    }

    public void setmPort(String mPort) {
        this.mPort = mPort;
    }

    public boolean ismCheckBox() {
        return mCheckBox;
    }

    public void setmCheckBox(boolean mCheckBox) {
        this.mCheckBox = mCheckBox;
    }
}
