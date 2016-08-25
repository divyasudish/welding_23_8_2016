package welding.taal.com.welding_23_08_2016.model;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import welding.taal.com.welding_23_08_2016.R;

/**
 * Created by divyashreenair on 24/8/16.
 */
public class DataHolder {

    private int selected;
    private ArrayAdapter<String> adapter;
    private List<String> operationList;
    private List<String> newList;
    private String op;

    public DataHolder(Context parent, String operation) {
        op = operation;
        operationList = new ArrayList<String>();
        newList = new ArrayList<>();
        operationList.add("BLDC Motor");
        operationList.add("IMU");
        operationList.add("Relative encoder");
        operationList.add("Absolute encoder");
        operationList.add("Digital Input");
        operationList.add("Proximity Input");
        operationList.add("Solenoid");
        operationList.add("All");
        operationList.add("None");

        for(int i = 0; i < operationList.size(); i++) {
            if(op.trim().equals(operationList.get(i))){
                newList.add(0,op);
            }
            else {
                newList.add(operationList.get(i));
            }
        }
        adapter = new ArrayAdapter<String>(parent, R.layout.spinner_text, newList);
//        adapter = ArrayAdapter.createFromResource(parent, R.array.new_device_spinner_list, R.layout.spinner_text);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public String getText() {
        return (String) adapter.getItem(selected);
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

}