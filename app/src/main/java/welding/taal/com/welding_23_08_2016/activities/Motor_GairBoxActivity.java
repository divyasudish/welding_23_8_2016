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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.GearBoxClass;

public class Motor_GairBoxActivity extends AppCompatActivity {
    @Bind(R.id.idTitle)
    protected TextView deviceName;
    @Bind(R.id.setBut)
    protected Button set;
    @Bind(R.id.bId)
    protected EditText bId;
    @Bind(R.id.bOd)
    protected EditText bOd;
    @Bind(R.id.bMd)
    protected EditText bMd;
    @Bind(R.id.pId)
    protected EditText pId;
    @Bind(R.id.pMd)
    protected EditText pMd;
    @Bind(R.id.pOd)
    protected EditText pOd;
    @Bind(R.id.gbrText)
    protected EditText gbrText;
    @Bind(R.id.bracketText)
    protected EditText bracketText;
    @Bind(R.id.gearText)
    protected EditText gearText;
    @Bind(R.id.relative)
    protected RelativeLayout rel;

    private DatabaseHelper databaseHelper;
    private List<GearBoxClass> mList;
    private String mDevice;
    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private String device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor__gair_box);
        ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);
        mList = databaseHelper.getAllGearBoxes();
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
        db = new DatabaseHelper(getApplicationContext());
        mDevice = getIntent().getStringExtra("Device");
        System.out.println("Device name " + mDevice);
        if(!mDevice.isEmpty()) {
            deviceName.setText(mDevice);
            rel.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(getApplicationContext(), "No device in Database", Toast.LENGTH_LONG).show();
        }
        try{
            if(!mList.isEmpty()) {
                for(int i = 0; i < mList.size(); i++) {
                    if(mList.get(i).getDeviceNmae().equals(mDevice)) {
                        bId.setText(mList.get(i).getBandId());
                        bOd.setText(mList.get(i).getBandOd());
                        bMd.setText(mList.get(i).getBandMd());
                        pId.setText(mList.get(i).getPipeId());
                        pOd.setText(mList.get(i).getPipeOd());
                        pMd.setText(mList.get(i).getPipeMd());
                        gbrText.setText(mList.get(i).getGbr());
                        bracketText.setText(mList.get(i).getBandDia());
                        gearText.setText(mList.get(i).getGearwheelDia());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    @OnClick(R.id.setBut)
    protected void set() {
        if(!(bId.getText().toString().trim().isEmpty()) && !(bOd.getText().toString().trim().isEmpty() && !(bMd.getText().toString().trim().isEmpty())
                && !(pId.getText().toString().trim().isEmpty()) && !(pOd.getText().toString().trim().isEmpty()) && !(pMd.getText().toString().trim().isEmpty()) && !(gbrText.getText().toString().trim().isEmpty())
                && !(bracketText.getText().toString().trim().isEmpty()) && !(gearText.getText().toString().isEmpty()))) {
            db.createGearBox(new GearBoxClass(mDevice, bId.getText().toString().trim(), bOd.getText().toString().trim(), bMd.getText().toString().trim(), pId.getText().toString().trim(),pOd.getText().toString().trim(), pMd.getText().toString().trim(), gbrText.getText().toString().trim(), bracketText.getText().toString().trim(), gearText.getText().toString().trim()));
        }

        bId.setEnabled(false);
        bMd.setEnabled(false);
        bOd.setEnabled(false);
        pId.setEnabled(false);
        pMd.setEnabled(false);
        pOd.setEnabled(false);
        gbrText.setEnabled(false);
        bracketText.setEnabled(false);
        gearText.setEnabled(false);
        set.setEnabled(false);

        finish();
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
    protected void onStart() {
        super.onStart();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
