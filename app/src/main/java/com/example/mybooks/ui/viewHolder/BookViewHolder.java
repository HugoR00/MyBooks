package com.example.mybooks.ui.viewHolder;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybooks.R;
import com.example.mybooks.databinding.ItemBookBinding;
import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.ui.listener.BookListener;

//A ViewHolder é a view a qual o adapter vai utilizar para demonstrar os dados do dataset
//Aqui é onde os dados são manipulados VISUALMENTE

public class BookViewHolder extends RecyclerView.ViewHolder {

    //Criado item_book.xml que é a view que vamos utilizar para manipular e apresentar os dados do dataset
    //Após criação instanciamos/bindamos ele aqui na viewholder
    private final ItemBookBinding item;
    private BookListener listener;

    public BookViewHolder(@NonNull ItemBookBinding itemView, BookListener bookListener) {
        super(itemView.getRoot());
        this.item = itemView;
        listener = bookListener;
    }

    //Binda a bookentity a essa view, fazendo com que o que está lá possa ser replicado aqui
    public void bind(BookEntity book){
        item.textviewTitle.setText(book.getTitle());
        item.textviewGenre.setText(book.getGenre());
        item.textviewAuthor.setText(book.getAuthor());

        updateFavoriteIcon(book);
        setGenreBackgroundColor(book);

        item.textviewTitle.setOnClickListener(v -> listener.onClick(book.getId()));
        item.textviewAuthor.setOnClickListener(v -> listener.onClick(book.getId()));
        item.imageviewFavorite.setOnClickListener(v -> listener.onClickFavorite(book.getId()));

    }

    public void updateFavoriteIcon(BookEntity book){
        if (book.isFavorite()){
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite);
        }else{
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty);
        }
    }

    public void setGenreBackgroundColor(BookEntity book){
        if (book.getGenre().equals("Fantasia")) {
            item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_fantasy);
        } else if (book.getGenre().equals("Terror")) {
            item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_horror);
        }else if (book.getGenre().equals("Romance")) {
            item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_romance);
        }else {
            item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_generic);
        }
    }
}
