package ru.mpei.profcom.entry.ui;

import android.util.Log;

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
            Log.d("pu-pu", String.valueOf(response.code()));
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
    }

    @Override
    protected void refresh() {

    }
}
