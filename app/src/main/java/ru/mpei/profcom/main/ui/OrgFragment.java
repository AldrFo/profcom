package ru.mpei.profcom.main.ui;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.squareup.picasso.Picasso;

import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentOrgBinding;
import ru.mpei.profcom.main.model.OrgViewModel;
import ru.mpei.profcom.main.model.entities.OrgDto;

public class OrgFragment extends BaseFragment<FragmentOrgBinding, OrgViewModel> {

    private final OrgDto item;

    public OrgFragment(Bundle bundle) {
        super(OrgViewModel.class, FragmentOrgBinding::inflate);
        this.item = (OrgDto) bundle.getSerializable("organization");
    }

    @Override
    protected void prepareViewModel() {}

    @Override
    protected void bindViews() {
        binding.orgDescription.setText(item.description);
        binding.orgName.setText(item.name);
        Picasso.get().load(item.logoUrl).into(binding.orgImage);
    }

}
