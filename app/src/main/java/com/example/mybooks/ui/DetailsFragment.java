package com.example.mybooks.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybooks.R;
import com.example.mybooks.databinding.FragmentDetailsBinding;
import com.example.mybooks.databinding.FragmentHomeBinding;
import com.example.mybooks.entity.BookEntity;
import com.example.mybooks.viewmodel.DetailsViewModel;
import com.example.mybooks.viewmodel.HomeViewModel;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private DetailsViewModel viewModel;
    private int bookId = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        //bookId criado e pegando o valor de id vinculado ao bookID
        bookId = getArguments() != null ? getArguments().getInt("bookId", 0) : 0;
        //id passado a viewModel para retornar livro
        viewModel.getBookById(bookId);

        setObservers();
        setListeners();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setObservers(){
        //BOOK pego da viewmodel, que por sua vez pegou do Repository, esse book é passado via
        //mutable livedata-livedata e aqui é observado o LIVEDATA book
        viewModel.book.observe(getViewLifecycleOwner(), new Observer<BookEntity>() {
            @Override
            public void onChanged(BookEntity book) {
                binding.textviewTitle.setText(book.getTitle());
                binding.textviewAuthorValue.setText(book.getAuthor());
                binding.textviewGenreValue.setText(book.getGenre());
                binding.checkboxFavorite.setChecked(book.isFavorite());
                setGenreBackgroundColor(book);
            }
        });
    }

    private void setListeners(){
        binding.checkboxFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.toggleFavoriteStatus(bookId);
            }
        });
        binding.imageviewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pega a activity em que a fragment esta inserida
                //Pega o gerenciador das fragments
                //Navega para o destino anterior
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public void setGenreBackgroundColor(BookEntity book){
        if (book.getGenre().equals("Fantasia")) {
            binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_fantasy);
        } else if (book.getGenre().equals("Terror")) {
            binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_horror);
        }else if (book.getGenre().equals("Romance")) {
            binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_romance);
        }else {
            binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_generic);
        }
    }

}