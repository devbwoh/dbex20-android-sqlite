package kr.ac.kumoh.s20210000.gunplasqliteapplication
// 본인의 package 사용할 것

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "mechanic")
data class Mechanic (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val armor: String,
    val height: Double,
    val weight: Double
)

data class Mechanic2 (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val armor: String,
    val height: Double,
    val weight: Double
)

@Dao
interface GunplaDao {
    @Query("SELECT * FROM mechanic")
    fun getAllMechanics(): Flow<List<Mechanic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mechanic: Mechanic)
}

@Database(entities = [Mechanic::class], version = 1, exportSchema = false)
abstract class GunplaDatabase : RoomDatabase() {
    abstract fun gunplaDao(): GunplaDao

    companion object {
        @Volatile
        private var INSTANCE: GunplaDatabase? = null

        fun getDatabase(context: Context): GunplaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GunplaDatabase::class.java,
                    "gunpla_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

class WordRepository(private val gunplaDao: GunplaDao) {
    // public 이므로 외부에서 접근 가능
    val allMechanic: Flow<List<Mechanic>> = gunplaDao.getAllMechanics()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(mechanic: Mechanic) {
        gunplaDao.insert(mechanic)
    }
}
