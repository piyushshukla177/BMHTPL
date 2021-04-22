package com.service.bmhtpl.util;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "invoice_table")
public class InvoiceTable {

    @PrimaryKey
    private int id;
    private String license_id;
    private String vehical_no;
    private String category;
    private String Km_start;
    private String Km_Close;
    private String Km_Run;
    private String Time_Start;
    private String Time_Close;
    private String Out_station;
    private String One_Way;
    private String No_Halt;
    private String Extra_Kms;
    private String Extra_hrs;
    private String km_start_image;
    private String km_close_image;

    public InvoiceTable(int id,String license_id, String vehical_no, String category, String Km_start, String Km_Close, String Km_Run, String Time_Start, String Time_Close,
                        String Out_station, String One_Way, String No_Halt, String Extra_Kms, String Extra_hrs,String km_start_image,String km_close_image) {
        this.id = id;
        this.license_id = license_id;
        this.vehical_no = vehical_no;
        this.category = category;
        this.Km_start = Km_start;
        this.Km_Close = Km_Close;
        this.Km_Run = Km_Run;
        this.Time_Start = Time_Start;
        this.Time_Close = Time_Close;
        this.Out_station = Out_station;
        this.One_Way = One_Way;
        this.No_Halt = No_Halt;
        this.Extra_Kms = Extra_Kms;
        this.Extra_hrs = Extra_hrs;
        this.km_start_image = km_start_image;
        this.km_close_image = km_close_image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLicense_id() {
        return license_id;
    }

    public String getVehical_no() {
        return vehical_no;
    }

    public String getCategory() {
        return category;
    }

    public String getKm_start() {
        return Km_start;
    }

    public String getKm_Close() {
        return Km_Close;
    }

    public String getKm_Run() {
        return Km_Run;
    }

    public String getTime_Start() {
        return Time_Start;
    }

    public String getTime_Close() {
        return Time_Close;
    }

    public String getOut_station() {
        return Out_station;
    }

    public String getOne_Way() {
        return One_Way;
    }

    public String getNo_Halt() {
        return No_Halt;
    }

    public String getExtra_Kms() {
        return Extra_Kms;
    }

    public String getExtra_hrs() {
        return Extra_hrs;
    }

    public String getKm_start_image() {
        return km_start_image;
    }

    public String getKm_close_image() {
        return km_close_image;
    }
}
