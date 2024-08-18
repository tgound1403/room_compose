import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tgound.example.test1.Student

@Composable
fun MainScreen(viewModel: StudentViewModel = viewModel()) {
    val students by viewModel.allStudents.collectAsState(initial = emptyList())
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf( "") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val student = Student(
                    name = name,
                    age = age.toIntOrNull() ?: 0,
                    address = address,
                    gender = gender
                )
                viewModel.insertStudent(student)
                name = ""
                age = ""
                address = ""
                gender = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Student")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(students) { student ->
                StudentItem(student, viewModel)
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, viewModel: StudentViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${student.name}")
            Text("Age: ${student.age}")
            Text("Address: ${student.address}")
            Text("Gender: ${student.gender}")
            Button(
                onClick = { viewModel.deleteStudent(student) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete")
            }
        }
    }
}
