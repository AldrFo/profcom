package ru.mpei.profcom.main.ui;

import android.view.View;

import androidx.lifecycle.Observer;

import java.util.Objects;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.Inflater;
import ru.mpei.profcom.databinding.FragmentProfileBinding;
import ru.mpei.profcom.entry.model.UserData;
import ru.mpei.profcom.main.model.ProfileViewModel;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> {

    public ProfileFragment() {super(ProfileViewModel.class, FragmentProfileBinding::inflate);}

    @Override
    protected void prepareViewModel() {
        viewModel.getUserData(MainActivity.prefs.getInt("id", -1));
        viewModel.observeUserLiveData(this, userData -> {
            if(Objects.equals(userData.type, "standart")){
                binding.bookBtn.setVisibility(View.VISIBLE);
                binding.enterPbBtn.setVisibility(View.VISIBLE);
            }
            else if (Objects.equals(userData.type, "profcom")){
                binding.bookBtn.setVisibility(View.VISIBLE);
                binding.metodBtn.setVisibility(View.VISIBLE);
            }
            else{
                binding.bookBtn.setVisibility(View.VISIBLE);
                binding.metodBtn.setVisibility(View.VISIBLE);
                binding.boardBtn.setVisibility(View.VISIBLE);
            }
            binding.userEmail.setText(userData.email);
            binding.userGroup.setText(userData.group);
            binding.userCardNumber.setText(userData.profCard);

        });
    }

    @Override
    protected void bindViews() {
        binding.bookBtn.setOnClickListener(view -> {
            navigate(MainActivity.EVENTS_FRAGMENT, null);
        });
    }
}
