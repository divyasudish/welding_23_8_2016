package welding.taal.com.welding_23_08_2016.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class DeviceClass {

    private int id;
    private String Ip;
    private String Device;
    private String operation;
    private boolean mChecked;

    public DeviceClass() {

    }

    public DeviceClass(String ip, String device, String sub, boolean checked){
        Ip = ip;
        Device = device;
        operation = sub;
        mChecked = checked;
    }
    public DeviceClass(String ip, String device){
        Ip = ip;
        Device = device;
    }


    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean ismChecked() {
        return mChecked;
    }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }
}
