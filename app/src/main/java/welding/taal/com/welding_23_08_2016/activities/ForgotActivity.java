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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import welding.taal.com.welding_23_08_2016.R;
import welding.taal.com.welding_23_08_2016.database.DatabaseHelper;
import welding.taal.com.welding_23_08_2016.model.RegistrationClass;

public class ForgotActivity extends AppCompatActivity {
    @Bind(R.id.regBut)
    Button mReg;
    @Bind(R.id.username)
    TextView mName;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.confirmPassword)
    EditText mConfirmPassword;
    private DatabaseHelper db;
    private Boolean flag = false;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");
        System.out.println("Message is " + message);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if ((Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH) || (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT))
        {

        }
        else
        {
            window.setStatusBarColor(Color.BLACK);
        }
        ButterKnife.bind(this);
        mName.setText(message);
        db = new DatabaseHelper(getApplicationContext());

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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.regBut)
    protected void registration() {
        System.out.println("password " + mPassword.getText().toString());
        List<RegistrationClass> allTags = db.getAllToDos();
        for (RegistrationClass tag : allTags) {
            System.out.println("inside tags " + tag.get_id() + "//" + tag.get_userName() + "//" + tag.get_password());
            if (mName.getText().toString().trim().equals(tag.get_userName())) {
                if(mPassword.getText().toString().trim().equals(mConfirmPassword.getText().toString().trim())) {
                    flag = true;
                    db.update_byId(new RegistrationClass(tag.get_id(), tag.get_name().toString().trim(), mPassword.getText().toString().trim(), mConfirmPassword.getText().toString().trim()));
                    Toast.makeText(getApplicationContext(), "Successfully saved in database", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Password and confirm password are not matching", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                flag = false;
            }
        }
    }
}
