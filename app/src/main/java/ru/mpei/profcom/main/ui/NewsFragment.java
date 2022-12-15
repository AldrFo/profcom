package ru.mpei.profcom.main.ui;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentNewsBinding;
import ru.mpei.profcom.databinding.ItemNewsBinding;
import ru.mpei.profcom.main.model.NewsViewModel;
import ru.mpei.profcom.main.model.entities.NewsDto;

public class NewsFragment extends BaseFragment<FragmentNewsBinding, NewsViewModel> {

    private final RecyclerViewAdapter<NewsDto, ItemNewsBinding> adapter = new RecyclerViewAdapter<NewsDto, ItemNewsBinding>() {

        @NonNull
        @Override
        public ViewHolder<NewsDto, ItemNewsBinding> onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            return new ViewHolder<>(
                    ItemNewsBinding.inflate(getLayoutInflater(), parent, false),
                    new AdapterCallback<NewsDto, ItemNewsBinding>() {
                        @Override
                        public void bindViews(ItemNewsBinding binding, NewsDto item, int position) {
                            binding.typeText.setText(item.type);
                            binding.titleText.setText(item.title);
                            binding.descriptionText.setText(item.description);
                        }

                        @Override
                        public void onViewClicked(View view, NewsDto item) {}
                    }
            );
        }
    };

    public NewsFragment() {
        super(NewsViewModel.class, FragmentNewsBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeNews(this, dtos -> {
            adapter.setItems(dtos);
        });
    }

    @Override
    protected void bindViews() {
        binding.newsRecycler.setAdapter(adapter);
    }

    @Override
    protected void refresh() {
        viewModel.getNews();
    }
}
