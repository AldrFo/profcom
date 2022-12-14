package ru.mpei.profcom;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import ru.mpei.profcom.core.NavigationController;
import ru.mpei.profcom.entry.ui.EntryFragment;

public class MainActivity extends AppCompatActivity implements NavigationController {

    public static final int ENTRY_FRAGMENT = 0;

    public static SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);
        navigate(ENTRY_FRAGMENT);
    }

    @Override
    public void navigate(int fragmentId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (fragmentId){
            case ENTRY_FRAGMENT:
                ft.replace(R.id.main_container, new EntryFragment());
                break;
            default:
                throw new IllegalArgumentException("Fragment doesn't exist");
        }
        ft.commitNow();
    }
}
