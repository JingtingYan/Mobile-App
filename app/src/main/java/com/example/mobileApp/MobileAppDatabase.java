package com.example.mobileApp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.IOException;
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
                        exportSchema = false)  // ??
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


//    Reference: https://github.com/ashishrawat2911/MVVM-Demo/blob/master/app/src/main/java/com/aeologic/moviemvvmdemo/db/MovieDB.java

    // Singleton
    private static MobileAppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static final String DB_NAME = "app_database";


    static MobileAppDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null) {
            System.out.println("\nHELLO INSTANCE == NULL\n\n");
            synchronized (MobileAppDatabase.class)
            {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MobileAppDatabase.class, DB_NAME)
                            .addCallback(roomCallback)
                            //.allowMainThreadQueries()
                            .build();
                    System.out.println("\nBUILT\n\n");
                    PopulateLocation populate = new PopulateLocation(INSTANCE.locationDao());
                    System.out.println("\n\n MOBILEAPPDATABASE: abt to run thread\n\n");
                    databaseWriteExecutor.execute(populate);
                }
                else
                {
                    System.out.println("\nINSTANCE ISNT NULL1\n\n");
                }

            }
//            INSTANCE.locationDao().insertLocation(1, "Federal Republic of Nigeria", -1);
        }
        else
        {
            System.out.println("\nINSTANCE ISNT NULL2\n\n");
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            System.out.println("\nCALLBACK: HI\n");
            super.onCreate(db);
//            PopulateLocation populate = new PopulateLocation(INSTANCE.locationDao());
//            System.out.println("\n\n MOBILEAPPDATABASE: abt to run thread\n\n");
//            databaseWriteExecutor.execute(populate);
//            new PopulateDBAsyncTask(INSTANCE).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            System.out.println("\nCALLBACK: ON OPEN\n");
        }
    };

    private static class PopulateLocation implements Runnable {

        private LocationDao locationDao;
        PopulateLocation(LocationDao locationDao) {
            this.locationDao = locationDao;
        }

        @Override
        public void run() {
            Prepopulate prepopulate = new Prepopulate();

            try {
                System.out.println("\nTRYING TO POPULATE\n");
                prepopulate.populateLocation("../../assets/locationDummy.csv", locationDao);
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
