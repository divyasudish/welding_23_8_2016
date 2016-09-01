package welding.taal.com.welding_23_08_2016.model;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import welding.taal.com.welding_23_08_2016.R;

/**
 * Created by divyashreenair on 24/8/16.
 */
public class DeviceSelectionHolder {

    private int selected;
    private ArrayAdapter<String> adapter;
    private List<String> operationList;
    private List<String> newList;
    private String op;
    public DeviceSelectionHolder(Context parent, String operation) {
        op = operation;
        operationList = new ArrayList<String>();
        newList = new ArrayList<>();
        operationList.add("Group 1");
        operationList.add("Group 2");
        operationList.add("Group 3");
        operationList.add("Group 4");
        operationList.add("Group 5");
        operationList.add("Group 6");
        operationList.add("Group 7");
        operationList.add("Group 8");
        operationList.add("Group 9");
        operationList.add("Group 10");

        for(int i = 0; i < operationList.size(); i++) {
            if(op.trim().equals(operationList.get(i))){
                newList.add(0,op);
            }
            else {
                newList.add(operationList.get(i));
            }
        }
        adapter = new ArrayAdapter<String>(parent, R.layout.spinner_text, newList);
    }

    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public String getText() {
        return (String) adapter.getItem(selected);
    }

    public int getselectedPosition(String x) {
        int pos = 0;
        for(int i = 0; i < newList.size(); i++) {
            if(newList.get(i).toString().equals(x.trim())){
                pos = i;
            }
        }
        return pos;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

}