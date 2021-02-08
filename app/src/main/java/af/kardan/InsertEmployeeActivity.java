package af.kardan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertEmployeeActivity extends AppCompatActivity {
    EditText empName, empFname, empDes, empSalary;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_employee);

        empName = findViewById(R.id.empName);
        empFname = findViewById(R.id.empFName);
        empDes = findViewById(R.id.empDes);
        empSalary = findViewById(R.id.empSalary);

        database = new Database(getApplicationContext());
        database.open();

    }

    public void addEmployee(View view) {
        long id = database.insertEmployee(new Employee(0,
                empName.getEditableText().toString(),
                empFname.getEditableText().toString(),
                empDes.getEditableText().toString(),
                empSalary.getEditableText().toString()
        ));
        Log.i("ABC", "addEmployee: "+id);
        if (id != -1)
            Toast.makeText(this, "Employee Added.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
