import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import tgound.example.test1.AppDatabase
import tgound.example.test1.Student
import tgound.example.test1.StudentRepository

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudentRepository
    val allStudents: Flow<List<Student>>

    init {
        val database = AppDatabase.getDatabase(application)
        val dao = database.studentDao()
        repository = StudentRepository(dao)
        allStudents = repository.allStudents
    }

    fun insertStudent(student: Student) = viewModelScope.launch {
        repository.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        repository.updateStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        repository.deleteStudent(student)
    }
}
