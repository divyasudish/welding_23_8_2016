package welding.taal.com.welding_23_08_2016.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class FirmwareClass {

    private int id;
    private String Device;
    private String Path;
    private boolean mChecked;

    public FirmwareClass() {

    }

    public FirmwareClass(String device, String sub, boolean checked) {
        Device = device;
        Path = sub;
        mChecked = checked;
    }
    public FirmwareClass(String ip, String device){
        Device = device;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
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
