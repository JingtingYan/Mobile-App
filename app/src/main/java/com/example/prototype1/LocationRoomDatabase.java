package com.example.prototype1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Country.class, Region.class, Cluster.class}, version = 1, exportSchema = false)
public abstract class LocationRoomDatabase extends RoomDatabase {

    public abstract DbDao dbDao();

    private static volatile LocationRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


//    FIRST METHOD
    static LocationRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (LocationRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocationRoomDatabase.class, "location_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

//    SECOND METHOD
    private static LocationRoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
               DbDao dao = INSTANCE.dbDao();
               Country indonesia = new Country(0, "Indonesia");
               Country india = new Country(0, "India");
               Country nigeria = new Country(0, "Nigeria");
               dao.insertCountry(indonesia);
               dao.insertCountry(india);
               dao.insertCountry(nigeria);

               int parentIndonesia = indonesia.getCountryId();
               Region java = new Region(0, "Java", parentIndonesia);
               dao.insertRegion(java);

               int parentIndia = india.getCountryId();
               Region srinigar = new Region(0, "Srinigar", parentIndia);
               Region chandigarh = new Region(0, "Chandigargh", parentIndia);
               Region simla = new Region(0, "Simla", parentIndia);
               dao.insertRegion(srinigar);
               dao.insertRegion(chandigarh);
               dao.insertRegion(simla);

               int parentNigeria = nigeria.getCountryId();
               Region ado = new Region(0, "Ado", parentNigeria);
               Region bauchi = new Region(0, "Bauchi", parentNigeria);
               Region sokoto = new Region(0, "Sokoto", parentNigeria);
               dao.insertRegion(ado);
               dao.insertRegion(bauchi);
               dao.insertRegion(sokoto);

               int parentJava = java.getRegionId();
               Cluster java1 = new Cluster(0, "Cluster A1", parentJava);
               Cluster java2 = new Cluster(0, "ClusterA2", parentJava);
               dao.insertCluster(java1);
               dao.insertCluster(java2);

               int parentSri = srinigar.getRegionId();
               Cluster sri1 = new Cluster(0, "Cluster A1", parentSri);
               Cluster sri2 = new Cluster(0, "Cluster B1", parentSri);
               Cluster sri3 = new Cluster(0, "Cluster B2", parentSri);
               dao.insertCluster(sri1);
               dao.insertCluster(sri2);
               dao.insertCluster(sri3);

               int parentChan = chandigarh.getRegionId();
               Cluster chan1 = new Cluster(0, "Cluster A1", parentChan);
               Cluster chan2 = new Cluster(0, "Cluster B1", parentChan);
               dao.insertCluster(chan1);
               dao.insertCluster(chan2);

               int parentSim = simla.getRegionId();
               Cluster sim1 = new Cluster(0, "Cluster A1", parentSim);
               dao.insertCluster(sim1);

               int parentAdo = ado.getRegionId();
               Cluster ado1 = new Cluster(0, "Cluster A1", parentAdo);
               Cluster ado2 = new Cluster(0, "Cluster B1", parentAdo);
               Cluster ado3 = new Cluster(0, "Cluster C1", parentAdo);
               dao.insertCluster(ado1);
               dao.insertCluster(ado2);
               dao.insertCluster(ado3);

               int parentBau = bauchi.getRegionId();
               Cluster bau1 = new Cluster(0, "Cluster D1", parentBau);
               Cluster bau2 = new Cluster(0, "Cluster D2", parentBau);
               dao.insertCluster(bau1);
               dao.insertCluster(bau2);

               int parentSok = sokoto.getRegionId();
               Cluster sok1 = new Cluster(0, "Cluster S1", parentSok);
               Cluster sok2 = new Cluster(0, "Cluster S2", parentSok);
               Cluster sok3 = new Cluster(0, "Cluster S3", parentSok);
               dao.insertCluster(sok1);
               dao.insertCluster(sok2);
               dao.insertCluster(sok3);
            });
        }
    };

}
