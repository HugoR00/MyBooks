package com.example.mybooks.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mybooks.R;
import com.example.mybooks.ui.adapter.BooksAdapter;
import com.example.mybooks.databinding.FragmentHomeBinding;
import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.ui.listener.BookListener;
import com.example.mybooks.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private BooksAdapter adapter = new BooksAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

       binding = FragmentHomeBinding.inflate(inflater, container, false);

       viewModel.getBooks();
       binding.recyclerviewBooks.setLayoutManager(new LinearLayoutManager(getContext()));
       binding.recyclerviewBooks.setAdapter(adapter);

       setObservers();
       attachListener();
       return binding.getRoot();
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
            }
        });
    }

    private void attachListener(){
        BookListener listener = new BookListener() {
            @Override
            public void onClick(int id) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.navigation_details);
            }

            @Override
            public void onClickFavorite(int id) {

            }
        };
        adapter.attachListener(listener);
    }
}