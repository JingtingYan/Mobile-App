package com.example.mobileApp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mobileApp.database.dao.PatientAssessmentStatusDao;
import com.example.mobileApp.database.entity.PatientAssessmentStatusTable;
import com.example.mobileApp.database.dao.PatientDao;
import com.example.mobileApp.database.entity.PatientTable;
import com.example.mobileApp.database.dao.QuestionAnswerDao;
import com.example.mobileApp.database.entity.QuestionAnswerTable;
import com.example.mobileApp.database.dao.QuestionDao;
import com.example.mobileApp.database.dao.QuestionRelationDao;
import com.example.mobileApp.database.entity.QuestionRelationTable;
import com.example.mobileApp.database.entity.QuestionTable;
import com.example.mobileApp.database.dao.QuestionTypeDao;
import com.example.mobileApp.database.entity.QuestionTypeTable;
import com.example.mobileApp.database.dao.QuestionnaireDao;
import com.example.mobileApp.database.entity.QuestionnaireTable;
import com.example.mobileApp.database.dao.ResponseDao;
import com.example.mobileApp.database.entity.ResponseTable;
import com.example.mobileApp.database.dao.ServiceDao;
import com.example.mobileApp.database.entity.ServiceTable;
import com.example.mobileApp.database.dao.AnswerDao;
import com.example.mobileApp.database.dao.AssistiveTechnologyDao;
import com.example.mobileApp.database.dao.CauseOfDiseaseDao;
import com.example.mobileApp.database.dao.DiagnosisDao;
import com.example.mobileApp.database.dao.EnumeratorDao;
import com.example.mobileApp.database.dao.HouseholdDao;
import com.example.mobileApp.database.dao.LocationDao;
import com.example.mobileApp.database.dao.LogicDao;
import com.example.mobileApp.database.entity.AnswerTable;
import com.example.mobileApp.database.entity.AssistiveTechnologyTable;
import com.example.mobileApp.database.entity.CauseOfDiseaseTable;
import com.example.mobileApp.database.entity.DiagnosisTable;
import com.example.mobileApp.database.entity.EnumeratorTable;
import com.example.mobileApp.database.entity.HouseholdTable;
import com.example.mobileApp.database.entity.LocationTable;
import com.example.mobileApp.database.entity.LogicTable;

@Database(entities = {EnumeratorTable.class,
                      LocationTable.class,
                      HouseholdTable.class,
                      PatientTable.class,
                      PatientAssessmentStatusTable.class,
                      QuestionnaireTable.class,
                      QuestionTypeTable.class,
                      QuestionTable.class,
                      AnswerTable.class,
                      QuestionAnswerTable.class,
                      ResponseTable.class,
                      LogicTable.class,
                      QuestionRelationTable.class,
                      DiagnosisTable.class,
                      ServiceTable.class,
                      CauseOfDiseaseTable.class,
                      AssistiveTechnologyTable.class},
          exportSchema = false,
          version = 7)
@TypeConverters({LocalDateTypeConverter.class})
public abstract class MobileAppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "app_database";

    public abstract EnumeratorDao enumeratorDao();
    public abstract LocationDao locationDao();
    public abstract HouseholdDao householdDao();
    public abstract PatientDao patientDao();
    public abstract PatientAssessmentStatusDao patientAssessmentStatusDao();
    public abstract QuestionnaireDao questionnaireDao();
    public abstract QuestionTypeDao questionTypeDao();
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();
    public abstract QuestionAnswerDao questionAnswerDao();
    public abstract ResponseDao responseDao();
    public abstract LogicDao logicDao();
    public abstract QuestionRelationDao questionRelationDao();
    public abstract DiagnosisDao diagnosisDao();
    public abstract ServiceDao serviceDao();
    public abstract CauseOfDiseaseDao causeOfDiseaseDao();
    public abstract AssistiveTechnologyDao atDao();


    /* Singleton */
    private static volatile MobileAppDatabase instance;
    private static final Object LOCK = new Object();

    public static MobileAppDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), MobileAppDatabase.class, DATABASE_NAME)
                                   .fallbackToDestructiveMigration()    // clear the db when migration - would lose all data
                                   .build();
                }
            }
        }
        return instance;
    }
}
