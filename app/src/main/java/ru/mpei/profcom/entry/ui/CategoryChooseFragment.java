package ru.mpei.profcom.entry.ui;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ru.mpei.profcom.R;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.Categories;
import ru.mpei.profcom.databinding.FragmentCategoryBinding;
import ru.mpei.profcom.entry.model.EntryViewModel;

public class CategoryChooseFragment extends BaseFragment<FragmentCategoryBinding, EntryViewModel> {

    private Categories choice;

    public CategoryChooseFragment() { super(EntryViewModel.class, FragmentCategoryBinding::inflate); }

    @Override
    protected void prepareViewModel() {

    }

    @Override
    protected void bindViews() {
        binding.categoriesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                setChoice(id);
            }
        });

        binding.confirmChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmChoice();
            }
        });
    }

    public void confirmChoice(){
        viewModel.postCategoryValue(choice);
    }

    private void setChoice(int id){
        switch(id) {
            case R.id.student_radio:
                choice = Categories.STUDENT;
                break;
            case R.id.profcom_member_radio:
                choice = Categories.MEMBER;
                break;
            case R.id.profcom_worker_radio:
                choice = Categories.WORKER;
                break;
            case R.id.profcom_boss_radio:
                choice = Categories.BOSS;
                break;
        }
    }

}
