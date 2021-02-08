package af.kardan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertBookActivity extends AppCompatActivity {
    EditText bookTitle, bookAuthor, bookISBN, bookPublisher;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);

        database = new Database(getApplicationContext());
        database.open();

        bookTitle = findViewById(R.id.bookTitle);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookISBN = findViewById(R.id.bookISBN);
        bookPublisher = findViewById(R.id.bookPublisher);

    }


    public void insertBook(View view) {
        long id = database.insertBook(new Book(0,
                bookTitle.getEditableText().toString(),
                bookAuthor.getEditableText().toString(),
                bookISBN.getEditableText().toString(),
                bookPublisher.getEditableText().toString()
        ));
        Log.i("ABC", "addBook: "+id);
        if (id != -1)
            Toast.makeText(this, "Book Added.", Toast.LENGTH_SHORT).show();

    }
}
