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
    @Bind(R.id.gbrText)
    protected TextView gear;
    @Bind(R.id.bracketText)
    protected TextView band;
    @Bind(R.id.gearText)
    protected TextView wheel;
    @Bind(R.id.goHomeText)
    protected TextView homeText;
    @Bind(R.id.calbrate)
    protected TextView calib;
    @Bind(R.id.Initial)
    protected TextView initial;
    @Bind(R.id.rel)
    protected RelativeLayout rel;

    private DatabaseHelper db;
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

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
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
