package ru.mpei.profcom.core;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewAdapter <DATA, B extends ViewBinding>
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder<DATA, B>>{

    protected ArrayList<DATA> list = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<DATA> items){
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(DATA item){
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addItems(List<DATA> items){
        list.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(DATA item){
        int index = list.indexOf(item);
        list.remove(item);
        notifyItemRemoved(index);
    }

    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public abstract ViewHolder<DATA, B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<DATA, B> holder, int position) {
        holder.bind(list.get(position), position);
    }

    protected static class ViewHolder<DATA, B extends ViewBinding> extends RecyclerView.ViewHolder{

        private final AdapterCallback<DATA, B> callbacks;
        private final B binding;

        public ViewHolder(@NonNull B binding, AdapterCallback<DATA, B> callbacks) {
            super(binding.getRoot());
            this.binding = binding;
            this.callbacks = callbacks;
        }

        public void bind(DATA item, int position){
            callbacks.bindViews(binding, item, position);
            binding.getRoot().setOnClickListener(view -> {
                callbacks.onViewClicked(binding.getRoot(), item);
            });
        }
    }

}
