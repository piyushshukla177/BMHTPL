package com.service.bmhtpl.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.service.bmhtpl.model.ProductSqlLiteModel;

import java.util.ArrayList;

/**
 * Created by reale on 15/09/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //Database
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "bmhtpl";

    //Table
    private static final String TABLE_NAME = "INDENT";
    private static final String KEY_ID = "Id";
    private static final String KEY_LICENSE_ID = "License_Id";
    private static final String KEY_VEHICAL_NUMBER = "Vehical_Number";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_KM_START = "Km_start";
    private static final String KEY_KM_CLOSE = "Km_Close";
    private static final String KEY_KM_RUN = "Km_Run";
    private static final String KEY_TIME_START = "Time_Start";
    private static final String KEY_TIME_CLOSE = "Time_Close";
    private static final String KEY_OUT_STATION = "Out_station";
    private static final String KEY_ONE_WAY = "One_Way";
    private static final String KEY_NO_HALT = "No_Halt";
    private static final String KEY_EXTRA_KMS = "Extra_Kms";
    private static final String KEY_EXTRA_HRS = "Extra_hrs";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LICENSE_ID + " TEXT,"
                + KEY_VEHICAL_NUMBER + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_KM_START + " TEXT," + KEY_KM_CLOSE + " TEXT," + KEY_KM_RUN + " TEXT," +
                "" + KEY_TIME_START + " TEXT," +
                KEY_TIME_CLOSE + " TEXT," +
                KEY_OUT_STATION + " TEXT," +
                KEY_ONE_WAY + " TEXT," +
                KEY_NO_HALT + " TEXT," +
                KEY_EXTRA_KMS + " TEXT," +
                KEY_EXTRA_HRS + " TEXT" +

                ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //CRUD Persons
    public void addProduct(ProductSqlLiteModel p) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, p.getLicense_id());
        values.put(KEY_LICENSE_ID, p.getLicense_id());
        values.put(KEY_VEHICAL_NUMBER, p.getVehical_no());
        values.put(KEY_CATEGORY, p.getCategory());
        values.put(KEY_KM_START, p.getKm_start());
        values.put(KEY_KM_CLOSE, p.getKm_Close());
        values.put(KEY_KM_RUN, p.getKm_Run());
        values.put(KEY_TIME_START, p.getTime_Start());
        values.put(KEY_TIME_CLOSE, p.getTime_Close());
        values.put(KEY_OUT_STATION, p.getOut_station());
        values.put(KEY_ONE_WAY, p.getOne_Way());
        values.put(KEY_NO_HALT, p.getNo_Halt());
        values.put(KEY_EXTRA_KMS, p.getExtra_Kms());
        values.put(KEY_EXTRA_HRS, p.getExtra_hrs());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

//    public int updateProductQty(ProductSqlLiteModel p) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_QUNATITY, p.getQuantity());
//        return db.update(TABLE_NAME, values, KEY_ID + " =?", new String[]{String.valueOf(p.getProduct_id())});
//    }
//
//    public void deleteProduct(ProductSqlLiteModel p) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NAME, KEY_ID + " =?", new String[]{String.valueOf(p.getProduct_id())});
//        db.close();
//    }

    public ProductSqlLiteModel getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID, KEY_LICENSE_ID, KEY_VEHICAL_NUMBER, KEY_CATEGORY, KEY_KM_START, KEY_KM_CLOSE, KEY_KM_RUN, KEY_TIME_START,
                        KEY_TIME_CLOSE, KEY_OUT_STATION, KEY_ONE_WAY, KEY_NO_HALT, KEY_EXTRA_KMS, KEY_EXTRA_HRS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ProductSqlLiteModel m = new ProductSqlLiteModel();
        m.setId(cursor.getInt(0));
        m.setLicense_id(cursor.getString(1));
        m.setVehical_no(cursor.getString(2));
        m.setCategory(cursor.getString(3));
        m.setKm_start(cursor.getString(4));
        m.setKm_Close(cursor.getString(5));
        m.setKm_Run(cursor.getString(6));
        m.setTime_Start(cursor.getString(7));
        m.setTime_Close(cursor.getString(8));
        m.setOut_station(cursor.getString(9));
        m.setOne_Way(cursor.getString(10));
        m.setNo_Halt(cursor.getString(11));
        m.setExtra_Kms(cursor.getString(12));
        m.setExtra_hrs(cursor.getString(13));
        return m;
    }

    /*public ArrayList<ProductSqlLiteModel> getAllProducts() {
        ArrayList<ProductSqlLiteModel> lstPersons = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductSqlLiteModel p = new ProductSqlLiteModel();
                p.setProduct_id(String.valueOf(cursor.getInt(0)));
                p.setProduct_name(cursor.getString(1));
                p.setMrp_price(cursor.getString(2));
                p.setSale_price(cursor.getString(3));
                p.setImage_url(cursor.getString(4));
                p.setQuantity(cursor.getString(5));
                p.setStock(cursor.getString(6));
                p.setTotal(cursor.getString(7));
                lstPersons.add(p);
            }
            while (cursor.moveToNext());
        }
        return lstPersons;
    }
*/
    public long getProductCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }

    //Method for Clear value from cart
    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Cart");
        db.execSQL(query);
    }
}
