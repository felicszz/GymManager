package com.cftr.gymmanager.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cftr.gymmanager.data.dao.ClaseColectivaDao
import com.cftr.gymmanager.data.dao.CuotaDao
import com.cftr.gymmanager.data.dao.RutinaDao
import com.cftr.gymmanager.data.dao.SocioDao

@Database(
    entities = [
        SocioEntity::class,
        RutinaEntity::class,
        ClaseColectivaEntity::class,
        CuotaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GymDatabase : RoomDatabase() {

    abstract fun socioDao(): SocioDao
    abstract fun rutinaDao(): RutinaDao
    abstract fun claseColectivaDao(): ClaseColectivaDao
    abstract fun cuotaDao(): CuotaDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getDatabase(context: Context): GymDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "gym_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
