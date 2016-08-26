package welding.taal.com.welding_23_08_2016.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.adapter.DeviceInfoAdapter;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;

public class NewDeviceActivity extends AppCompatActivity {
    @Bind(R.id.sub)
    protected Button submit;
    @Bind(R.id.create)
    protected Button mCreate;
    @Bind(R.id.newDeviceList)
    protected ListView mNewDeviceList;
    @Bind(R.id.linear)
    protected LinearLayout ln;
    @Bind(R.id.selectCheck)
    protected CheckBox checkAll;

    private ArrayAdapter<String> adapter;
    private List<String> list;
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private Button createEdit;
    Boolean flag = false;
    Boolean flag_checkAll = false;

    private List<DeviceClass> newDeviceList;
    private DeviceInfoAdapter deviceInfoAdapter;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);
        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        newDeviceList = new ArrayList<>();

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
            ln.setVisibility(View.VISIBLE);
            checkAll.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
        }
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                if (chk.isChecked()) {
                    for (int i = 0; i < deviceInfoAdapter.mDeviceList.size(); i++) {
                        deviceInfoAdapter.mDeviceList.get(i).setmChecked(true);
                    }
                } else {
                    for (int i = 0; i < deviceInfoAdapter.mDeviceList.size(); i++) {
                        deviceInfoAdapter.mDeviceList.get(i).setmChecked(false);
                    }
                }
                deviceInfoAdapter.notifyDataSetChanged();
            }
        });
        deviceInfoAdapter = new DeviceInfoAdapter(NewDeviceActivity.this, newDeviceList);
        mNewDeviceList.setAdapter(deviceInfoAdapter);
    }

    @OnClick(R.id.sub)
    protected void save() {
        db.deleteDeviceList();
        for(int i=0;i<deviceInfoAdapter.mDeviceList.size();i++) {
            if(deviceInfoAdapter.mDeviceList.get(i).ismChecked()==true) {
                Pattern IP_ADDRESS = Pattern.compile(
                        "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                                + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                                + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                                + "|[1-9][0-9]|[0-9]))");
                matcher = IP_ADDRESS.matcher(deviceInfoAdapter.mDeviceList.get(i).getIp().trim());
                if (matcher.matches()) {
                    if(!deviceInfoAdapter.mDeviceList.get(i).getDevice().isEmpty() && !deviceInfoAdapter.mDeviceList.get(i).getIp().isEmpty()){
                        db.createNewDevice(new DeviceClass(deviceInfoAdapter.mDeviceList.get(i).getIp().trim(), deviceInfoAdapter.mDeviceList.get(i).getDevice().trim(), deviceInfoAdapter.mDeviceList.get(i).getOperation().trim(), deviceInfoAdapter.mDeviceList.get(i).ismChecked()));
                        System.out.println("Datas are " + deviceInfoAdapter.mDeviceList.get(i).getDevice());
                        if(i == deviceInfoAdapter.mDeviceList.size() - 1) {
                            Toast.makeText(getApplicationContext(), "Success" , Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please enter device name or Ip", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter valid ip", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(!deviceInfoAdapter.mDeviceList.get(deviceInfoAdapter.mDeviceList.size() - 1).getDevice().isEmpty() && deviceInfoAdapter.mDeviceList.get(deviceInfoAdapter.mDeviceList.size() - 1).ismChecked() == true && matcher.matches()) {
            finish();
            startActivity(new Intent(getApplicationContext(), ConnectionActivity.class));
        }
        else if(deviceInfoAdapter.mDeviceList.get(deviceInfoAdapter.mDeviceList.size() - 1).getDevice().isEmpty() && deviceInfoAdapter.mDeviceList.get(deviceInfoAdapter.mDeviceList.size() - 1).ismChecked() == false) {
            Toast.makeText(getApplicationContext(), "Please enter device details", Toast.LENGTH_SHORT).show();
        }
        else if(deviceInfoAdapter.mDeviceList.get(deviceInfoAdapter.mDeviceList.size() - 1).ismChecked() == false) {
            Toast.makeText(getApplicationContext(), "Please select checkbox", Toast.LENGTH_SHORT).show();
        }


    }
    @OnClick(R.id.create)
    protected void Create() {
        boolean flag = true;
        for(int i=0;i<deviceInfoAdapter.mDeviceList.size();i++) {
            if(deviceInfoAdapter.mDeviceList.get(i).getDevice().equals("")) {
                flag = true;
            }
            else {
                flag = false;
            }
        }
        if(flag == false || deviceInfoAdapter.mDeviceList.isEmpty()) {
            if(checkAll.isChecked()) {
                newDeviceList.add(new DeviceClass("", "", "", true));
            }
            else if(!checkAll.isChecked()) {
                newDeviceList.add(new DeviceClass("", "", "", false));
            }
        }
        checkAll.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
        deviceInfoAdapter.notifyDataSetChanged();
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
