package ru.mpei.profcom.core;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;

public interface Inflater<B extends ViewBinding> {

    B inflate(LayoutInflater inflater ,ViewGroup container, boolean attachToParent);

}
