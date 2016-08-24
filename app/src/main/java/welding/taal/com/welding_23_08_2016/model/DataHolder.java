package welding.taal.com.welding_23_08_2016.model;

import android.content.Context;
import android.widget.ArrayAdapter;

import welding.taal.com.welding_23_08_2016.R;

/**
 * Created by divyashreenair on 24/8/16.
 */
public class DataHolder {

    private int selected;
    private ArrayAdapter<CharSequence> adapter;

    public DataHolder(Context parent) {
        adapter = ArrayAdapter.createFromResource(parent, R.array.new_device_spinner_list, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public ArrayAdapter<CharSequence> getAdapter() {
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