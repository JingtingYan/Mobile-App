package com.example.mobileApp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Enumerator.class,
                      Location.class,
                      Household.class,
                      Patient.class,
                      PatientAssessmentStatus.class,
                      Questionnaire.class,
                      QuestionType.class,
                      QuestionTable.class,
                      AnswerTables.class,
                      QuestionAnswer.class,
                      ResponseTable.class,
                      Logic.class,
                      QuestionRelation.class,
                      Diagnosis.class,
                      Service.class,
                      CauseOfDisease.class,
                      AT.class},
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
    public abstract ATDao atDao();


    // Singleton
    private static MobileAppDatabase INSTANCE;

    static MobileAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MobileAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MobileAppDatabase.class, "app_database")
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
