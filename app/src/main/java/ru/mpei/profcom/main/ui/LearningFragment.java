package ru.mpei.profcom.main.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Observable;
import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentLearningBinding;
import ru.mpei.profcom.databinding.ItemLearningBinding;
import ru.mpei.profcom.main.model.LearningViewModel;
import ru.mpei.profcom.main.model.entities.LearnDto;

public class LearningFragment extends BaseFragment<FragmentLearningBinding, LearningViewModel> {

    private RecyclerViewAdapter<LearnDto, ItemLearningBinding> adapter = new RecyclerViewAdapter<LearnDto, ItemLearningBinding>() {
        @NonNull
        @Override
        public ViewHolder<LearnDto, ItemLearningBinding> onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            return new ViewHolder<>(
                    ItemLearningBinding.inflate(getLayoutInflater(), parent, false),
                    new AdapterCallback<LearnDto, ItemLearningBinding>() {
                        @Override
                        public void bindViews(ItemLearningBinding binding, LearnDto item, int position) {
                            binding.title.setText(item.name);
                            binding.descriptionText.setText(item.description);
                        }

                        @Override
                        public void onViewClicked(View view, LearnDto item) {}
                    }
            );
        }
    };

    public LearningFragment() {
        super(LearningViewModel.class, FragmentLearningBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeLearnData(this, list -> {
            adapter.setItems(list);
        });
    }

    @Override
    protected void bindViews() {
        binding.learningRecycler.setAdapter(adapter);
    }

    @Override
    protected void refresh() {
        viewModel.getLearnings();
    }
}
