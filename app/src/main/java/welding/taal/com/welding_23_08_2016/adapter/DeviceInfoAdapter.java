package welding.taal.com.welding_23_08_2016.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DataHolder;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class DeviceInfoAdapter extends BaseAdapter {

    private Context mContext;
    public List<DeviceClass> mDeviceList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;

    public DeviceInfoAdapter(Context context, List<DeviceClass> list) {
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
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.list_new_device, null);
            viewHolder = new ViewHolder();
            viewHolder.data = new DataHolder(mContext);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.ip = (EditText) convertView.findViewById(R.id.ipValue);
            viewHolder.device = (EditText) convertView.findViewById(R.id.devicenameValue);
            viewHolder.spin = (Spinner) convertView.findViewById(R.id.spinner);
            viewHolder.remove = (Button) convertView.findViewById(R.id.remove);
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
        //viewHolder.checkBox.setChecked(mDeviceList.get(position).ismChecked());
        viewHolder.ip.setText(mDeviceList.get(position).getIp());
        viewHolder.device.setText(mDeviceList.get(position).getDevice());
        viewHolder.spin.setAdapter(viewHolder.data.getAdapter());
        viewHolder.ip.setId(position);
        viewHolder.device.setId(position);

        viewHolder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                viewHolder.data.setSelected(arg2);
                mDeviceList.get(position).setOperation(viewHolder.data.getText());
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

        //we need to update adapter once we finish with editing
        viewHolder.ip.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                final int position = v.getId();
                final EditText Caption = (EditText) v;
                try {
                    mDeviceList.get(position).setIp(Caption.getText().toString());
                } catch (Exception e) {

                }
            }
            }
        });
        viewHolder.device.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    try {
                        mDeviceList.get(position).setDevice(Caption.getText().toString());
                    } catch (Exception e) {

                    }
                }
            }
        });
        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeviceList.remove(position);
                notifyDataSetChanged();
            }
        });
        try {
            viewHolder.spin.setSelection(viewHolder.data.getSelected());

        }
        catch (Exception e) {

        }
        return convertView;
    }

    class ViewHolder {
        public EditText ip;
        public EditText device;
        public Spinner spin;
        public CheckBox checkBox;
        public Button remove;
        protected DataHolder data;
    }
}