package welding.taal.com.welding_23_08_2016.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import welding.taal.com.welding_23_08_2016.model.ConnectionClass;
import welding.taal.com.welding_23_08_2016.model.DeviceClass;
import welding.taal.com.welding_23_08_2016.model.DeviceSelectionClass;
import welding.taal.com.welding_23_08_2016.model.RegistrationClass;

/**
 * Created by Suneesh on 3/26/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "Welding";

    private static final String TABLE_REG = "Registration";
    private static final String KEY_REG_ID = "reg_id";
    private static final String KEY_REG_NAME = "regname";
    private static final String KEY_REG_USERNAME = "regUsername";
    private static final String KEY_REG_PASSWORD = "regPassword";
    private static final String KEY_REG_CONFIRM_PASSWORD = "regConfirmPassword";

    private static final String TABLE_CONNECTION = "Connection";
    private static final String KEY_CON_ID = "con_id";
    private static final String KEY_CON_IP = "con_ip";
    private static final String KEY_CON_PORT = "con_port";
    private static final String KEY_STATE = "con_state";

    private static final String TABLE_DEVICE_NEW = "NewDevice";
    private static final String KEY_NEW_DEVICE_ID = "new_id";
    private static final String KEY_NEW_DEVICE_IP = "new_deviceIp";
    private static final String KEY_DEVICE_NAME = "new_device_name";
    private static final String NEW_DEVICE_OPERATION = "new_device_operation";
    private static final String NEW_DEVICE_STATE = "new_device_state";

    private static final String TABLE_DEVICE_DEVICE_SELECTION = "DeviceSelection";
    private static final String KEY_DEVICE_SELECTION_ID = "device_selection_id";
    private static final String KEY_DEVICE_SELECTION_NAME = "device_selection_name";
    private static final String DEVICE_SELECTION_OPERATION = "device_slection_operation";
    private static final String DEVICE_SELECTION_GROUP = "device_slection_group";
    private static final String DEVICE_SELECTION_STATE = "device_selection_state";


    private static final String CREATE_TABLE_REGISTRATION = "CREATE TABLE "
            + TABLE_REG + "(" + KEY_REG_ID + " INTEGER PRIMARY KEY," + KEY_REG_NAME + " TEXT," + KEY_REG_USERNAME
            + " TEXT," + KEY_REG_PASSWORD + " TEXT," + KEY_REG_CONFIRM_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_CONNECTION = "CREATE TABLE "
            + TABLE_CONNECTION + "(" + KEY_CON_ID + " INTEGER PRIMARY KEY," + KEY_CON_IP + " TEXT," + KEY_CON_PORT + " TEXT," +
            KEY_STATE + " flag INTEGER DEFAULT 0" + ")";


    private static final String CREATE_TABLE_NEW_DEVICE = "CREATE TABLE "
            + TABLE_DEVICE_NEW + "(" + KEY_NEW_DEVICE_ID + " INTEGER PRIMARY KEY," + KEY_NEW_DEVICE_IP + " TEXT," + KEY_DEVICE_NAME + " TEXT," +
            NEW_DEVICE_OPERATION + " TEXT," + NEW_DEVICE_STATE  + " flag INTEGER DEFAULT 0" + ")";

    private static final String CREATE_TABLE_DEVICE_SELECTION = "CREATE TABLE "
            + TABLE_DEVICE_DEVICE_SELECTION + "(" + KEY_DEVICE_SELECTION_ID + " INTEGER PRIMARY KEY," + KEY_DEVICE_SELECTION_NAME + " TEXT," + DEVICE_SELECTION_OPERATION + " TEXT," +
            DEVICE_SELECTION_GROUP + " TEXT," + DEVICE_SELECTION_STATE  + " flag INTEGER DEFAULT 0" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REGISTRATION);
        db.execSQL(CREATE_TABLE_CONNECTION);
        db.execSQL(CREATE_TABLE_NEW_DEVICE);
        db.execSQL(CREATE_TABLE_DEVICE_SELECTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONNECTION);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DEVICE_NEW);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DEVICE_DEVICE_SELECTION);
        onCreate(db);
    }

    public void createReg(RegistrationClass todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REG_NAME, todo.get_name());
        values.put(KEY_REG_USERNAME, todo.get_userName());
        values.put(KEY_REG_PASSWORD, todo.get_password());
        values.put(KEY_REG_CONFIRM_PASSWORD, todo.get_confirmPassword());
        System.out.println("hello" + todo.get_id() + todo.get_name() + todo.get_userName() + todo.get_password());
        // insert row
        db.insert(TABLE_REG, null, values);
        System.out.println("successfully");
    }

    public List<RegistrationClass> getAllToDos() {
        List<RegistrationClass> todos = new ArrayList<RegistrationClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_REG;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                RegistrationClass td = new RegistrationClass();
                td.set_id(c.getInt((c.getColumnIndex(KEY_REG_ID))));
                td.set_name((c.getString(c.getColumnIndex(KEY_REG_NAME))));
                td.set_userName(c.getString(c.getColumnIndex(KEY_REG_USERNAME)));
                td.set_password(c.getString(c.getColumnIndex(KEY_REG_PASSWORD)));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }
    public void update_byId(RegistrationClass todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REG_NAME, todo.get_name());
        values.put(KEY_REG_PASSWORD, todo.get_password());
        values.put(KEY_REG_CONFIRM_PASSWORD, todo.get_confirmPassword());
        System.out.println("Inside Update ");
        db.update(TABLE_REG, values, KEY_REG_ID + "=" + todo.get_id(), null);
    }

    public void createCon(ConnectionClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CON_IP, todo.getmIpAddress());
        values.put(KEY_CON_PORT, todo.getmPort());
        values.put(KEY_STATE, todo.ismCheckBox());
        // insert row
        db.insert(TABLE_CONNECTION, null, values);
        List<ConnectionClass> list = getAllConnection();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getmIpAddress().trim().equals(todo.getmIpAddress().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_CONNECTION, null, values);
        }
        if(flag == true) {
            deleteConnection(todo.getmIpAddress().trim());
            db.insert(TABLE_CONNECTION, null, values);
        }

        System.out.println("successfully" + todo.getmIpAddress());
        System.out.println("successfully");
    }

    public List<ConnectionClass> getAllConnection() {
        List<ConnectionClass> todos = new ArrayList<ConnectionClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONNECTION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ConnectionClass td = new ConnectionClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_CON_ID))));
                td.setmIpAddress((c.getString(c.getColumnIndex(KEY_CON_IP))));
                td.setmPort(c.getString(c.getColumnIndex(KEY_CON_PORT)));
                Boolean flag = (c.getInt(c.getColumnIndex(KEY_STATE)) == 1);
                System.out.println("State is " + flag);
                td.setmCheckBox(flag);
                todos.add(td);
            } while (c.moveToNext());
        }
        return todos;
    }

    public void deleteConnection(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONNECTION,
                KEY_CON_IP + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void deleteConnectionList() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONNECTION, null, null);
        System.out.println("Delete devices successfully ");
    }

    public void createNewDevice(DeviceClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NEW_DEVICE_IP, todo.getIp());
        values.put(KEY_DEVICE_NAME, todo.getDevice());
        values.put(NEW_DEVICE_OPERATION, todo.getOperation());
        values.put(NEW_DEVICE_STATE, todo.ismChecked());
        // insert row
        db.insert(TABLE_DEVICE_NEW, null, values);
        List<DeviceClass> list = getAllNewDevices();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getIp().trim().equals(todo.getIp().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_DEVICE_NEW, null, values);
        }
        if(flag == true) {
            deleteDeviceName(todo.getIp().trim());
            db.insert(TABLE_DEVICE_NEW, null, values);
        }

        System.out.println("successfully" + todo.getIp());
        System.out.println("successfully");
    }

    public List<DeviceClass> getAllNewDevices() {
        List<DeviceClass> todos = new ArrayList<DeviceClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE_NEW;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DeviceClass td = new DeviceClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_NEW_DEVICE_ID))));
                td.setIp((c.getString(c.getColumnIndex(KEY_NEW_DEVICE_IP))));
                td.setDevice(c.getString(c.getColumnIndex(KEY_DEVICE_NAME)));
                td.setOperation(c.getString(c.getColumnIndex(NEW_DEVICE_OPERATION)));
                Boolean flag = (c.getInt(c.getColumnIndex(NEW_DEVICE_STATE)) == 1);
                System.out.println("State is " + flag);
                td.setmChecked(flag);
                todos.add(td);
            } while (c.moveToNext());
        }
        return todos;
    }

    public void deleteDeviceName(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE_NEW,
                KEY_NEW_DEVICE_IP + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }

    public void deleteDeviceList() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE_NEW, null, null);
        System.out.println("Delete devices successfully ");
    }

    public void createDeviceSelection(DeviceSelectionClass todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DEVICE_SELECTION_NAME, todo.getDevice());
        values.put(DEVICE_SELECTION_OPERATION, todo.getOperation());
        values.put(DEVICE_SELECTION_GROUP, todo.getGroup());
        values.put(DEVICE_SELECTION_STATE, todo.ismChecked());
        // insert row
        db.insert(TABLE_DEVICE_DEVICE_SELECTION, null, values);
        List<DeviceSelectionClass> list = getAllDevices();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).getDevice().trim().equals(todo.getDevice().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_DEVICE_DEVICE_SELECTION, null, values);
        }
        if(flag == true) {
            deleteSelectedDeviceName(todo.getDevice().trim());
            db.insert(TABLE_DEVICE_DEVICE_SELECTION, null, values);
        }
        System.out.println("successfully" + todo.getDevice());
    }

    public List<DeviceSelectionClass> getAllDevices() {
        List<DeviceSelectionClass> todos = new ArrayList<DeviceSelectionClass>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEVICE_DEVICE_SELECTION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DeviceSelectionClass td = new DeviceSelectionClass();
                td.setId(c.getInt((c.getColumnIndex(KEY_DEVICE_SELECTION_ID))));
                td.setDevice(c.getString(c.getColumnIndex(KEY_DEVICE_SELECTION_NAME)));
                td.setOperation(c.getString(c.getColumnIndex(DEVICE_SELECTION_OPERATION)));
                td.setGroup(c.getString(c.getColumnIndex(DEVICE_SELECTION_GROUP)));
                Boolean flag = (c.getInt(c.getColumnIndex(DEVICE_SELECTION_STATE)) == 1);
                System.out.println("Group is " + c.getString(c.getColumnIndex(DEVICE_SELECTION_GROUP)));
                td.setmChecked(flag);
                todos.add(td);
            } while (c.moveToNext());
        }
        return todos;
    }

    public void deleteSelectedDeviceName(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE_DEVICE_SELECTION,
                KEY_DEVICE_SELECTION_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }
    public void deleteSelectionDeviceList() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEVICE_DEVICE_SELECTION, null, null);
        System.out.println("Delete selected devices successfully ");
    }
}
