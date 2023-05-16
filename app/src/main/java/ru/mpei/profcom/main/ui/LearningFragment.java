package ru.mpei.profcom.main.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentLearningBinding;
import ru.mpei.profcom.databinding.ItemLearningBinding;
import ru.mpei.profcom.main.model.LearningViewModel;
import ru.mpei.profcom.main.model.entities.LearnDto;

public class LearningFragment extends BaseFragment<FragmentLearningBinding, LearningViewModel> {

    private final RecyclerViewAdapter<LearnDto, ItemLearningBinding> adapter = new RecyclerViewAdapter<LearnDto, ItemLearningBinding>() {
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
                        public void onViewClicked(View view, LearnDto item) {
                            Bundle b = new Bundle();
                            b.putSerializable("learn", item);
                            navigate(MainActivity.LEARN_FRAGMENT, b);
                        }
                    }
            );
        }
    };

    public LearningFragment() {
        super(LearningViewModel.class, FragmentLearningBinding::inflate);
    }

    @Override
    protected void prepareViewModel() {
        viewModel.observeLearnData(this, adapter::setItems);
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
