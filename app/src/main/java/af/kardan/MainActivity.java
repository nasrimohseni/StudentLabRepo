package af.kardan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView empList;
    Button addEmp, addStudent;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empList = findViewById(R.id.employeesList);
        addEmp = findViewById(R.id.btnAddEmp);
        addStudent = findViewById(R.id.btnAddStudent);

        database = new Database(getApplicationContext());
        database.open();

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Employee> employeeList = database.getAllEmployees();
        empList.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, employeeList));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    public void goToAddEmp(View view) {
        startActivity(new Intent(this, InsertEmployeeActivity.class));
    }

    public void goToBooks(View view) {
        startActivity(new Intent(this, BooksActivity.class));
    }
}
