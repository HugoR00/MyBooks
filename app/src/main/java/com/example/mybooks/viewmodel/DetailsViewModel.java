package com.example.mybooks.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.repository.BookRepository;

public class DetailsViewModel extends ViewModel {

    private final BookRepository bookRepository = BookRepository.getInstance();

    //Criaçao de mutable/livedata para vincular informacao do livro ao observer
    private final MutableLiveData<BookEntity> _book = new MutableLiveData<>();
    public final LiveData<BookEntity> book = _book;

    //Mutable live data pra acompanhar a deleção do livro
    private final MutableLiveData<Boolean> _bookDeleted = new MutableLiveData<>();
    public final LiveData<Boolean> bookDeleted = _bookDeleted;

    public void getBookById(int id){
        //Repassada alteraçao vinda do mutable para a funcao
        _book.setValue(bookRepository.getBookById(id));
    }

    public void toggleFavoriteStatus(int id){
        bookRepository.toggleFavoriteStatus(id);
    }

    //Func para deletar o livro, vinculando o mutable ao que foi deletado no repository
    public void deleteBook(int id){
        _bookDeleted.setValue(bookRepository.deleteBook(id));
    }
}