package com.hoang.pagedlistexample.repository

import android.content.Context
import androidx.paging.DataSource
import androidx.room.*
import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory

@Entity(tableName = "cities")
data class City(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "population") var population: Long
)

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    fun loadCities(): DataSource.Factory<Int, City>
}

@Database(entities = [City::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun CityDao(): CityDao

    companion object {
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            sInstance ?: synchronized(this) {
                sInstance ?: run {
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "utopia_cities.db"
                    )
                        .allowMainThreadQueries()
                        .openHelperFactory(AssetSQLiteOpenHelperFactory())
                        .build()
                    sInstance!!
                }
            }

    }

    fun destroyInstance() {
        sInstance = null
    }
}
