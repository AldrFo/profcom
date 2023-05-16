package ru.mpei.profcom.main.ui;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentEventsBinding;
import ru.mpei.profcom.databinding.ItemEventBinding;
import ru.mpei.profcom.main.model.EventsViewModel;
import ru.mpei.profcom.main.model.entities.EventDto;

public class EventsFragment extends BaseFragment<FragmentEventsBinding, EventsViewModel> {

    private final RecyclerViewAdapter<EventDto, ItemEventBinding> eventsAdapter = new RecyclerViewAdapter<EventDto, ItemEventBinding>() {
        @NonNull
        @Override
        public ViewHolder<EventDto, ItemEventBinding> onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            return new ViewHolder<>(
                    ItemEventBinding.inflate(getLayoutInflater(), parent, false),
                    new AdapterCallback<EventDto, ItemEventBinding>() {
                        @Override
                        public void bindViews(ItemEventBinding binding, EventDto item, int position) {
                            binding.eventName.setText(item.name);
                            binding.eventDescription.setText(item.description);
                            String link = "<a href=\"" + item.link + "\"> " + item.link + "</a>";
                            binding.eventLink.setText(Html.fromHtml(link));
                            binding.eventLink.setMovementMethod(LinkMovementMethod.getInstance());
                            binding.registerButton.setVisibility(item.canRegister ? View.VISIBLE : View.GONE);
                            binding.registerButton.setOnClickListener((view) -> {
                                list.get(position).alreadyGoing = true;
                                viewModel.addEventToGoing(item.id);
                                binding.registerButton.setEnabled(false);
                                binding.registerButton.setText("Уже иду...");
                            });
                            if (item.alreadyGoing) {
                                binding.registerButton.setEnabled(false);
                                binding.registerButton.setText("Уже иду...");
                            }
                        }

                        @Override
                        public void onViewClicked(View view, EventDto item) {}
                    }
            );
        }
    };

    public EventsFragment() {super(EventsViewModel.class, FragmentEventsBinding::inflate);}

    @Override
    protected void prepareViewModel() {
        viewModel.observeEventsData(this, eventsAdapter::setItems);
    }

    @Override
    protected void bindViews() {
        binding.eventRecycler.setAdapter(eventsAdapter);
    }

    @Override
    protected void refresh(){ viewModel.getEvents(MainActivity.prefs.getInt("id", -1)); }
}
