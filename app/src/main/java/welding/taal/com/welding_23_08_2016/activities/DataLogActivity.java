package welding.taal.com.welding_23_08_2016.activities;

import android.app.Activity;
import android.app.LocalActivityManager;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;

public class DataLogActivity extends AppCompatActivity {
    @Bind(R.id.spinner)
    protected Spinner spinner;
    @Bind(R.id.tabLayout)
    protected LinearLayout tabLayout;
    private LocalActivityManager mlam;
    private TabHost TabHostWindow;
    private DatabaseHelper db;
    private List<DeviceSelectionClass> deviceSelectionList;
    private List<String> tablist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mlam = new LocalActivityManager(this, false);
        mlam.dispatchCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log_new);
        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        deviceSelectionList = new ArrayList<>();
        deviceSelectionList = db.getAllDevices();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {

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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        TabHostWindow = (TabHost)findViewById(android.R.id.tabhost);
        TabHostWindow.setup(mlam);
        TabHostWindow.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int selectedPosition = arg2; //Here is your selected position
                tablist =  new ArrayList<>();
                TabHostWindow.getTabWidget().removeAllViews();
                System.out.println("selected position " + spinner.getSelectedItem().toString());
                for (int i = 0; i < deviceSelectionList.size(); i++) {
                    System.out.println("Inside aha");
                    if (deviceSelectionList.get(i).getGroup().equals(spinner.getSelectedItem().toString().trim())) {
                        System.out.println("Inside device selection list");
                        tablist.add(deviceSelectionList.get(i).getDevice());
                    }
                }
                //Creating tab menu.
                if(!tablist.isEmpty()) {
                    tabLayout.setVisibility(View.VISIBLE);
                    for(int i = 0; i < tablist.size(); i++) {
                        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec(tablist.get(i));
                        TabMenu1.setIndicator(tablist.get(i));
                        TabMenu1.setContent(new Intent(getApplicationContext(), DataLogMainActivity.class));
                        TabHostWindow.addTab(TabMenu1);
                    }
                }
                if(tablist.isEmpty()) {
                    tabLayout.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "No device", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

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
