package ru.mpei.profcom.core;

import android.view.View;

import androidx.viewbinding.ViewBinding;

public interface AdapterCallback <DATA, B extends ViewBinding>{

    void bindViews(B binding,DATA item, int position);

    void onViewClicked(View view, DATA item);

}
