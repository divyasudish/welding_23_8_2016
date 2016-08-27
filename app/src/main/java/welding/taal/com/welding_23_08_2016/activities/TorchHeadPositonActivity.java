package welding.taal.com.welding_23_08_2016.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;

public class TorchHeadPositonActivity extends Activity {
    @Bind(R.id.startProgress)
    protected Button start;
    @Bind(R.id.stopProgress)
    protected Button stop;
    @Bind(R.id.vel)
    protected Button velocityMov;
    @Bind(R.id.posMove)
    protected Button posMove;
    @Bind(R.id.solenoidBut)
    protected Button solenoidBut;
    @Bind(R.id.GoHome)
    protected Button goHome;
    @Bind(R.id.accelX)
    protected TextView accelX;
    @Bind(R.id.accelY)
    protected TextView accelY;
    @Bind(R.id.accelZ)
    protected TextView accelZ;
    @Bind(R.id.gyroX)
    protected TextView gyroX;
    @Bind(R.id.gyroY)
    protected TextView gyroY;
    @Bind(R.id.gyroZ)
    protected TextView gyroZ;
    @Bind(R.id.magX)
    protected TextView magnetoX;
    @Bind(R.id.magY)
    protected TextView magnetoY;
    @Bind(R.id.magZ)
    protected TextView magnetoZ;
    @Bind(R.id.baro)
    protected TextView baro;
    @Bind(R.id.temp)
    protected TextView temp;
    @Bind(R.id.homePos)
    protected TextView homepos;
    @Bind(R.id.relText)
    protected TextView relEncoder;
    @Bind(R.id.absText)
    protected TextView absEncoder;
    @Bind(R.id.velocityTextView)
    protected TextView velocityTextView;
    @Bind(R.id.pos)
    protected TextView posTextView;
    @Bind(R.id.degree)
    protected TextView degreeText;
    @Bind(R.id.velocity)
    protected TextView Veloc;
    @Bind(R.id.ramp)
    protected TextView ramp;
    @Bind(R.id.direction)
    protected TextView Direction;
    @Bind(R.id.posText)
    protected EditText pos;
    @Bind(R.id.velocityText)
    protected EditText vel;
    @Bind(R.id.rampText)
    protected EditText RampText;

    private DatabaseHelper db;
    private List<DeviceClass> newList;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torch_head_positon);
        ButterKnife.bind(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)) {

        } else {
            window.setStatusBarColor(Color.BLACK);
        }
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
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
