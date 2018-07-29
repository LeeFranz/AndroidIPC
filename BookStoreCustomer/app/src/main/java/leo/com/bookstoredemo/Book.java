package leo.com.bookstoredemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    public Book(String mBookName, int mBookPrice) {
        this.mBookName = mBookName;
        this.mBookPrice = mBookPrice;
    }

    protected Book(Parcel in) {
        mBookName = in.readString();
        mBookPrice = in.readInt();
    }

    private String mBookName;
    private int mBookPrice;

    public String getmBookName() {
        return mBookName;
    }

    public void setmBookName(String mBookName) {
        this.mBookName = mBookName;
    }

    public int getmBookPrice() {
        return mBookPrice;
    }

    public void setmBookPrice(int mBookPrice) {
        this.mBookPrice = mBookPrice;
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
        parcel.writeString(mBookName);
        parcel.writeInt(mBookPrice);
    }

    @Override
    public String toString() {
        return "Book{" +
                "mBookName='" + mBookName + '\'' +
                ", mBookPrice=" + mBookPrice +
                '}';
    }
}
