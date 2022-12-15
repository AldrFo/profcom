package ru.mpei.profcom.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment <B extends ViewBinding, VM extends ViewModel> extends Fragment {

    protected B binding;
    protected VM viewModel;

    private final Class<VM> viewModelClass;
    private final Inflater<B> inflater;

    public BaseFragment(Class<VM> viewModelClass, Inflater<B> inflater){
        this.viewModelClass = viewModelClass;
        this.inflater = inflater;
    }

    protected abstract void prepareViewModel();

    protected  abstract void bindViews();

    protected void refresh(){}

    protected void navigate(int fragmentId, Bundle bundle){
        if(!(getActivity() instanceof NavigationController))
            return;

        ((NavigationController) getActivity()).navigate(fragmentId, bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = inflateBinding(this.inflater, container);
        viewModel = instanceViewModel();
        prepareViewModel();
        bindViews();
        refresh();
        return binding.getRoot();
    }

    protected B inflateBinding(Inflater<B> inflater, ViewGroup container){
        return inflater.inflate(getLayoutInflater(), container, false);
    }

    private VM instanceViewModel(){
        return (new ViewModelProvider(
                (ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory())
        ).get(viewModelClass);
    }
}
