package welding.taal.com.welding_23_08_2016.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
public class FirmwareAdapter extends BaseAdapter {
    private Context mContext;
    public List<DeviceClass> mDeviceList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;
    private List<String> operationList;

    public FirmwareAdapter(Context context, List<DeviceClass> list) {
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
            db = new DatabaseHelper(mContext);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.firmCheck);
            viewHolder.firm = (TextView) convertView.findViewById(R.id.firmPath);
            viewHolder.browse = (Button) convertView.findViewById(R.id.firmButton);
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
        viewHolder.checkBox.setText(mDeviceList.get(position).getIp());

//        if (mDeviceList.get(position).ismChecked())
//            viewHolder.checkBox.setChecked(true);
//        else
//            viewHolder.checkBox.setChecked(false);
//
//        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if (viewHolder.checkBox.isChecked())
//                    mDeviceList.get(position).setmChecked(true);
//                else
//                    mDeviceList.get(position).setmChecked(false);
//            }
//        });
        return convertView;
    }

    class ViewHolder {
        public CheckBox checkBox;
        public TextView firm;
        public Button browse;
    }
}