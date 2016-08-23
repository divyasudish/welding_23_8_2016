package welding.taal.com.welding_23_08_2016.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class DeviceSelectionAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<DeviceSelectionClass> mDeviceList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;

    public DeviceSelectionAdapter(Context context, ArrayList<DeviceSelectionClass> list) {
        this.mDeviceList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mDeviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.list_device_selection, null);
            viewHolder = new ViewHolder();
            db = new DatabaseHelper(mContext);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.device = (TextView) convertView.findViewById(R.id.deviceNameVal);
            viewHolder.operation = (TextView) convertView.findViewById(R.id.operationVal);
            viewHolder.spin = (Spinner) convertView.findViewById(R.id.spinner);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("OpenDbListAdapter ", "List View Clicked");
            }
        });
        viewHolder.checkBox.setChecked(mDeviceList.get(position).ismChecked());
        viewHolder.operation.setText(mDeviceList.get(position).getOperation());
        viewHolder.device.setText(mDeviceList.get(position).getDevice());
        viewHolder.spin = (Spinner) convertView.findViewById(R.id.spinner);
        list = new ArrayList<String>();
        list.add("Group 1");
        list.add("Group 2");
        list.add("Group 3");
        list.add("Group 4");
        list.add("Group 5");
        list.add("Group 6");
        list.add("Group 7");
        list.add("Group 8");
        list.add("Group 9");
        list.add("Group 10");
        ArrayAdapter<String> ad = new ArrayAdapter<String>(mContext, R.layout.spinner_text, list);
        viewHolder.spin.setAdapter(ad);
        return convertView;
    }

    class ViewHolder {
        public TextView device;
        public TextView operation;
        public Spinner spin;
        public CheckBox checkBox;
    }
}