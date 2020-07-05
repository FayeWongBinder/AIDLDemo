// IBookManager.aidl
package com.study.myapplication;


import com.study.myapplication.Book;
interface IBookManager {
   List<Book> getBookList();
   void setBook(in Book book);
}
