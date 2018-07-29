package leo.com.bookstoredemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookStoreService extends Service {

    private static final String TAG = "BookStoreService";

    private List<Book> mBooks = new ArrayList<>();

    private static final Object sObject = new Object();

    private BookStoreInterface.Stub mBookStoreManager = new BookStoreInterface.Stub(){
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            synchronized (sObject) {
                if(mBooks != null){
                    return mBooks;
                } else {
                    mBooks = new ArrayList<>();
                    return mBooks;
                }
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (sObject) {
                if(mBooks == null) {
                    mBooks = new ArrayList<>();
                }
                if(book == null) {
                    book = new Book("A Book",10);
                    Log.e(TAG,"the Book added in is null !");
                }
                if(!mBooks.contains(book)){
                    book.setmBookPrice(book.getmBookPrice() * 2);
                    mBooks.add(book);
                    Log.i(TAG,"now adding the book and change its price to "+book.getmBookPrice());
                }
            }

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Book originalBook = new Book("tap dancing to work",30);
        mBooks.add(originalBook);
        Log.i(TAG,"original book list is： "+originalBook.toString());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"Bookstore's manager has been connected: "+mBookStoreManager.toString());
        return mBookStoreManager;
    }
}
