package ru.mpei.profcom.main.ui;

import android.widget.Toast;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentRequestBinding;
import ru.mpei.profcom.main.model.ProfileViewModel;

public class RequestFragment extends BaseFragment<FragmentRequestBinding, ProfileViewModel> {

    public RequestFragment() {
        super(ProfileViewModel.class, FragmentRequestBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeRequestSend(this, b -> {
            Toast.makeText(requireContext(), "Запрос отправлен! Позже с вами свяжуться по почте.", Toast.LENGTH_LONG).show();
            navigate(MainActivity.PROFILE_FRAGMENT, null);
        });
    }

    @Override
    protected void bindViews() {
        binding.sendButton.setOnClickListener(v -> viewModel.sendRequest(
                binding.requestEditText.getText().toString()
        ));
    }
}
