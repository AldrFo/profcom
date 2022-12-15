package ru.mpei.profcom.entry.ui;


import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentEntryBinding;
import ru.mpei.profcom.entry.model.EntryViewModel;

public class EntryFragment extends BaseFragment<FragmentEntryBinding, EntryViewModel> {

    public EntryFragment() {
        super(EntryViewModel.class, FragmentEntryBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeEntry(this, response -> {
            if(response.isSuccessful())
                navigate(MainActivity.MAIN_FRAGMENT, null);
        });
    }

    @Override
    protected void bindViews() {
        binding.authButton.setOnClickListener(view -> {
            viewModel.entry(
                binding.loginEditText.getText().toString(),
                binding.passEditText.getText().toString()
            );
        });
        binding.registerButton.setOnClickListener(view -> {
            navigate(MainActivity.REGISTER_FRAGMENT, null);
        });
    }

}
