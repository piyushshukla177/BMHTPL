package com.service.bmhtpl.util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {InvoiceTable.class}, version = 1)
public abstract class InvoiceDb extends RoomDatabase {
    private static InvoiceDb instance;

    public abstract InvoiceDAO.ItemsDao ItemsDao();
//    public abstract AdditionalDao AdditionalDao();

    public static synchronized InvoiceDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    InvoiceDb.class, "invoice_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}