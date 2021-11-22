package com.nigamar.nasapictures.di

import android.content.Context
import androidx.room.Room
import com.nigamar.nasapictures.data.dao.StorageDaoImpl
import com.nigamar.nasapictures.data.data_source.PicturesDao
import com.nigamar.nasapictures.data.data_source.PicturesDb
import com.nigamar.nasapictures.data.repository.PicturesRepoImpl
import com.nigamar.nasapictures.domain.dao.StorageDao
import com.nigamar.nasapictures.domain.repository.PicturesRepository
import com.nigamar.nasapictures.domain.use_cases.GetAllPictures
import com.nigamar.nasapictures.domain.use_cases.GetPicturesByDate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPicturesDb(@ApplicationContext app : Context ) : PicturesDb {
        return Room.databaseBuilder(
            app ,
            PicturesDb::class.java,
            "pictures_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesPicturesDao(picturesDb: PicturesDb) : PicturesDao {
        return picturesDb.getPicturesDao()
    }

    @Provides
    @Singleton
    fun providesStorageDao(@ApplicationContext app : Context, picturesDao: PicturesDao ) : StorageDao {
        return StorageDaoImpl(app,picturesDao)
    }

    @Provides
    @Singleton
    fun providesPicturesRepository(storageDao: StorageDao , picturesDao: PicturesDao) : PicturesRepository {
        return PicturesRepoImpl(storageDao,picturesDao)
    }

    @Provides
    fun provideGetAllPictures(picturesRepository: PicturesRepository) : GetAllPictures {
        return GetAllPictures(picturesRepository)
    }

    @Provides
    fun provideGetPicturesByDate(picturesRepository: PicturesRepository) : GetPicturesByDate {
        return GetPicturesByDate(picturesRepository)
    }

}