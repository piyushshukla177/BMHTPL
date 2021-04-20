package com.service.bmhtpl.util;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
        @Dao
        public interface ItemsDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(InvoiceTable item);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void update(InvoiceTable item);

        @Delete
        void delete(InvoiceTable item);

        @Query("DELETE FROM invoice_table")
        void deleteAllItems();

        @Query("SELECT * FROM invoice_table")
        List<InvoiceTable> getAllItems();
    }
}
