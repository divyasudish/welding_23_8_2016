package welding.taal.com.welding_23_08_2016.model;

/**
 * Created by divyashreenair on 18/7/16.
 */
public class DeviceSelectionClass {

    private int id;
    private String Device;
    private String operation;
    private String group;
    private boolean mChecked;

    public DeviceSelectionClass() {

    }

    public DeviceSelectionClass(String device, String sub, String gr, boolean checked){
        Device = device;
        operation = sub;
        group = gr;
        mChecked = checked;
    }
    public DeviceSelectionClass(String ip, String device){
        Device = device;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean ismChecked() {
        return mChecked;
    }

    public void setmChecked(boolean mChecked) {
        this.mChecked = mChecked;
    }
}
