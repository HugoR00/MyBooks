package com.example.mybooks.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybooks.databinding.ItemBookBinding;
import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.ui.listener.BookListener;
import com.example.mybooks.ui.viewHolder.BookViewHolder;

import java.util.ArrayList;
import java.util.List;

//O Adapter é a ponte entre o dado e a view, responsável também por criar uma view para cada item no dataset

public class BooksAdapter extends RecyclerView.Adapter<BookViewHolder> {

    //Cria uma lista de bookentity
    private List<BookEntity> booksList = new ArrayList<>();
    private BookListener listener;


    //Infla a viewholder, ou seja, instancia ela, da vida a viewholder
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding view = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new BookViewHolder(view,listener);
    }

    //É o que faz a passagem do dataset e binda a viewholder, no caso bindando a BookViewHolder, o
    //position é a posição do dado que deve ser passado, como é uma lista ele começa em 0 e vai atualizando
    //a cada novo dado que deve ser passado
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(booksList.get(position));
    }

    //Número de itens na lista
    @Override
    public int getItemCount() {
        return booksList.size();
    }

    //Traz os livros que estäo na bookentity e vincula a lista criada aqui
    //Essa atualização é feita lá no observer do fragment
    public void updateBooks(List<BookEntity> books){
        booksList = books;
        notifyDataSetChanged();
    }

    public void attachListener(BookListener bookListener){
        listener = bookListener;
    }
}
