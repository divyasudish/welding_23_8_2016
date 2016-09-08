package welding.taal.com.welding_23_08_2016.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import welding.taal.com.welding_23_08_2016.model.GearBoxClass;

public class SensorActivity extends Activity {
    @Bind(R.id.SetForwordHome)
    protected Button setForward;
    @Bind(R.id.setBackward)
    protected Button setBackward;
    @Bind(R.id.calibrateBut)
    protected Button calibBut;
    @Bind(R.id.save)
    protected Button saveBut;
    @Bind(R.id.stopSensor)
    protected Button stopSens;
    @Bind(R.id.Gyro)
    protected Button gyroBut;
    @Bind(R.id.accX)
    protected TextView accX;
    @Bind(R.id.accY)
    protected TextView accY;
    @Bind(R.id.accZ)
    protected TextView accZ;
    @Bind(R.id.gyroX)
    protected TextView gyroX;
    @Bind(R.id.gyroY)
    protected TextView gyroY;
    @Bind(R.id.gyroZ)
    protected TextView gyroZ;
    @Bind(R.id.magX)
    protected TextView magX;
    @Bind(R.id.magY)
    protected TextView magY;
    @Bind(R.id.magZ)
    protected TextView magZ;
    @Bind(R.id.goHomeText)
    protected TextView homeText;
    @Bind(R.id.calbrate)
    protected TextView calib;
    @Bind(R.id.Initial)
    protected TextView initial;
    @Bind(R.id.rel)
    protected RelativeLayout rel;
    public static DatabaseHelper db;
    private Intent intent;
    private static String mDevice;
    public static List<GearBoxClass> mListGear;
    public static Handler UIHandler;
    private static TextView gear;
    private static TextView band;
    private static TextView wheel;
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
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
        rel.setVisibility(View.VISIBLE);
        db = new DatabaseHelper(getApplicationContext());
        UIHandler = new Handler(Looper.getMainLooper());
        try {
            mListGear = db.getAllGearBoxes();
        }
        catch (Exception e) {

        }
        gear = (TextView) findViewById(R.id.gbrText);
        band = (TextView) findViewById(R.id.bracketText);
        wheel = (TextView) findViewById(R.id.gearText);
        gear.setText("0");
        band.setText("0");
        wheel.setText("0");
    }
    @OnClick(R.id.Gyro)
    protected void gearbox() {
        Intent i = new Intent(this,  Motor_GairBoxActivity.class);
        i.putExtra("Device", mDevice);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        System.out.println("Inside pause thread");
        super.onPause();
    }
    @Override
    protected void onRestart() {
        System.out.println("Inside restart thread");
        super.onRestart();
    }
    @Override
    protected void onStop() {
        System.out.println("Inside stop thread");
        super.onStop();
    }
    @Override
    protected void onStart() {
        System.out.println("Inside start thread ffgggg");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d("ChangeStateInfo", "onResume");
        super.onResume();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        System.out.println("before refresh");
                        try {
                            UIHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        mListGear = db.getAllGearBoxes();
                                        if (!mListGear.isEmpty()) {
                                            mDevice = CalibrationMainActivity.devname;
                                            System.out.println("Inside resume thread iii" + mDevice);
                                            for (int i = 0; i < mListGear.size(); i++) {
                                                if (mListGear.get(i).getDeviceNmae().equals(mDevice)) {
                                                    System.out.println("Inside for " + mListGear.get(i).getDeviceNmae() + "//" + mDevice + "//" + mListGear.get(i).getGbr());
                                                    System.out.println("Inside for display" + mListGear.get(i).getDeviceNmae() + "../" + mDevice + ".../" + mListGear.get(i).getGbr());
                                                    gear.setText(mListGear.get(i).getGbr());
                                                    band.setText(mListGear.get(i).getBandDia());
                                                    wheel.setText(mListGear.get(i).getGearwheelDia());
                                                    break;
                                                }
                                            }
                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            });
                        }
                        catch (Exception e) {

                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }

    @Override
    protected void onDestroy() {
        System.out.println("Inside destroy thread");
        super.onDestroy();
    }
}
