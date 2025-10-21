package com.example.mybooks.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.repository.BookRepository;

public class DetailsViewModel extends ViewModel {

    private final BookRepository bookRepository = new BookRepository();

    //Criaçao de mutable/livedata para vincular informacao do livro ao observer
    private final MutableLiveData<BookEntity> _book = new MutableLiveData<>();
    public final LiveData<BookEntity> book = _book;

    public void getBookById(int id){
        //Repassada alteraçao vinda do mutable para a funcao
        _book.setValue(bookRepository.getBookById(id));
    }
}