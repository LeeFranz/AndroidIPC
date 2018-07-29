// BookStoreInterface.aidl
package leo.com.bookstoredemo;

// Declare any non-default types here with import statements
//导入所需要使用的非默认支持数据类型的包
import leo.com.bookstoredemo.Book;

interface BookStoreInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    //所有的返回值前都不需要加任何东西，不管是什么数据类型
    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    List<Book> getBooks();
    void addBook(in Book book);
}
