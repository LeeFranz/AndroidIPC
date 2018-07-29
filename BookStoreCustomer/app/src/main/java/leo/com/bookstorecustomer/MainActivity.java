package leo.com.bookstorecustomer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import leo.com.bookstoredemo.Book;
import leo.com.bookstoredemo.BookStoreInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BookStoreInterface mBookStoreInterface = null;

    private boolean mHasBound = false;

    private List<Book> mBooks = null;

    private Button mAddBookButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddBookButton = findViewById(R.id.add_book);
        mAddBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mHasBound && mBookStoreInterface != null) {
                    Book book = new Book("fucking android",50);
                    try {
                        mBookStoreInterface.addBook(book);
                        Log.i(TAG,"the book has been added to list "+book.toString());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        attemptToBindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnectionService);
        mBookStoreInterface = null;
        mHasBound = false;
    }

    private void attemptToBindService() {
        Intent intent = new Intent("com.leo.bookstore.manager");
        intent.setPackage("leo.com.bookstoredemo");
        bindService(intent,mConnectionService, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnectionService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG,"Service connected !");
            mBookStoreInterface = BookStoreInterface.Stub.asInterface(iBinder);
            mHasBound = true;
            if(mBookStoreInterface != null) {
                try {
                    mBooks = mBookStoreInterface.getBooks();
                    Log.i(TAG,"customer has connected bookstore and get a book list: "+mBooks.toString());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBookStoreInterface = null;
            mHasBound = false;
            Log.i(TAG,"Service disconnected");
        }
    };
}
