package af.kardan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String TAG = "LMS_DB";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public Database(Context context) {
        this.context = context;
    }

    public Database open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        //  BOOKS..............
        public static final String BOOKS_TBL_NAME = "BOOKS";
        public static final String BOOK_ID = "id";
        public static final String BOOK_TITLE = "book_title";
        public static final String BOOK_AUTHOR = "book_author";
        public static final String BOOK_ISBN = "book_isb";
        public static final String BOOK_PUBLISHER = "book_pub";
        //  EMPLOYEE...........
        public static final String EMP_TBL_NAME = "EMPLOYEES";
        public static final String EMP_ID = "id";
        public static final String EMP_NAME = "emp_name";
        public static final String EMP_FNAME = "emp_fname";
        public static final String EMP_DES = "emp_des";
        public static final String EMP_SALARY = "emp_salary";

        DatabaseHelper(Context context) {
            super(context, "LMS", null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE " + BOOKS_TBL_NAME + " ("
                            + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + BOOK_TITLE + " TEXT, "
                            + BOOK_AUTHOR + " TEXT, "
                            + BOOK_ISBN + " TEXT, "
                            + BOOK_PUBLISHER + " TEXT" +
                            ")"
            );
            sqLiteDatabase.execSQL(
                    "CREATE TABLE " + EMP_TBL_NAME + " ("
                            + EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + EMP_NAME + " TEXT, "
                            + EMP_FNAME + " TEXT, "
                            + EMP_DES + " TEXT, "
                            + EMP_SALARY + " TEXT" +
                            ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BOOKS_TBL_NAME);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EMP_TBL_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    /**
     * Insert Into Database.
     */
    public long insertBook(Book book) {

        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.BOOK_TITLE, book.getBookTitle());
        cv.put(DatabaseHelper.BOOK_AUTHOR, book.getBookAuthor());
        cv.put(DatabaseHelper.BOOK_ISBN, book.getBookISBN());
        cv.put(DatabaseHelper.BOOK_PUBLISHER, book.getBookPublisher());

        return db.insert(DatabaseHelper.BOOKS_TBL_NAME, null, cv);

    }

    /**
     * Insert Into Database.
     */
    public long insertEmployee(Employee employee) {

        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.EMP_NAME, employee.getEmployeeName());
        cv.put(DatabaseHelper.EMP_FNAME, employee.getEmployeeFatherName());
        cv.put(DatabaseHelper.EMP_DES, employee.getEmployeeDesignation());
        cv.put(DatabaseHelper.EMP_SALARY, employee.getEmployeeSalary());

        return db.insert(DatabaseHelper.EMP_TBL_NAME, null, cv);

    }

    /**
     * Delete From Database.
     */
    public int deleteBook(String id) {
        return db.delete(DatabaseHelper.BOOKS_TBL_NAME, DatabaseHelper.BOOK_ID + " =?", new String[]{id});
    }

    /**
     * Delete From Database.
     */
    public int deleteEmployee(String id) {
        return db.delete(DatabaseHelper.BOOKS_TBL_NAME, DatabaseHelper.BOOK_ID + " =?", new String[]{id});
    }

    /**
     * Get All Database Data.
     *
     * @return List Of DATA.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        Cursor cursor = db.query(
                DatabaseHelper.BOOKS_TBL_NAME,
                new String[]{DatabaseHelper.BOOK_ID, DatabaseHelper.BOOK_TITLE, DatabaseHelper.BOOK_AUTHOR, DatabaseHelper.BOOK_ISBN, DatabaseHelper.BOOK_PUBLISHER},
                null, null, null, null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {

            do {
                int bookId = cursor.getColumnIndex(DatabaseHelper.BOOK_ID);
                int bookTitle = cursor.getColumnIndex(DatabaseHelper.BOOK_TITLE);
                int bookAuthor = cursor.getColumnIndex(DatabaseHelper.BOOK_AUTHOR);
                int bookISBN = cursor.getColumnIndex(DatabaseHelper.BOOK_ISBN);
                int bookPublisher = cursor.getColumnIndex(DatabaseHelper.BOOK_PUBLISHER);

                books.add(new Book(
                        cursor.getInt(bookId),
                        cursor.getString(bookTitle),
                        cursor.getString(bookAuthor),
                        cursor.getString(bookISBN),
                        cursor.getString(bookPublisher)));
            } while (cursor.moveToNext());
        }

        if (cursor != null)
            cursor.close();

        return books;
    }

    /**
     * Get All Database Data.
     *
     * @return List Of DATA.
     */
    public List<Employee> getAllEmployees() {
        List<Employee> emps = new ArrayList<>();

        Cursor cursor = db.query(
                DatabaseHelper.EMP_TBL_NAME,
                new String[]{DatabaseHelper.EMP_ID, DatabaseHelper.EMP_NAME, DatabaseHelper.EMP_FNAME, DatabaseHelper.EMP_DES, DatabaseHelper.EMP_SALARY},
                null, null, null, null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {

            do {
                int empId = cursor.getColumnIndex(DatabaseHelper.EMP_ID);
                int empName = cursor.getColumnIndex(DatabaseHelper.EMP_NAME);
                int empFName = cursor.getColumnIndex(DatabaseHelper.EMP_FNAME);
                int empDes = cursor.getColumnIndex(DatabaseHelper.EMP_DES);
                int empSalary = cursor.getColumnIndex(DatabaseHelper.EMP_SALARY);

                emps.add(new Employee(
                        cursor.getInt(empId),
                        cursor.getString(empName),
                        cursor.getString(empFName),
                        cursor.getString(empDes),
                        cursor.getString(empSalary)));
            } while (cursor.moveToNext());
        }

        if (cursor != null)
            cursor.close();

        return emps;
    }

    /**
     * Get All Database Data.
     */
    public List<Book> getBook(String id) {
        List<Book> books = new ArrayList<>();

        Cursor cursor = db.query(
                DatabaseHelper.BOOKS_TBL_NAME,
                new String[]{DatabaseHelper.BOOK_ID, DatabaseHelper.BOOK_TITLE, DatabaseHelper.BOOK_AUTHOR, DatabaseHelper.BOOK_ISBN, DatabaseHelper.BOOK_PUBLISHER},
                DatabaseHelper.BOOK_ID + " =? ", new String[]{id}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int bookId = cursor.getColumnIndex(DatabaseHelper.BOOK_ID);
                int bookTitle = cursor.getColumnIndex(DatabaseHelper.BOOK_TITLE);
                int bookAuthor = cursor.getColumnIndex(DatabaseHelper.BOOK_AUTHOR);
                int bookISBN = cursor.getColumnIndex(DatabaseHelper.BOOK_ISBN);
                int bookPublisher = cursor.getColumnIndex(DatabaseHelper.BOOK_PUBLISHER);

                books.add(new Book(
                        cursor.getInt(bookId),
                        cursor.getString(bookTitle),
                        cursor.getString(bookAuthor),
                        cursor.getString(bookISBN),
                        cursor.getString(bookPublisher)));
            } while (cursor.moveToNext());
        }

        if (cursor != null)
            cursor.close();

        return books;
    }

    /**
     * Update Book.
     */
    public boolean updateBook(Book book) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.BOOK_TITLE, book.getBookTitle());
        cv.put(DatabaseHelper.BOOK_AUTHOR, book.getBookAuthor());
        cv.put(DatabaseHelper.BOOK_ISBN, book.getBookISBN());
        cv.put(DatabaseHelper.BOOK_PUBLISHER, book.getBookPublisher());
        int updated = db.update(DatabaseHelper.BOOKS_TBL_NAME, cv, DatabaseHelper.BOOK_ID + " =? ", new String[]{String.valueOf(book.getBookId())});
        Log.i(TAG, "book updated: " + updated);
        return updated >= 1;
    }

    /**
     * Update Book.
     */
    public boolean updateEmployee(Employee employee) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.EMP_NAME, employee.getEmployeeName());
        cv.put(DatabaseHelper.EMP_FNAME, employee.getEmployeeFatherName());
        cv.put(DatabaseHelper.EMP_DES, employee.getEmployeeDesignation());
        cv.put(DatabaseHelper.EMP_SALARY, employee.getEmployeeSalary());
        int updated = db.update(DatabaseHelper.EMP_TBL_NAME, cv, DatabaseHelper.EMP_ID + " =? ", new String[]{String.valueOf(employee.getEmployeeID())});
        Log.i(TAG, "emp updated: " + updated);
        return updated >= 1;
    }

    /**
     * Checks if the item exits in the database or not.
     */
    public boolean checkBookExists(int ID) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.BOOKS_TBL_NAME + " WHERE " + DatabaseHelper.BOOK_ID + "=" + ID, null);
        boolean exits = cursor.moveToFirst();
        cursor.close();
        return exits;
    }

    /**
     * Checks if the item exits in the database or not.
     */
    public boolean checkEmployeeExists(int ID) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.EMP_TBL_NAME + " WHERE " + DatabaseHelper.EMP_ID + "=" + ID, null);
        boolean exits = cursor.moveToFirst();
        cursor.close();
        return exits;
    }

}
