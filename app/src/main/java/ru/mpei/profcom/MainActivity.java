package ru.mpei.profcom;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import ru.mpei.profcom.core.NavigationController;
import ru.mpei.profcom.entry.ui.EntryFragment;
import ru.mpei.profcom.entry.ui.RegisterFragment;
import ru.mpei.profcom.main.ui.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationController {

    public static final int REGISTER_FRAGMENT = -1;
    public static final int ENTRY_FRAGMENT = 0;
    public static final int MAIN_FRAGMENT = 1;
    public static final int NEWS_FRAGMENT = 2;

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
            case REGISTER_FRAGMENT:
                ft.replace(R.id.main_container, new RegisterFragment());
                break;
            case MAIN_FRAGMENT:
                ft.replace(R.id.main_container, new MainFragment());
                break;
            case NEWS_FRAGMENT:
            default:
                throw new IllegalArgumentException("Fragment doesn't exist");
        }
        ft.commitNow();
    }
}
