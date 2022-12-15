package ru.mpei.profcom.entry.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Locale;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.R;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentCategoryBinding;
import ru.mpei.profcom.entry.model.EntryViewModel;

public class CategoryChooseFragment extends BaseFragment<FragmentCategoryBinding, EntryViewModel> {

    private String choice;
    private Bundle bundle;

    public CategoryChooseFragment(Bundle bundle) {
        super(EntryViewModel.class, FragmentCategoryBinding::inflate);
        this.bundle = bundle;
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeCategory(this, response -> {
            navigate(MainActivity.MAIN_FRAGMENT, null);
        });
    }

    @Override
    protected void bindViews() {
        binding.categoriesRadioGroup.setOnCheckedChangeListener((radioGroup, id) -> setChoice(id));
        binding.confirmChoiceBtn.setOnClickListener(view -> confirmChoice());
    }

    public void confirmChoice(){
        if(getPdIbByName(binding.editTextTextPersonName.getText().toString()) == -1 && choice.equals("pb")) {
            Toast.makeText(requireContext(), "Введите название ПБ", Toast.LENGTH_LONG).show();
            return;
        }
        viewModel.setUserTypeWithEntry(
                bundle.getString("email"),
                bundle.getString("password"),
                choice,
                getPdIbByName(binding.editTextTextPersonName.getText().toString())
        );
    }

    private int getPdIbByName(String name){
        switch(name.toUpperCase(Locale.ROOT)){
            case "ПБ ИВТИ":
                return 1;
            default:
                Toast.makeText(requireContext(), "Такого ПБ нет!", Toast.LENGTH_LONG).show();
                return -1;
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void setChoice(int id){
        switch(id) {
            case R.id.pb_member_radio:
                choice = "pb";
                break;
            case R.id.profcom_worker_radio:
                choice = "profcom";
                break;
            case R.id.profcom_boss_radio:
                choice = "president";
                break;
        }
    }

}
