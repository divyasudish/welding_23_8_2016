package welding.taal.com.welding_23_08_2016.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.adapter.DeviceInfoAdapter;
import welding.taal.com.welding_23_08_2016.adapter.DeviceSelectionAdapter;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.ConnectionClass;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;

public class DeviceSelectionActivity extends AppCompatActivity {

    @Bind(R.id.saveBut)
    protected Button mSave;
    @Bind(R.id.deviceSelectionList)
    protected ListView mDeviceList;
    @Bind(R.id.linear)
    protected LinearLayout ln;
    @Bind(R.id.selectCheck)
    protected CheckBox chekAll;
    @Bind(R.id.deviceSelectionScreenLayout)
    protected LinearLayout deviceLayout;
    private boolean flag = false;
    private List<DeviceSelectionClass> deviceSelectionList;
    private List<DeviceClass> newDeviceList;
    private List<DeviceSelectionClass> mdeviceSelectionArrayList;
    private DeviceSelectionAdapter deviceSelectionAdapter;
    private DatabaseHelper db;
    private boolean flag_save = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        deviceSelectionList = new ArrayList<>();
        newDeviceList = new ArrayList<>();
        mdeviceSelectionArrayList = new ArrayList<>();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.BLACK);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {
            //statusbar.setVisibility(View.VISIBLE);
        }
        else
        {
            window.setStatusBarColor(Color.BLACK);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try{
                getSupportActionBar().setTitle("BLDC PoC");

            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        newDeviceList = db.getAllNewDevices();
        if(newDeviceList.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No device in database", Toast.LENGTH_LONG).show();
        }
        else if(!newDeviceList.isEmpty()) {
            deviceLayout.setVisibility(View.VISIBLE);
            mSave.setVisibility(View.VISIBLE);
            chekAll.setVisibility(View.VISIBLE);
        }
        mdeviceSelectionArrayList = db.getAllDevices();

        for(int i = 0; i < newDeviceList.size(); i++) {
            for(int j = 0; j < mdeviceSelectionArrayList.size(); j++) {
                System.out.println("inside 2 for ");
                if(newDeviceList.get(i).getDevice().equals(mdeviceSelectionArrayList.get(j).getDevice())) {
                    deviceSelectionList.add(new DeviceSelectionClass(mdeviceSelectionArrayList.get(j).getDevice(), newDeviceList.get(i).getOperation(),mdeviceSelectionArrayList.get(j).getGroup(), mdeviceSelectionArrayList.get(j).ismChecked()));
                    flag = true;
                    break;
                }
                else {
                    flag = false;
                }
            }
            if(flag == false) {
                deviceSelectionList.add(new DeviceSelectionClass(newDeviceList.get(i).getDevice(), newDeviceList.get(i).getOperation(),"", false));
            }
        }
        chekAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                if(chk.isChecked()) {
                    for(int i=0;i<deviceSelectionAdapter.mDeviceList.size();i++) {
                        deviceSelectionAdapter.mDeviceList.get(i).setmChecked(true);
                    }
                }
                else {
                    for(int i=0;i<deviceSelectionAdapter.mDeviceList.size();i++) {
                        deviceSelectionAdapter.mDeviceList.get(i).setmChecked(false);
                    }
                }
                deviceSelectionAdapter.notifyDataSetChanged();
            }
        });
        deviceSelectionAdapter = new DeviceSelectionAdapter(DeviceSelectionActivity.this, deviceSelectionList);
        mDeviceList.setAdapter(deviceSelectionAdapter);
    }
    @OnClick(R.id.saveBut)
    protected void save() {
        db.deleteSelectionDeviceList();
        for(int i=0;i<deviceSelectionAdapter.mDeviceList.size();i++) {
            if(deviceSelectionAdapter.mDeviceList.get(i).ismChecked()==true) {
                db.createDeviceSelection(new DeviceSelectionClass(deviceSelectionAdapter.mDeviceList.get(i).getDevice().trim(), deviceSelectionAdapter.mDeviceList.get(i).getOperation().trim(), deviceSelectionAdapter.mDeviceList.get(i).getGroup().trim(), deviceSelectionAdapter.mDeviceList.get(i).ismChecked()));
//                if(i == deviceSelectionAdapter.mDeviceList.size() - 1) {
//                    Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_SHORT).show();
//                }
                flag_save = false;
            }
            else {
                System.out.println("Hahah");
                flag_save = true;
            }
        }
        try {
            if(flag_save == true) {
                Toast.makeText(getApplicationContext(), "Please select checkbox ", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            case R.id.action_name:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
