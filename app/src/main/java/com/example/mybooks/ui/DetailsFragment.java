package com.example.mybooks.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        //bookId criado e pegando o valor de id vinculado a key bookID no repo
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

        //Identifica a deleção do livro e mostra que foi removido com sucesso
        viewModel.bookDeleted.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(getContext(), R.string.msg_removed_successfully,Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
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
        //Ao clicar no botão ele aparece o aviso de se realmente deseja deletar
        binding.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRemove();
            }
        });
    }


    //Func pra perguntar se a pessoa realmente deseja deletar o livro, se sim
    //ele deleta a partir do código "viewModel.deleteBook(bookId)"
    private void handleRemove(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.dialog_message_delete_item)
                .setPositiveButton(R.string.dialog_positive_button_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.deleteBook(bookId);
                    }
                })
                .setNegativeButton(R.string.dialog_negative_button_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
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