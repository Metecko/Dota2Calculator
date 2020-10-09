package cl.arlanditech.dota2calculator.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cl.arlanditech.dota2calculator.model.database.DotaDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class RoomApplication: Application() {

    val TAG = "RoomApplication"

    companion object {
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(4)
        var dotaDatabase: DotaDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()
        dotaDatabase = Room
            .databaseBuilder(this,
                DotaDatabase::class.java,
                "dota-master-db").addCallback(rdc).build()
    }

    var rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
        }
    }
}