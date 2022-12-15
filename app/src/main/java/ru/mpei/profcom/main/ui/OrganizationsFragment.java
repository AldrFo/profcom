package ru.mpei.profcom.main.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentOrganizationsBinding;
import ru.mpei.profcom.databinding.ItemOrgBinding;
import ru.mpei.profcom.main.model.OrgViewModel;
import ru.mpei.profcom.main.model.entities.OrgDto;

public class OrganizationsFragment extends BaseFragment<FragmentOrganizationsBinding, OrgViewModel> {

    private final RecyclerViewAdapter<OrgDto, ItemOrgBinding> adapter = new RecyclerViewAdapter<OrgDto, ItemOrgBinding>() {

        @NonNull
        @Override
        public RecyclerViewAdapter.ViewHolder<OrgDto, ItemOrgBinding> onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            return new RecyclerViewAdapter.ViewHolder<>(
                    ItemOrgBinding.inflate(getLayoutInflater(), parent, false),
                    new AdapterCallback<OrgDto, ItemOrgBinding>() {

                        @Override
                        public void bindViews(ItemOrgBinding binding, OrgDto item, int position) {
                            binding.orgItemDescription.setText(item.description);
                            binding.orgItemName.setText(item.name);
                            Picasso.get().load(item.logoUrl).into(binding.orgItemImage);
                        }

                        @Override
                        public void onViewClicked(View view, OrgDto item) {
                            Bundle b = new Bundle();
                            b.putSerializable("organization", item);
                            navigate(MainActivity.ORG_FRAGMENT, b);
                        }
                    }
            );
        }
    };


    public OrganizationsFragment() { super(OrgViewModel.class, FragmentOrganizationsBinding::inflate);}

    @Override
    protected void prepareViewModel() {
        viewModel.observeOrgs(this, adapter::setItems);
    }

    @Override
    protected void bindViews() {
        binding.orgRecycler.setAdapter(adapter);
    }

    @Override
    protected void refresh() {
        viewModel.getOrgs();
    }
}
