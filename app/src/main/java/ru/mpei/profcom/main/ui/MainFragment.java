package ru.mpei.profcom.main.ui;

import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentMainBinding;
import ru.mpei.profcom.main.model.MainViewModel;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainViewModel> {

    public MainFragment() {
        super(MainViewModel.class, FragmentMainBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {}

    @Override
    protected void bindViews() {
        binding.newsButton.setOnClickListener(view -> {

        });
    }
}