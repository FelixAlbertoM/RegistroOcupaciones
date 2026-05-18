package edu.ucne.registroocupaciones.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.registroocupaciones.data.database.OcupacionDb
import edu.ucne.registroocupaciones.data.local.dao.OcupacionDao
import edu.ucne.registroocupaciones.domain.ocupacion.repository.OcupacionRepository
import edu.ucne.registroocupaciones.data.repository.OcupacionRepositoryImpl
import javax.inject.Singleton
import edu.ucne.registroocupaciones.domain.empleado.repository.EmpleadoRepository
import edu.ucne.registroocupaciones.data.repository.EmpleadoRepositoryImpl
import edu.ucne.registroocupaciones.data.local.dao.EmpleadoDao


@InstallIn(SingletonComponent::class)
@Module

object AppModule {

    @Provides
    @Singleton
    fun provideOcupacionDb(@ApplicationContext appContext: Context): OcupacionDb {
        return Room.databaseBuilder(
            appContext,
            OcupacionDb::class.java,
            "OcupacionDb"
        ).fallbackToDestructiveMigration(false )
            .build()
    }

    @Provides
    @Singleton
    fun provideOcupacionDao(ocupacionDb: OcupacionDb): OcupacionDao {
        return ocupacionDb.ocupacionDao()
    }

    @Provides
    @Singleton
    fun provideOcupacionRepositoryImpl(ocupacionDao: OcupacionDao): OcupacionRepositoryImpl {
        return OcupacionRepositoryImpl(ocupacionDao)
    }

    @Provides
    @Singleton
    fun provideOcupacionRepository(impl: OcupacionRepositoryImpl): OcupacionRepository {
        return impl
    }

    @Provides
    @Singleton
    fun provideEmpleadoDao(ocupacionDb: OcupacionDb): EmpleadoDao {
        return ocupacionDb.empleadoDao()
    }

    @Provides
    @Singleton
    fun provideEmpleadoRepositoryImpl(empleadoDao: EmpleadoDao): EmpleadoRepositoryImpl {
        return EmpleadoRepositoryImpl(empleadoDao)
    }

    @Provides
    @Singleton
    fun provideEmpleadoRepository(impl: EmpleadoRepositoryImpl): EmpleadoRepository {
        return impl
    }
}