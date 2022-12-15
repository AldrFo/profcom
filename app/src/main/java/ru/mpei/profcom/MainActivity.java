package ru.mpei.profcom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Stack;

import ru.mpei.profcom.core.NavigationController;
import ru.mpei.profcom.entry.ui.CategoryChooseFragment;
import ru.mpei.profcom.entry.ui.EntryFragment;
import ru.mpei.profcom.entry.ui.RegisterFragment;
import ru.mpei.profcom.main.ui.MainFragment;
import ru.mpei.profcom.main.ui.NewsFragment;

public class MainActivity extends AppCompatActivity implements NavigationController {

    public static final int REGISTER_FRAGMENT = -1;
    public static final int ENTRY_FRAGMENT = 1;
    public static final int MAIN_FRAGMENT = 2;
    public static final int NEWS_FRAGMENT = 3;
    public static final int CATEGORY_FRAGMENT = 4;

    public static SharedPreferences prefs;

    private final Stack<Integer> navigateStack = new Stack<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);
        if(prefs.getInt("id", -1) == -1)
            navigate(ENTRY_FRAGMENT, null);
        else
            navigate(MAIN_FRAGMENT, null);
    }

    @Override
    public void navigate(int fragmentId, Bundle bundle) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        navigateStack.push(fragmentId);
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
                ft.replace(R.id.main_container, new NewsFragment());
                break;
            case CATEGORY_FRAGMENT:
                ft.replace(R.id.main_container, new CategoryChooseFragment(bundle));
                break;
            default:
                throw new IllegalArgumentException("Fragment doesn't exist");
        }
        ft.commitNow();
    }

    @Override
    public void onBackPressed() {
        if(navigateStack.isEmpty())
            return;
        navigateStack.pop();
        if(navigateStack.isEmpty())
            return;
        Integer id = navigateStack.pop();
        Log.d("asas", String.valueOf(id));
        if(id != 0 && id != CATEGORY_FRAGMENT && id != REGISTER_FRAGMENT && id != ENTRY_FRAGMENT){
            navigate(id, null);
        }
    }
}
