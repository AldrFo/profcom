package ru.mpei.profcom.entry.ui;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentRegisterBinding;
import ru.mpei.profcom.entry.model.EntryViewModel;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, EntryViewModel> {

    public RegisterFragment() {
        super(EntryViewModel.class, FragmentRegisterBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeRegister(getViewLifecycleOwner(), new Observer<Response<ResponseBody>>() {
            @Override
            public void onChanged(Response<ResponseBody> responseBodyResponse) {

            }
        });
    }

    @Override
    protected void bindViews() {
        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){

        if(binding.editPassword.getText() != binding.editPassConfirm.getText())
            return;

        viewModel.register(
            binding.editEmail.getText().toString(),
            binding.editGroup.getText().toString(),
            binding.editCardNum.getText().toString(),
            binding.editPassword.getText().toString()
        );
    }
}
