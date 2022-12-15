package ru.mpei.profcom.main.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentInfoBinding;
import ru.mpei.profcom.databinding.ItemInfoBinding;
import ru.mpei.profcom.databinding.ItemNewsBinding;
import ru.mpei.profcom.main.model.InfoViewModel;
import ru.mpei.profcom.main.model.entities.InfoDto;
import ru.mpei.profcom.main.model.entities.NewsDto;

public class InfoFragment extends BaseFragment<FragmentInfoBinding, InfoViewModel> {

    private RecyclerViewAdapter<InfoDto, ItemInfoBinding> adapter = new RecyclerViewAdapter<InfoDto, ItemInfoBinding>() {

        @NonNull
        @Override
        public ViewHolder<InfoDto, ItemInfoBinding> onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            return new ViewHolder<>(
                    ItemInfoBinding.inflate(getLayoutInflater(), parent, false),
                    new AdapterCallback<InfoDto, ItemInfoBinding>() {
                        @Override
                        public void bindViews(ItemInfoBinding binding, InfoDto item, int position) {
                            binding.infoDescription.setText(item.description);
                            binding.infoTitle.setText(item.title);
                            Picasso.get().load(item.imageUrl).into(binding.infoImage);
                        }

                        @Override
                        public void onViewClicked(View view, InfoDto item) {

                        }
                    }
            );
        }
    };

    public InfoFragment() { super(InfoViewModel.class, FragmentInfoBinding::inflate);}

    @Override
    protected void prepareViewModel() {
        viewModel.observeInfoDataList(this, list -> {
            adapter.setItems(list);
        });
    }

    @Override
    protected void bindViews() {
        binding.infoRecycler.setAdapter(adapter);
    }

    @Override
    protected void refresh() {
        viewModel.getInfo();
    }
}
