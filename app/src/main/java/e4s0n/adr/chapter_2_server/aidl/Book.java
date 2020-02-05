package e4s0n.adr.chapter_2_server.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private int BookId;
    private String BookName;

    public Book(int bookId, String bookName) {
        BookId = bookId;
        this.BookName = bookName;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    protected Book(Parcel in) {
        BookId = in.readInt();
        BookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(BookId);
        parcel.writeString(BookName);
    }
}
