package com.example.mobileApp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Enumerator.class,
                      Location.class,
                      Household.class,
                      Patient.class,
                      PatientAssessmentStatus.class,
                      Questionnaire.class,
                      QuestionType.class,
                      Question.class,
                      Answer.class},
          version = 1)
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
}
