package welding.taal.com.welding_23_08_2016.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import welding.taal.com.welding_23_08_2016.model.DataHolder;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionHolder;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class DeviceSelectionAdapter extends BaseAdapter {

    private Context mContext;
    public List<DeviceSelectionClass> mDeviceList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;

    public DeviceSelectionAdapter(Context context, List<DeviceSelectionClass> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.list_device_selection, null);
            viewHolder = new ViewHolder();
            if(!mDeviceList.get(position).getGroup().isEmpty()){
                System.out.println("Onnnnnn" +mDeviceList.get(position).getGroup());
                viewHolder.data = new DeviceSelectionHolder(mContext,mDeviceList.get(position).getGroup().trim());
            }
            else {
                viewHolder.data = new DeviceSelectionHolder(mContext,"");
            }

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
        String x = mDeviceList.get(position).getGroup();
        try {
            System.out.println("sub string is " + x.substring(x.indexOf(" "), x.length()));
        }
        catch (Exception e) {

        }
        viewHolder.device.setText(mDeviceList.get(position).getDevice());
        //viewHolder.device.setText(Html.fromHtml(mDeviceList.get(position).getDevice() + "<sup>" + x.substring(x.indexOf(" "), x.length()) + "</sup>"));
        viewHolder.spin.setAdapter(viewHolder.data.getAdapter());

        int pos = viewHolder.data.getselectedPosition(mDeviceList.get(position).getGroup());
        viewHolder.spin.setSelection(pos);

        viewHolder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                viewHolder.data.setSelected(arg2);
                System.out.println("View holder data " + viewHolder.data.getText() + "//" + mDeviceList.get(position).getDevice() + "//" + viewHolder.data.getSelected() + 1);
                mDeviceList.get(position).setGroup(viewHolder.data.getText());
                int x =  viewHolder.data.getSelected() + 1;
                viewHolder.device.setText(Html.fromHtml(mDeviceList.get(position).getDevice() + "<sup>" + viewHolder.data.getText().substring(viewHolder.data.getText().indexOf(" "), viewHolder.data.getText().length()) + "</sup>"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });

        if (mDeviceList.get(position).ismChecked())
            viewHolder.checkBox.setChecked(true);
        else
            viewHolder.checkBox.setChecked(false);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (viewHolder.checkBox.isChecked())
                    mDeviceList.get(position).setmChecked(true);
                else
                    mDeviceList.get(position).setmChecked(false);
            }
        });
        try {
            //viewHolder.spin.setSelection(viewHolder.data.getSelected());

        }
        catch (Exception e) {

        }
        return convertView;
    }

    class ViewHolder {
        public TextView device;
        public TextView operation;
        public Spinner spin;
        public CheckBox checkBox;
        protected DeviceSelectionHolder data;
    }
}