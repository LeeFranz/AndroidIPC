// BookStoreInterface.aidl
package leo.com.bookstoredemo;

// Declare any non-default types here with import statements
import leo.com.bookstoredemo.Book;

interface BookStoreInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Book> getBooks();
    void addBook(in Book book);
}
