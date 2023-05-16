package ru.mpei.profcom.main.ui;

import android.app.Dialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.R;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.NavigationController;
import ru.mpei.profcom.databinding.FragmentProfileBinding;
import ru.mpei.profcom.main.model.ProfileViewModel;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> {

    public ProfileFragment() { super(ProfileViewModel.class, FragmentProfileBinding::inflate); }

    @Override
    protected void prepareViewModel() {
        viewModel.getUserData(MainActivity.prefs.getInt("id", -1));
        viewModel.observeUserLiveData(this, userData -> {
            binding.userEmail.setText(userData.email);
            binding.userGroup.setText(userData.group);
            binding.userCardNumber.setText(userData.profCard);
            setVisibles(userData.type);
        });
        viewModel.observeAvailableTime(this, this::openPkRequestDialog);
    }

    @Override
    protected void bindViews() {
        binding.bookBtn.setOnClickListener(view -> navigate(MainActivity.EVENTS_FRAGMENT, null));
        binding.requestButton.setOnClickListener(view -> navigate(MainActivity.REQUEST_FRAGMENT, null));
        binding.exitButton.setOnClickListener(view ->{
            ((NavigationController)requireActivity()).clear();
            navigate(MainActivity.ENTRY_FRAGMENT, null);
        });
        binding.metodBtn.setOnClickListener(view -> navigate(MainActivity.LEARNING_FRAGMENT, null));
        binding.boardBtn.setOnClickListener(view -> navigate(MainActivity.TASKS_FRAGMENT, null));
        binding.gratitudesButton.setOnClickListener(view -> navigate(MainActivity.GRATITUDES_FRAGMENT, null));
        binding.pkRequest.setOnClickListener(view -> viewModel.getAvailableTime());
    }

    private void setVisibles(String type){
        binding.requestButton.setVisibility(View.VISIBLE);
        switch (type){
            case "profcom":
            case "president":
                binding.bookBtn.setVisibility(View.VISIBLE);
                binding.metodBtn.setVisibility(View.VISIBLE);
                break;
            case "pb":
                binding.bookBtn.setVisibility(View.VISIBLE);
                binding.metodBtn.setVisibility(View.VISIBLE);
                binding.boardBtn.setVisibility(View.VISIBLE);
                break;
            case "standart":
                binding.bookBtn.setVisibility(View.VISIBLE);
                break;
            default:

        }
    }

    private void openPkRequestDialog(List<String> availableTime){
        final Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.fragment_pk_request);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, availableTime);
        Spinner spinner = dialog.findViewById(R.id.timeSpinner);
        spinner.setAdapter(adapter);
        dialog.findViewById(R.id.sendButton).setOnClickListener(view -> {
            viewModel.sendPkRequest(
                spinner.getSelectedItem().toString(),
                ((EditText)dialog.findViewById(R.id.hintText)).getText().toString(),
                ((EditText)dialog.findViewById(R.id.linkText)).getText().toString()
            );
            dialog.cancel();
        });

        dialog.show();

    }
}
