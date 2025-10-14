package com.example.mybooks.ui.viewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybooks.databinding.ItemBookBinding;
import com.example.mybooks.entity.BookEntity;

public class BookViewHolder extends RecyclerView.ViewHolder {

    private final ItemBookBinding item;

    public BookViewHolder(@NonNull ItemBookBinding itemView) {
        super(itemView.getRoot());
        this.item = itemView;
    }

    public void bind(BookEntity book){
        item.textviewTitle.setText(book.getTitle());
    }
}
