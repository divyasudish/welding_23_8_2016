package welding.taal.com.welding_23_08_2016.activities;

import android.app.Activity;
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
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.adapter.FirmwareAdapter;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;
import welding.taal.com.welding_23_08_2016.model.FirmwareClass;

public class UpgradeFirmwareActivity extends AppCompatActivity {

    @Bind(R.id.firmWareList)
    protected ListView mListView;
    @Bind(R.id.spinner)
    protected Spinner spin;
    @Bind(R.id.upgradeBut)
    protected Button upgrade;
    private static final int REQUEST_PICK_FILE = 1;
    private File selectedFile;
    private String status = null;
    private List<DeviceSelectionClass> mList;
    private DatabaseHelper databaseHelper;
    private List<FirmwareClass> adapterList;
    private FirmwareAdapter firmwareAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_firmware);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
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
        databaseHelper = new DatabaseHelper(this);

        mList =  new ArrayList<DeviceSelectionClass>();
        mList = databaseHelper.getAllDevices();
        System.out.println("mList size is " + mList.size());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                int selectedPosition = arg2; //Here is your selected position
                adapterList = new ArrayList<>();
                System.out.println("selected position " + spin.getSelectedItem().toString());
                for (int i = 0; i < mList.size(); i++) {
                    System.out.println("Inside aha");
                    if (mList.get(i).getGroup().equals(spin.getSelectedItem().toString().trim())) {
                        System.out.println("Inside device selection list");
                        adapterList.add(new FirmwareClass(mList.get(i).getDevice(), "", false));
                    }
                }
                //Creating tab menu.
                if(!adapterList.isEmpty()) {
                    mListView.setVisibility(View.VISIBLE);
                    upgrade.setVisibility(View.VISIBLE);
                    firmwareAdapter = new FirmwareAdapter(UpgradeFirmwareActivity.this, adapterList);
                    mListView.setAdapter(firmwareAdapter);
//                    for(int i = 0; i < adapterList.size(); i++) {
//
//                    }
                }
                if(adapterList.isEmpty()) {
                    mListView.setVisibility(View.INVISIBLE);
                    upgrade.setVisibility(View.INVISIBLE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch(requestCode) {
                case REQUEST_PICK_FILE:
                    if(data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {
                        selectedFile = new File(data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                        adapterList.get(firmwareAdapter.pos).setPath(selectedFile.getPath());
                        mListView.setAdapter(null);
                        mListView.setAdapter(firmwareAdapter);
                        System.out.println("Selected path is " + selectedFile + ".." + firmwareAdapter.pos + adapterList.get(firmwareAdapter.pos).getPath());
                    }
                    break;
            }
        }
        catch (Exception e) {

        }
    }
}
