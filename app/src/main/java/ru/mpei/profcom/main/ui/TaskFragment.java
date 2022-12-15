package ru.mpei.profcom.main.ui;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.Inflater;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentTasksBinding;
import ru.mpei.profcom.databinding.ItemEventBinding;
import ru.mpei.profcom.databinding.ItemTaskBinding;
import ru.mpei.profcom.main.model.TaskViewModel;
import ru.mpei.profcom.main.model.entities.EventDto;
import ru.mpei.profcom.main.model.entities.TaskDto;

public class TaskFragment extends BaseFragment<FragmentTasksBinding, TaskViewModel> {

    public TaskFragment() {super(TaskViewModel.class, FragmentTasksBinding::inflate);}

    private final RecyclerViewAdapter<TaskDto, ItemTaskBinding> adapter = new RecyclerViewAdapter<TaskDto, ItemTaskBinding>() {
        @NonNull
        @Override
        public ViewHolder<TaskDto, ItemTaskBinding> onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            return new ViewHolder<TaskDto, ItemTaskBinding>(
                    ItemTaskBinding.inflate(getLayoutInflater(), parent, false),
                    new AdapterCallback<TaskDto, ItemTaskBinding>() {
                        @Override
                        public void bindViews(ItemTaskBinding binding, TaskDto item, int position) {
                            binding.taskName.setText(item.name);
                            binding.taskDescription.setText(item.description);
                            binding.taskStart.setText(item.start);
                            binding.taskDeadline.setText(item.deadline);
                            binding.taskCheckbox.setOnClickListener( v -> {
                                binding.taskCheckbox.setChecked(true);
                                adapter.removeItem(item);
                           });
                        }

                        @Override
                        public void onViewClicked(View view, TaskDto item) {}
                    }
            );
        }
    };

    @Override
    protected void prepareViewModel() {viewModel.observeTasksData(this, adapter::setItems);}

    @Override
    protected void bindViews() {binding.tasksRecycler.setAdapter(adapter);}

    @Override
    protected void refresh(){viewModel.getTasksData();}
}
