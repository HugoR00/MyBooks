package com.example.mybooks.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.repository.BookRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private BookRepository bookRepository = BookRepository.getInstance();

    //Capta as mudanças na lista
    private final MutableLiveData<List<BookEntity>> _books = new MutableLiveData<>();

    //Apresenta as mudanças e protege o mutable de alterações
    public final LiveData<List<BookEntity>> books = _books;

    //Pega os livros e adiciona ao mutable, usando a função do repository
    public void getBooks(){
        _books.setValue(bookRepository.getBooks());
    }

    public void toggleFavoriteStatus(int id){
        bookRepository.toggleFavoriteStatus(id);
        getBooks();
    }
}