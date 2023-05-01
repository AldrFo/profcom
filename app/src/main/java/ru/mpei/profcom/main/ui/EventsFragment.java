package ru.mpei.profcom.main.ui;

import android.app.Dialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.mpei.profcom.MainActivity;
import ru.mpei.profcom.R;
import ru.mpei.profcom.core.AdapterCallback;
import ru.mpei.profcom.core.BaseFragment;
import ru.mpei.profcom.core.RecyclerViewAdapter;
import ru.mpei.profcom.databinding.FragmentAddEventBinding;
import ru.mpei.profcom.databinding.FragmentEventsBinding;
import ru.mpei.profcom.databinding.ItemEventBinding;
import ru.mpei.profcom.main.model.EventsViewModel;
import ru.mpei.profcom.main.model.entities.EventDto;

public class EventsFragment extends BaseFragment<FragmentEventsBinding, EventsViewModel> {

    private final RecyclerViewAdapter<EventDto, ItemEventBinding> adapter = new RecyclerViewAdapter<EventDto, ItemEventBinding>() {
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
                        }

                        @Override
                        public void onViewClicked(View view, EventDto item) {

                        }
                    }
            );
        }
    };


    private void openAddEventDialog(){
        final Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.fragment_add_event);
        dialog.setTitle("Добавление мероприятия");

        dialog.findViewById(R.id.save_event_btn).setOnClickListener(view -> {
                EventDto event = new EventDto(
                        MainActivity.prefs.getInt("id", -1),
                        ((EditText) dialog.findViewById(R.id.edit_event_name)).getText().toString(),
                        ((EditText) dialog.findViewById(R.id.edit_event_description)).getText().toString(),
                        ((EditText) dialog.findViewById(R.id.edit_event_link)).getText().toString()
                );
                adapter.addItem(event);
                viewModel.addEvent(event.id, event.name, event.description, event.link);
                dialog.cancel();
            }
        );

        dialog.show();

    }


    public EventsFragment() {super(EventsViewModel.class, FragmentEventsBinding::inflate);}

    @Override
    protected void prepareViewModel() {
        viewModel.observeEventsData(this, adapter::setItems);
    }

    @Override
    protected void bindViews() {
        binding.eventRecycler.setAdapter(adapter);
        binding.addEventBtn.setOnClickListener(v -> {openAddEventDialog();});
    }

    @Override
    protected void refresh(){ viewModel.getEvents(MainActivity.prefs.getInt("id", -1)); }
}
