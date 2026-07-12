import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.Data.Habit
import com.example.habittracker.Data.HabitDao

@Database(
    entities = [Habit::class],
    version = 1,
    exportSchema = true
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {

        @Volatile
        private var INSTANCE: HabitDatabase? = null

        private val LOCK = Any()

        fun getInstance(context: Context): HabitDatabase {
            return INSTANCE ?: synchronized(LOCK) {

                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    HabitDatabase::class.java,
                    "habit_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}