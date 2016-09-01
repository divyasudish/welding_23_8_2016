package welding.taal.com.welding_23_08_2016.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.Connection.Connect;
import welding.taal.com.welding_23_08_2016.Connection.ConnectConfiguration;
import welding.taal.com.welding_23_08_2016.Connection.ConnectListener;
import welding.taal.com.welding_23_08_2016.Connection.ConnectManager;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.adapter.ConnectionAdapter;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.ConnectionClass;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;

public class ConnectionActivity extends AppCompatActivity implements ConnectListener {
    @Bind(R.id.connectBut)
    protected Button mConnect;
    @Bind(R.id.mainMenuBut)
    protected Button mMainMenu;
    @Bind(R.id.connectionList)
    protected ListView mConnectionList;
    @Bind(R.id.selectCheck)
    protected CheckBox checkAll;
    @Bind(R.id.ipAddress)
    protected TextView ipAddress;
    @Bind(R.id.portNumber)
    protected TextView portNumber;
    @Bind(R.id.connectScreenLayout)
    protected LinearLayout connectLayout;
    private DatabaseHelper db;
    private List<ConnectionClass> mList;
    private List<DeviceClass> mNewDeviceList;
    private List<ConnectionClass> mConnectionArrayList;
    private ConnectionAdapter connectionAdapter;
    private boolean flag = false;
    private Connect connect;

    public static final ConnectManager connectManager  = ConnectManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        ButterKnife.bind(this);
        db = new DatabaseHelper(getApplicationContext());
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
            try{
                getSupportActionBar().setTitle("BLDC PoC");
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {

        }
        else
        {
            window.setStatusBarColor(Color.BLACK);
        }
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        mNewDeviceList = new ArrayList<>();
        mConnectionArrayList = new ArrayList<>();
        mConnectionArrayList = db.getAllConnection();
        mNewDeviceList = db.getAllNewDevices();
        if(mNewDeviceList.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No device in database ", Toast.LENGTH_SHORT).show();
        }
        if(!mNewDeviceList.isEmpty()) {
            mConnect.setVisibility(View.VISIBLE);
            checkAll.setVisibility(View.VISIBLE);
            connectLayout.setVisibility(View.VISIBLE);
        }
        System.out.println("Size " + mConnectionArrayList.size());
        for(int i = 0; i < mNewDeviceList.size(); i++) {
            for(int j = 0; j < mConnectionArrayList.size(); j++) {
                System.out.println("inside 2 for ");
                if(mNewDeviceList.get(i).getIp().equals(mConnectionArrayList.get(j).getmIpAddress())) {
                    mList.add(new ConnectionClass(mConnectionArrayList.get(j).ismCheckBox(), mConnectionArrayList.get(j).getmIpAddress(), mConnectionArrayList.get(j).getmPort()));
                    flag = true;
                    break;
                }
                else {
                    flag = false;
                }
            }
            if(flag == false) {
                mList.add(new ConnectionClass(false, mNewDeviceList.get(i).getIp(), " "));
            }

        }
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox chk = (CheckBox) v;
                if(chk.isChecked()) {
                    for(int i=0;i<connectionAdapter.mList.size();i++) {
                        connectionAdapter.mList.get(i).setmCheckBox(true);
                    }
                }
                else {
                    for(int i=0;i<connectionAdapter.mList.size();i++) {
                        connectionAdapter.mList.get(i).setmCheckBox(false);
                    }
                }
                connectionAdapter.notifyDataSetChanged();
            }
        });
        connectionAdapter = new ConnectionAdapter(ConnectionActivity.this, mList);
        mConnectionList.setAdapter(connectionAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            System.out.println("Inside back button ");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.connectBut)
    protected void connection() {
        for(int i=0;i<connectionAdapter.mList.size();i++) {
            if (connectionAdapter.mList.get(i).ismCheckBox() == true) {
                connect =  connectManager.createTcpClient(connectionAdapter.mList.get(i).getmIpAddress(), Integer.parseInt(connectionAdapter.mList.get(i).getmPort()), this);
                //Toast.makeText(getApplicationContext(), connectionAdapter.mList.get(i).getmIpAddress() + ".." + connectionAdapter.mList.get(i).getmPort(), Toast.LENGTH_LONG).show();
                db.createCon(new ConnectionClass(connectionAdapter.mList.get(i).ismCheckBox(), connectionAdapter.mList.get(i).getmIpAddress(), connectionAdapter.mList.get(i).getmPort()));
            }
        }
    }
    @OnClick(R.id.mainMenuBut)
    protected void mainMenu() {
        db.deleteConnectionList();
        for(int i=0;i<connectionAdapter.mList.size();i++) {
            if(connectionAdapter.mList.get(i).ismCheckBox()==true) {
                db.createCon(new ConnectionClass(connectionAdapter.mList.get(i).ismCheckBox(), connectionAdapter.mList.get(i).getmIpAddress(), connectionAdapter.mList.get(i).getmPort()));
                System.out.println("Connection list are " + connectionAdapter.mList.get(i).getmIpAddress());
            }
        }
        startActivity(new Intent(ConnectionActivity.this, MainMenuActivity.class));
    }

    @Override
    public void connectSuccess(ConnectConfiguration configuration) {
        System.out.println("------------------>connectSuccess");
        String x = "{ewe}";
        connect.send(x.getBytes());
        Toast.makeText(getApplicationContext(), "Connection success ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void connectBreak(ConnectConfiguration configuration) {
        System.out.println("------------------>connectBreak");
    }
    @Override
    public void onReceviceData(ConnectConfiguration configuration, byte[] data) {
        try {
            String strMsg = new String(data, "UTF-8");
            Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ConnectionActivity.this, MainMenuActivity.class));
        }
        catch (Exception e) {

        }
    }
}
