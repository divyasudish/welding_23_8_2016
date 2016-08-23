package welding.taal.com.welding_23_08_2016.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.model.ConnectionClass;

/**
 * Created by User on 23-08-2016.
 */
public class ConnectionAdapter extends BaseAdapter {

    private Context mContext;
    private Intent mIntent;
    private List<ConnectionClass> mList;

    public ConnectionAdapter(Context context, List<ConnectionClass> connectionClassList) {
        this.mList = connectionClassList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mList.size();
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
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.list_connection, null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.ipAddress = (TextView) convertView.findViewById(R.id.ipAddress);
            viewHolder.portNumber = (EditText) convertView.findViewById(R.id.portNumber);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.checkBox.setText(mList.get(position).getC());
        viewHolder.ipAddress.setText(mList.get(position).getmIpAddress());
        viewHolder.portNumber.setText(mList.get(position).getmPort());

        return convertView;
    }

    class ViewHolder {
        public CheckBox checkBox;
        public TextView ipAddress;
        public EditText portNumber;
    }
}