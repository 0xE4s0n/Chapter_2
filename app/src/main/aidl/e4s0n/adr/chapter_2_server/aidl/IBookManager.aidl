// IBookManager.aidl
package e4s0n.adr.chapter_2_server.aidl;

// Declare any non-default types here with import statements

import e4s0n.adr.chapter_2_server.aidl.Book;

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBookList();
    void addBook(in Book book);
}
