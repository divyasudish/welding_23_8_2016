package welding.taal.com.welding_23_08_2016.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;

public class DeviceSelectionActivity extends AppCompatActivity {

    @Bind(R.id.saveBut)
    protected Button mSave;
    @Bind(R.id.deviceSelectionList)
    protected ListView mDeviceList;
    @Bind(R.id.linear)
    protected LinearLayout ln;

    private List<DeviceSelectionClass> deviceSelectionList;
    private List<DeviceClass> newDeviceList;
    private DeviceSelectionAdapter deviceSelectionAdapter;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        deviceSelectionList = new ArrayList<>();
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
            mSave.setVisibility(View.VISIBLE);
        }
        for(int i = 0; i < newDeviceList.size(); i++) {
            deviceSelectionList.add(new DeviceSelectionClass(newDeviceList.get(i).getDevice(), newDeviceList.get(i).getOperation(),"", newDeviceList.get(i).ismChecked()));
        }
        deviceSelectionAdapter = new DeviceSelectionAdapter(DeviceSelectionActivity.this, deviceSelectionList);
        mDeviceList.setAdapter(deviceSelectionAdapter);

    }
    @OnClick(R.id.saveBut)
    protected void save() {
        db.deleteSelectionDeviceList();
        for(int i=0;i<deviceSelectionAdapter.mDeviceList.size();i++) {
            if(deviceSelectionAdapter.mDeviceList.get(i).ismChecked()==true) {
                db.createDeviceSelection(new DeviceSelectionClass(deviceSelectionAdapter.mDeviceList.get(i).getDevice().trim(), deviceSelectionAdapter.mDeviceList.get(i).getOperation().trim(), deviceSelectionAdapter.mDeviceList.get(i).getGroup().trim(), deviceSelectionAdapter.mDeviceList.get(i).ismChecked()));
                System.out.println("Datas are " + deviceSelectionAdapter.mDeviceList.get(i).getDevice());
            }
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
