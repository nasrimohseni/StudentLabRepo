package af.kardan;

import androidx.annotation.NonNull;

public class Book {

    private int bookId;
    private String bookTitle, bookAuthor, bookISBN, bookPublisher;

    public Book(int bookId, String bookTitle, String bookAuthor, String bookISBN, String bookPublisher) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookPublisher = bookPublisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    @NonNull
    @Override
    public String toString() {
        return bookTitle;
    }
}
