package ru.mpei.profcom.main.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import ru.mpei.profcom.R;
import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentOrganizationsBinding;
import ru.mpei.profcom.databinding.ItemOrgBinding;
import ru.mpei.profcom.main.model.OrgViewModel;
import ru.mpei.profcom.main.model.entities.OrgDto;

public class OrganizationsFragment extends BaseFragment<FragmentOrganizationsBinding, OrgViewModel> {

    public OrganizationsFragment() { super(OrgViewModel.class, FragmentOrganizationsBinding::inflate);}

    @Override
    protected void prepareViewModel() {

    }

    @Override
    protected void bindViews() {
        binding.orgRecycler.setAdapter(new RecyclerViewAdapter<OrgDto, ItemOrgBinding>() {

            @NonNull
            @Override
            public ViewHolder<OrgDto, ItemOrgBinding> onCreateViewHolder(
                    @NonNull ViewGroup parent,
                    int viewType
            ) {
                return new ViewHolder<>(
                        ItemOrgBinding.inflate(getLayoutInflater()),
                        new AdapterCallback<OrgDto, ItemOrgBinding>() {

                            @Override
                            public void bindViews(ItemOrgBinding binding, OrgDto item, int position) {
                                binding.orgItemDescription.setText(item.description);
                                binding.orgItemName.setText(item.name);
                                Picasso.get().load(item.logoUrl).into(binding.orgItemImage);
                            }

                            @Override
                            public void onViewClicked(View view, OrgDto item) {

                            }
                        }
                );
            }
        });
    }
}
