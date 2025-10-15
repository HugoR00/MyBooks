package com.example.mybooks.ui.viewHolder;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybooks.R;
import com.example.mybooks.databinding.ItemBookBinding;
import com.example.mybooks.entity.BookEntity;

//A ViewHolder é a view a qual o adapter vai utilizar para demonstrar os dados do dataset
//Aqui é onde os dados são manipulados VISUALMENTE

public class BookViewHolder extends RecyclerView.ViewHolder {

    //Criado item_book.xml que é a view que vamos utilizar para manipular e apresentar os dados do dataset
    //Após criação instanciamos/bindamos ele aqui na viewholder
    private final ItemBookBinding item;

    public BookViewHolder(@NonNull ItemBookBinding itemView) {
        super(itemView.getRoot());
        this.item = itemView;
    }

    //Binda a bookentity a essa view, fazendo com que o que está lá possa ser replicado aqui
    public void bind(BookEntity book){
        item.textviewTitle.setText(book.getTitle());
        item.textviewGenre.setText(book.getGenre());
        item.textviewAuthor.setText(book.getAuthor());

        if (book.isFavorite()){
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite);
        }else{
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty);
        }
    }
}
