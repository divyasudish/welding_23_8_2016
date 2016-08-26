package welding.taal.com.welding_23_08_2016.adapter;

import android.app.Activity;
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

import java.io.File;
import java.util.List;

import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.activities.FilePicker;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DataHolder;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionHolder;
import welding.taal.com.welding_23_08_2016.model.FirmwareClass;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class FirmwareAdapter extends BaseAdapter {
    private Context mContext;
    public List<FirmwareClass> mDeviceList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;
    private List<String> operationList;
    private static final int REQUEST_PICK_FILE = 1;
    private File selectedFile;
    private Activity origin;
    ViewHolder viewHolder;
    public int pos;

    public FirmwareAdapter(Context context, List<FirmwareClass> list) {
        this.mDeviceList = list;
        this.mContext = context;
        viewHolder = new ViewHolder();
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

        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.list_upgrade_firm, null);
            db = new DatabaseHelper(mContext);
            //origin = (Activity)mContext;
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.firmCheck);
            viewHolder.device = (TextView) convertView.findViewById(R.id.firmName);
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
        viewHolder.device.setText(mDeviceList.get(position).getDevice().toString());
        System.out.println("Path in adapter " + mDeviceList.get(position).getPath().toString());
        viewHolder.firm.setText(mDeviceList.get(position).getPath().toString());
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
        viewHolder.browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;
                Intent intent = new Intent(mContext, FilePicker.class);
                ((Activity) v.getContext()).startActivityForResult(intent, REQUEST_PICK_FILE);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public CheckBox checkBox;
        public TextView device;
        public TextView firm;
        public Button browse;
    }

}