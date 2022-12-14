package ru.mpei.profcom.entry.ui;

import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentRegisterBinding;
import ru.mpei.profcom.entry.model.EntryViewModel;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding, EntryViewModel> {

    public RegisterFragment() {
        super(EntryViewModel.class, FragmentRegisterBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {

    }

    @Override
    protected void bindViews() {

    }
}
