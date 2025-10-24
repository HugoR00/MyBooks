package com.example.mybooks.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mybooks.R;
import com.example.mybooks.databinding.FragmentFavoriteBinding;
import com.example.mybooks.databinding.FragmentHomeBinding;
import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.ui.adapter.BooksAdapter;
import com.example.mybooks.ui.listener.BookListener;
import com.example.mybooks.viewmodel.FavoriteViewModel;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private final BooksAdapter adapter = new BooksAdapter();
    FavoriteViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);

        binding.recyclerviewFavoriteBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerviewFavoriteBooks.setAdapter(adapter);

        setObservers();
        attachListener();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getBooks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setObservers(){
        viewModel.books.observe(getViewLifecycleOwner(), new Observer<List<BookEntity>>() {
            @Override
            public void onChanged(List<BookEntity> bookEntities) {
                adapter.updateBooks(bookEntities);
                showEmptyList(bookEntities.isEmpty());
            }
        });
    }

    private void showEmptyList(boolean isEmpty){
        binding.recyclerviewFavoriteBooks.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        binding.textviewNoBooks.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        binding.imageviewNoBooks.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    private void attachListener(){
        BookListener listener = new BookListener() {
            @Override
            public void onClick(int id) {
                //Bundle pegando o ID do livro e vinculando a key bookId
                Bundle bundle = new Bundle();
                bundle.putInt("bookId", id);


                NavHostFragment.findNavController(FavoriteFragment.this)
                        .navigate(R.id.navigation_details, bundle); //Navegaçao para o navigation details e com a passagem do bundle contendo o bookId
            }

            @Override
            public void onClickFavorite(int id) {
                viewModel.toggleFavoriteStatus(id);
            }
        };
        adapter.attachListener(listener);
    }
}