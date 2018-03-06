// IBookManager.aidl
package com.mei.ipc;

// Declare any non-default types here with import statements


// Declare any non-default types here with import statements
import com.mei.ipc.Book;

interface IBookManager {

    List<Book> getBookList();

    /**
    * aidl接口文件中声明的方法，方法的参数除了基本数据类型，其他类型的参数都必须标明方向：
    * 即in、out或者inout。
    * in :表示输入型参数，
    * out:表示输出型参数，
    * inout:表示输入输出型参数。
    * 我们可以根据实际需要去指定参数的方向，不能一概使用out或者inout，因为这在底层实现是
    * 有开销的。
    */
    void addBook(in Book book);
}
