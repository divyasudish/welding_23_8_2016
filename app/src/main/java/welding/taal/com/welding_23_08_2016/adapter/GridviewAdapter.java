package welding.taal.com.welding_23_08_2016.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.activities.CalibrationMainActivity;
import welding.taal.com.welding_23_08_2016.activities.DataLogMainActivity;
import welding.taal.com.welding_23_08_2016.activities.DeviceSelectionActivity;
import welding.taal.com.welding_23_08_2016.activities.MainMenuActivity;
import welding.taal.com.welding_23_08_2016.activities.NewDeviceActivity;
import welding.taal.com.welding_23_08_2016.activities.TorchHeadPositonActivity;
import welding.taal.com.welding_23_08_2016.activities.TorchMainActivity;

/**
 * Created by divyashree_nair on 29/2/16.
 */
public class GridviewAdapter extends BaseAdapter {

    String[] result;
    Context context;
    Intent mIntent;
    int [] imageId;
    private String mKey = "MachineName";
    private String mProgresskey = "ProgressData";
    private LayoutInflater mInflater=null;
    public GridviewAdapter(MainMenuActivity mainActivity, String[] kurtasName, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result=kurtasName;
        imageId = prgmImages;
        context=mainActivity;
        mInflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder=new Holder();
        View rowView;
        rowView = mInflater.inflate(R.layout.gridview_item, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img = (ImageView) rowView.findViewById(R.id.img);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            switch (holder.tv.getText().toString().trim()) {
                case "New Device":
                    mIntent = new Intent(context, NewDeviceActivity.class);
                    context.startActivity(mIntent);
                    break;
                case "Device Selection":
                    mIntent = new Intent(context, DeviceSelectionActivity.class);
                    mIntent.putExtra(mKey, "Device");
                    context.startActivity(mIntent);
                    break;
                case "Data Log":
                    mIntent = new Intent(context, DataLogMainActivity.class);
                    context.startActivity(mIntent);
                    break;
                case "Torch Head Position":
                    mIntent = new Intent(context, TorchMainActivity.class);
                    context.startActivity(mIntent);
                    break;
                case "Calibration":
                    mIntent = new Intent(context, CalibrationMainActivity.class);
                    context.startActivity(mIntent);
                    break;
//                case "Firmware Upgrade":
//                    mIntent = new Intent(context, UpgradeFirmwareActivity.class);
//                    context.startActivity(mIntent);

                default:
                    System.out.println("Else ");
            }
            }
        });

        return rowView;
    }

}