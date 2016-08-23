package welding.taal.com.welding_23_08_2016.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

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

    private ArrayAdapter<String> adapter;
    private List<String> list;
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private Button createEdit;
    Boolean flag = false;

    private ArrayList<DeviceClass> newDeviceList;
    private DeviceInfoAdapter deviceInfoAdapter;

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
        init();
        deviceInfoAdapter = new DeviceInfoAdapter(NewDeviceActivity.this, newDeviceList);
        mNewDeviceList.setAdapter(deviceInfoAdapter);
    }

    private void init() {
        newDeviceList.add(new DeviceClass("192.168.0.22", "Crc_evans", "kkk", false));
    }

    @OnClick(R.id.sub)
    protected void submit(){

    }
    @OnClick(R.id.create)
    protected void Create() {
        newDeviceList.add(new DeviceClass("", "", "", false));
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
