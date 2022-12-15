package ru.mpei.profcom.main.ui;

import androidx.lifecycle.Observer;

import com.squareup.picasso.Picasso;

import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.databinding.FragmentOrgBinding;
import ru.mpei.profcom.main.model.OrgViewModel;
import ru.mpei.profcom.main.model.entities.OrgDto;

public class OrgFragment extends BaseFragment<FragmentOrgBinding, OrgViewModel> {

    public OrgFragment() { super(OrgViewModel.class, FragmentOrgBinding::inflate);}

    private OrgDto item; //либо инициализируй его, либо удали его, либо иди нахуй

    @Override
    protected void prepareViewModel() {

    }

    @Override
    protected void bindViews() {
        binding.orgDescription.setText(item.description);
        binding.orgName.setText(item.name);
        Picasso.get().load(item.logoUrl).into(binding.orgImage);
    }


}
