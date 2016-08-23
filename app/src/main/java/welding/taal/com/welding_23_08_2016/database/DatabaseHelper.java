package welding.taal.com.welding_23_08_2016.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import welding.taal.com.welding_23_08_2016.model.RegistrationClass;

/**
 * Created by Suneesh on 3/26/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Welding";

    private static final String TABLE_REG = "Registration";
    private static final String KEY_REG_ID = "reg_id";
    private static final String KEY_REG_NAME = "regname";
    private static final String KEY_REG_USERNAME = "regUsername";
    private static final String KEY_REG_PASSWORD = "regPassword";
    private static final String KEY_REG_CONFIRM_PASSWORD = "regConfirmPassword";

    private static final String TABLE_DEVICE_NEW = "NewDevice";
    private static final String KEY_NEW_DEVICE_ID = "new_id";
    private static final String KEY_NEW_DEVICE_IP = "new_deviceIp";
    private static final String KEY_DEVICE_NAME = "new_device_name";
    private static final String NEW_DEVICE_OPERATION = "new_device_operation";

    private static final String TABLE_MACHINE_DEVICE_NEWDEVICE_NEW = "M_NewDevice";
    private static final String KEY_M_NEW_DEVICE_ID = "m_new_id";
    private static final String KEY_M_NEW_DEVICE_IP = "m_new_deviceIp";
    private static final String KEY_M_DEVICE_NAME = "m_new_device_name";
    private static final String NEW_M_DEVICE_OPERATION = "m_new_device_operation";

    private static final String TABLE_GEARBOX = "GearBox";
    private static final String KEY_GEARBOX_ID = "gearBox_id";
    private static final String KEY_Gear_DEVICE_NAME = "deviceName";
    private static final String KEY_band_id = "bandId";
    private static final String KEY_band_od = "bandOd";
    private static final String KEY_band_md = "bandMd";
    private static final String KEY_pipe_id = "pipeId";
    private static final String KEY_pipe_od = "pipeOd";
    private static final String KEY_pipe_md = "pipeMd";
    private static final String KEY_GEARBOX_RATIO = "gbr";
    private static final String KEY_BAND_DIA = "band_dia";
    private static final String KEY_GEAR_WHEEL_DIA = "gear_wheel_dia";

    private static final String TABLE_SENSOR = "Sensor";
    private static final String KEY_Sensor_ID = "sensor_id";
    private static final String KEY_Sensor_DEVICE_NAME = "sensordeviceName";
    private static final String KEY_accX = "accX";
    private static final String KEY_accY = "accY";
    private static final String KEY_accZ = "accZ";
    private static final String KEY_gyroX = "gyroX";
    private static final String KEY_gyroY = "gyroY";
    private static final String KEY_gyroZ = "gyroZ";
    private static final String KEY_magXX = "magX";
    private static final String KEY_magY = "magY";
    private static final String KEY_magZ = "magZ";
    private static final String KEY_SENSOR_GBR = "gbr";
    private static final String KEY_Sen_BAND_DIA = "bandDia";
    private static final String KEY_SENSOR_GEAR_WHEEL_DIA = "gearwheelDia";
    private static final String KEY_SEN_FULL_CYCLE = "fullcycle";
    private static final String KEY_SEN_CIRCUM = "circum";
    private static final String KEY_SEN_RPM = "rpm";


    private static final String TABLE_DATALOG = "DAtalog";
    private static final String KEY_Datalog_ID = "datalog_id";
    private static final String KEY_Datalog_DEVICE_NAME = "sensordeviceName";
    private static final String KEY_Data_accX = "D_accX";
    private static final String KEY_Data_accY = "D_accY";
    private static final String KEY_Data_accZ = "D_accZ";
    private static final String KEY_Data_gyroX = "D_gyroX";
    private static final String KEY_Data_gyroY = "D_gyroY";
    private static final String KEY_Data_gyroZ = "D_gyroZ";
    private static final String KEY_Data_magXX = "D_magX";
    private static final String KEY_Data_magY = "D_magY";
    private static final String KEY_Data_magZ = "D_magZ";
    private static final String KEY_Data_baro = "D_baro";
    private static final String KEY_Data_temp = "D_temp";
    private static final String KEY_Home_position = "D_Home_position";
    private static final String KEY_Data_rel = "D_rel";
    private static final String KEY_Data_abs = "D_Abs";


    private static final String TABLE_DEVICE_INFO = "DeviceInfo";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "devicename";
    private static final String KEY_ACTIVITY = "deviceActivity";
    private static final String KEY_EDIT = "deviceActivityEdit";

    private static final String TABLE_MACHINE_INFO = "MachineInfo";
    private static final String KEY_MACHINE_ID = "mid";
    private static final String KEY_MACHINE_NAME = "machinename";
    private static final String KEY_MACHINE_DEVICE_NAME = "machineDevice";
    private static final String KEY_MACHINE_EDIT = "machineEdit";

    private static final String TABLE_MACHINE_PROGRESS_List = "MachineProgressList";
    private static final String KEY_MACHINE_PROGRESS_List_ID = "machinepid";
    private static final String KEY_MACHINE_PROGRESS_LIST_DEVICE_NAME = "machineListDevicename";
    private static final String KEY_MACHINE_PROGRESSLIST_ACCX = "machineListAccX";
    private static final String KEY_MACHINE_PROGRESSLIST_ACCY = "machineListAccY";
    private static final String KEY_MACHINE_PROGRESSLIST_ACCZ = "machineListAccZ";
    private static final String KEY_MACHINE_PROGRESSLIST_GYROX = "machineListGyroX";
    private static final String KEY_MACHINE_PROGRESSLIST_GYROY= "machineListGyroY";
    private static final String KEY_MACHINE_PROGRESSLIST_GYROZ = "machineListGyroZ";
    private static final String KEY_MACHINE_PROGRESSLIST_MAGX = "machineListMagX";
    private static final String KEY_MACHINE_PROGRESSLIST_MAGY = "machineListMagY";
    private static final String KEY_MACHINE_PROGRESSLIST_MAGZ = "machineListMagZ";
    private static final String KEY_MACHINE_PROGRESSLIST_Baro = "machineListBaro";
    private static final String KEY_MACHINE_PROGRESSLIST_Temp = "machineListTemp";
    private static final String KEY_MACHINE_PROGRESSLIST_ActualVel = "machineListActualVelocity";
    private static final String KEY_MACHINE_PROGRESSLIST_RelEncoder = "machineListRelEncoder";
    private static final String KEY_MACHINE_PROGRESSLIST_AbsEncoder = "machineListAbsEncoder";

    private static final String TABLE_MACHINE_PROGRESS = "MachineProgress";
    private static final String KEY_MACHINE_PROGRESS_ID = "mpid";
    private static final String KEY_MACHINE_P_DEVICE_NAME = "machineDevicename";
    private static final String KEY_MACHINE_Pos = "machinePos";
    private static final String KEY_MACHINE_Velocity = "machineVelocity";
    private static final String KEY_MACHINE_ABS_PULSE = "abdpulse";


    private static final String CREATE_TABLE_REGISTRATION = "CREATE TABLE "
            + TABLE_REG + "(" + KEY_REG_ID + " INTEGER PRIMARY KEY," + KEY_REG_NAME + " TEXT," + KEY_REG_USERNAME
            + " TEXT," + KEY_REG_PASSWORD + " TEXT," + KEY_REG_CONFIRM_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_NEW_DEVICE = "CREATE TABLE "
            + TABLE_DEVICE_NEW + "(" + KEY_NEW_DEVICE_ID + " INTEGER PRIMARY KEY," + KEY_NEW_DEVICE_IP + " TEXT," + KEY_DEVICE_NAME + " TEXT," +
            NEW_DEVICE_OPERATION + " TEXT" + ")";

    private static final String CREATE_TABLE_MACHINE_NEW_DEVICE = "CREATE TABLE "
            + TABLE_MACHINE_DEVICE_NEWDEVICE_NEW + "(" + KEY_M_NEW_DEVICE_ID + " INTEGER PRIMARY KEY," + KEY_M_NEW_DEVICE_IP + " TEXT," + KEY_M_DEVICE_NAME + " TEXT," +
            NEW_M_DEVICE_OPERATION + " TEXT" + ")";

    private static final String CREATE_TABLE_GEARBOX = "CREATE TABLE "
            + TABLE_GEARBOX + "(" + KEY_GEARBOX_ID + " INTEGER PRIMARY KEY," + KEY_Gear_DEVICE_NAME + " TEXT," + KEY_band_id + " TEXT," +
            KEY_band_od + " TEXT," + KEY_band_md + " TEXT," + KEY_pipe_id + " TEXT," +
            KEY_pipe_od + " TEXT," + KEY_pipe_md + " TEXT," + KEY_GEARBOX_RATIO + " TEXT," +
            KEY_BAND_DIA + " TEXT," + KEY_GEAR_WHEEL_DIA + " TEXT" + ")";

    private static final String CREATE_TABLE_SENSOR = "CREATE TABLE "
            + TABLE_SENSOR + "(" + KEY_Sensor_ID + " INTEGER PRIMARY KEY," + KEY_Sensor_DEVICE_NAME + " TEXT," +
            KEY_accX + " TEXT," + KEY_accY + " TEXT," + KEY_accZ + " TEXT," +
            KEY_gyroX + " TEXT," + KEY_gyroY + " TEXT," + KEY_gyroZ + " TEXT," +
            KEY_magXX + " TEXT," + KEY_magY + " TEXT," + KEY_magZ + " TEXT," +
            KEY_SENSOR_GBR + " TEXT," + KEY_Sen_BAND_DIA + " TEXT," + KEY_SENSOR_GEAR_WHEEL_DIA + " TEXT," +
            KEY_SEN_CIRCUM + " TEXT," + KEY_SEN_FULL_CYCLE + " TEXT," + KEY_SEN_RPM + " TEXT" + ")";

    private static final String CREATE_TABLE_DATALOG = "CREATE TABLE "
            + TABLE_DATALOG + "(" + KEY_Datalog_ID + " INTEGER PRIMARY KEY," + KEY_Datalog_DEVICE_NAME + " TEXT," +
            KEY_Data_accX + " TEXT," + KEY_Data_accY + " TEXT," + KEY_Data_accZ + " TEXT," +
            KEY_Data_gyroX + " TEXT," + KEY_Data_gyroY + " TEXT," + KEY_Data_gyroZ + " TEXT," +
            KEY_Data_magXX + " TEXT," + KEY_Data_magY + " TEXT," + KEY_Data_magZ + " TEXT," +
            KEY_Data_baro + " TEXT," + KEY_Data_temp + " TEXT," + KEY_Home_position + " TEXT," +
            KEY_Data_rel + " TEXT," + KEY_Data_abs + " TEXT" + ")";


    private static final String CREATE_TABLE_DEVICE = "CREATE TABLE "
            + TABLE_DEVICE_INFO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_ACTIVITY
            + " TEXT," + KEY_EDIT + " TEXT" + ")";

    private static final String CREATE_TABLE_MACHINE = "CREATE TABLE "
            + TABLE_MACHINE_INFO + "(" + KEY_MACHINE_ID + " INTEGER PRIMARY KEY," + KEY_MACHINE_NAME + " TEXT," + KEY_MACHINE_DEVICE_NAME
            + " TEXT," + KEY_MACHINE_EDIT + " TEXT"+ ")";

    private static final String CREATE_TABLE_Machine_Progress = "CREATE TABLE "
            + TABLE_MACHINE_PROGRESS + "(" + KEY_MACHINE_PROGRESS_ID + " INTEGER PRIMARY KEY," + KEY_MACHINE_P_DEVICE_NAME + " TEXT," + KEY_MACHINE_Pos
            + " TEXT," + KEY_MACHINE_Velocity + " TEXT," + KEY_MACHINE_ABS_PULSE + " TEXT" + ")";

    private static final String CREATE_TABLE_MACHINEPROGRESS_LIST = "CREATE TABLE "
            + TABLE_MACHINE_PROGRESS_List + "(" + KEY_MACHINE_PROGRESS_List_ID + " INTEGER PRIMARY KEY," + KEY_MACHINE_PROGRESS_LIST_DEVICE_NAME + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_ACCX + " TEXT," + KEY_MACHINE_PROGRESSLIST_ACCY + " TEXT," + KEY_MACHINE_PROGRESSLIST_ACCZ + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_GYROX + " TEXT," + KEY_MACHINE_PROGRESSLIST_GYROY + " TEXT," + KEY_MACHINE_PROGRESSLIST_GYROZ + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_MAGX + " TEXT," + KEY_MACHINE_PROGRESSLIST_MAGY + " TEXT," + KEY_MACHINE_PROGRESSLIST_MAGZ + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_Baro + " TEXT," + KEY_MACHINE_PROGRESSLIST_Temp + " TEXT," + KEY_MACHINE_PROGRESSLIST_ActualVel + " TEXT," +
            KEY_MACHINE_PROGRESSLIST_RelEncoder + " TEXT," + KEY_MACHINE_PROGRESSLIST_AbsEncoder + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REGISTRATION);
//        db.execSQL(CREATE_TABLE_DEVICE);
//        db.execSQL(CREATE_TABLE_MACHINE_NEW_DEVICE);
//        db.execSQL(CREATE_TABLE_MACHINE);
//        db.execSQL(CREATE_TABLE_NEW_DEVICE);
//        db.execSQL(CREATE_TABLE_GEARBOX);
//        db.execSQL(CREATE_TABLE_SENSOR);
//        db.execSQL(CREATE_TABLE_DATALOG);
//        db.execSQL(CREATE_TABLE_Machine_Progress);
//        db.execSQL(CREATE_TABLE_MACHINEPROGRESS_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REG);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVICE_INFO);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MACHINE_INFO);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DEVICE_NEW);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_GEARBOX);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SENSOR);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MACHINE_DEVICE_NEWDEVICE_NEW);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_DATALOG);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MACHINE_PROGRESS);
//        db.execSQL("DROP TABLE IF EXISTS" + TABLE_MACHINE_PROGRESS_List);
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
}
