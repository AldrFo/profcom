package ru.mpei.profcom.entry.ui;

import android.os.Bundle;
import android.widget.Toast;

import kotlin.text.Regex;
import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentRegisterBinding;
import ru.mpei.profcom.entry.model.EntryViewModel;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, EntryViewModel> {

    public RegisterFragment() {
        super(EntryViewModel.class, FragmentRegisterBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeRegister(getViewLifecycleOwner(), response -> {
            Bundle b = new Bundle();
            b.putString("email", binding.editEmail.getText().toString());
            b.putString("password", binding.editPassword.getText().toString());
            navigate(MainActivity.CATEGORY_FRAGMENT, b);
        });
    }

    @Override
    protected void bindViews() {
        binding.registerBtn.setOnClickListener(view -> register());
    }

    private void register(){

        Regex emailRg = new Regex("[a-zA-Z0-9]+@mpei.ru");
        Regex groupRg = new Regex("[А-Яа-я]+-\\d\\d-\\d\\dм?");
        Regex cardRg = new Regex("[0-9]+");

        String email = binding.editEmail.getText().toString();
        String firstPass = binding.editPassword.getText().toString();
        String secondPass = binding.editPassConfirm.getText().toString();
        String group = binding.editGroup.getText().toString();
        String card = binding.editCardNum.getText().toString();

        if(firstPass.length() <= 6){
            Toast.makeText(requireContext(), "Пароль должен быть больше 6 символов", Toast.LENGTH_LONG).show();
            return;
        }
        if(!firstPass.equals(secondPass)) {
            Toast.makeText(requireContext(), "Пароли не совпадают!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!emailRg.matches(email)) {
            Toast.makeText(requireContext(), "Некорректная почта!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!groupRg.matches(group)){
            Toast.makeText(requireContext(), "Некорректная группа!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!cardRg.matches(card)){
            Toast.makeText(requireContext(), "Некорректный номер карты!", Toast.LENGTH_LONG).show();
            return;
        }

        viewModel.register(
            binding.editEmail.getText().toString(),
            binding.editGroup.getText().toString(),
            binding.editCardNum.getText().toString(),
            binding.editPassword.getText().toString()
        );
    }
}
