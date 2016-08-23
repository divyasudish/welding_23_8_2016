package welding.taal.com.welding_23_08_2016.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.adapter.ConnectionAdapter;
import welding.taal.com.welding_23_08_2016.model.ConnectionClass;

public class ConnectionActivity extends AppCompatActivity {
    @Bind(R.id.connectBut)
    protected Button mConnect;
    @Bind(R.id.mainMenuBut)
    protected Button mMainMenu;
    @Bind(R.id.connectionList)
    protected ListView mConnectionList;

    private List<ConnectionClass> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

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
        init();
        mConnectionList.setAdapter(new ConnectionAdapter(ConnectionActivity.this, mList));
    }
    private void init() {
        mList.add(new ConnectionClass(false, "192.168.0.22", "20108"));
        mList.add(new ConnectionClass(false, "192.168.0.23", "20109"));
        mList.add(new ConnectionClass(false, "192.168.0.24", "20107"));
        mList.add(new ConnectionClass(false, "192.168.0.25", "20106"));
    }
    @OnClick(R.id.connectBut)
    protected void connection() {

    }
    @OnClick(R.id.mainMenuBut)
    protected void mainMenu() {
        startActivity(new Intent(ConnectionActivity.this, MainMenuActivity.class));
    }
}
