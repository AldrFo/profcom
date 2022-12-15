package ru.mpei.profcom.entry.ui;

import android.os.Bundle;
import android.widget.Toast;

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

        if(!binding.editPassword.getText().toString().equals(binding.editPassConfirm.getText().toString())) {
            Toast.makeText(requireContext(), "Пароли не совпадают!", Toast.LENGTH_LONG).show();
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
