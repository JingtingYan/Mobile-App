package com.example.mobileApp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
          version = 1,
          exportSchema = false)  // ignore database migrations atm
@TypeConverters({LocalDateTypeConverter.class})
public abstract class MobileAppDatabase extends RoomDatabase {

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
    private static volatile MobileAppDatabase INSTANCE;
    // run database operations asynchronously on a background thread
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MobileAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MobileAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MobileAppDatabase.class,
                            "app_database")
                            .addCallback(roomDbCallback)  // pre-populate db
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    /* pre-populate data in database */
    private static RoomDatabase.Callback roomDbCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                LocationDao locationDao = INSTANCE.locationDao();
                locationDao.deleteAll();

                // pre-populate countries
                LocationTable location = new LocationTable("Federal Repubic of Nigeria", -1);
                locationDao.insert(location);
                location = new LocationTable("India", -1);
                locationDao.insert(location);
                location = new LocationTable("Phillipenes", -1);
                locationDao.insert(location);
                location = new LocationTable("Indonesia", -1);
                locationDao.insert(location);

                // pre-populate regions
                location = new LocationTable("Ado", 1);
                locationDao.insert(location);
                location = new LocationTable("Bauchi", 1);
                locationDao.insert(location);
                location = new LocationTable("Sokoto", 1);
                locationDao.insert(location);
                location = new LocationTable("Srinigar", 2);
                locationDao.insert(location);
                location = new LocationTable("Chandigarh", 2);
                locationDao.insert(location);
                location = new LocationTable("Simla", 2);
                locationDao.insert(location);
                location = new LocationTable("Manila", 3);
                locationDao.insert(location);
                location = new LocationTable("Java", 4);
                locationDao.insert(location);
                location = new LocationTable("Sumatera", 4);
                locationDao.insert(location);
                location = new LocationTable("Palembang", 4);
                locationDao.insert(location);

                // pre-populate clusters
                location = new LocationTable("Cluster A1", 5);
                locationDao.insert(location);
                location = new LocationTable("Cluster A2", 5);
                locationDao.insert(location);
                location = new LocationTable("Cluster B1", 5);
                locationDao.insert(location);
                location = new LocationTable("Cluster A1", 6);
                locationDao.insert(location);
                location = new LocationTable("Cluster A2", 6);
                locationDao.insert(location);
                location = new LocationTable("Cluster A3", 6);
                locationDao.insert(location);
                // ...
            });
        }
    };
}
