package com.cftr.gymmanager

import android.app.Application
import com.cftr.gymmanager.data.db.GymDatabase
import com.cftr.gymmanager.data.repository.SocioRepository

class GymApplication : Application() {

    val database: GymDatabase by lazy { GymDatabase.getDatabase(this) }
    val socioRepository: SocioRepository by lazy { SocioRepository(database.socioDao()) }
}
