package com.example.mobileApp.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "responses")   // remove indices and foreign keys
public class ResponseTable {

    @PrimaryKey
    @NonNull
    private Integer index;  // remove auto-increment; remove default = 0

    // this is a foreign key
    //    REQUIRED
    private String patient_id;

    // this is a foreign key
    //    REQUIRED
    private Integer q_id;

    // this is a foreign key
    //    REQUIRED
    private Integer ans_id;

    private String text;

    // this is a foreign key
    //    REQUIRED
    private Integer qnnaire_id;

    // date is in format "YYYY-MM-DD"
    //    REQUIRED
    private String date;

    //    REQUIRED
    // This field is created to support data sync
    // 0 - means this entry is downloaded from server database and will not be synced later
    // 1 - means this entry is newly created on the mobile app and will be synced later
    private Integer isNew;


    public ResponseTable(@NonNull Integer index, String patient_id, Integer q_id, Integer ans_id,
                         String text, Integer qnnaire_id, String date, Integer isNew) {
        this.index = index;
        this.patient_id = patient_id;
        this.q_id = q_id;
        this.ans_id = ans_id;
        this.text = text;
        this.qnnaire_id = qnnaire_id;
        this.date = date;
        this.isNew = isNew;
    }

    /* getter and setter */

    @NonNull
    public Integer getIndex() {
        return index;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public Integer getQ_id() {
        return q_id;
    }

    public Integer getAns_id() {
        return ans_id;
    }

    public String getText() {
        return text;
    }

    public Integer getQnnaire_id() {
        return qnnaire_id;
    }

    public String getDate() {
        return date;
    }

    public Integer getIsNew() {
        return isNew;
    }
}
