package af.kardan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BooksActivity extends AppCompatActivity {
    Database database;
    ListView booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        booksList = findViewById(R.id.booksList);
        database = new Database(getApplicationContext());
        database.open();

    }

    @Override
    protected void onResume() {
        super.onResume();
        super.onResume();
        List<Book> bookList = database.getAllBooks();
        booksList.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, bookList));
    }

    public void addBook(View view) {
        startActivity(new Intent(this, InsertBookActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
