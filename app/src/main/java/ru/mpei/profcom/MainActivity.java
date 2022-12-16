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
import ru.mpei.profcom.main.model.RequestFragment;
import ru.mpei.profcom.main.ui.EventsFragment;
import ru.mpei.profcom.main.ui.InfoFragment;
import ru.mpei.profcom.main.ui.LearningFragment;
import ru.mpei.profcom.main.ui.MainFragment;
import ru.mpei.profcom.main.ui.NewsFragment;
import ru.mpei.profcom.main.ui.OrgFragment;
import ru.mpei.profcom.main.ui.OrganizationsFragment;
import ru.mpei.profcom.main.ui.ProfileFragment;
import ru.mpei.profcom.main.ui.TaskFragment;

public class MainActivity extends AppCompatActivity implements NavigationController {

    public static final int REGISTER_FRAGMENT = -1;
    public static final int ENTRY_FRAGMENT = 1;
    public static final int MAIN_FRAGMENT = 2;
    public static final int NEWS_FRAGMENT = 3;
    public static final int CATEGORY_FRAGMENT = 4;
    public static final int INFO_FRAGMENT = 5;
    public static final int ORGS_FRAGMENT = 6;
    public static final int ORG_FRAGMENT = 7;
    public static final int PROFILE_FRAGMENT = 8;
    public static final int EVENTS_FRAGMENT = 9;
    public static final int REQUEST_FRAGMENT = 10;
    public static final int LEARNING_FRAGMENT = 11;
    public static final int TASKS_FRAGMENT = 12;

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
            case INFO_FRAGMENT:
                ft.replace(R.id.main_container, new InfoFragment());
                break;
            case ORGS_FRAGMENT:
                ft.replace(R.id.main_container, new OrganizationsFragment());
                break;
            case ORG_FRAGMENT:
                ft.replace(R.id.main_container, new OrgFragment(bundle));
                break;
            case PROFILE_FRAGMENT:
                ft.replace(R.id.main_container, new ProfileFragment());
                break;
            case EVENTS_FRAGMENT:
                ft.replace(R.id.main_container, new EventsFragment());
                break;
            case REQUEST_FRAGMENT:
                ft.replace(R.id.main_container, new RequestFragment());
                break;
            case LEARNING_FRAGMENT:
                ft.replace(R.id.main_container, new LearningFragment());
                break;
            case TASKS_FRAGMENT:
                ft.replace(R.id.main_container, new TaskFragment());
                break;
            default:
                throw new IllegalArgumentException("Fragment doesn't exist");
        }
        ft.commitNow();
    }

    @Override
    public void clear() {
        prefs.edit().clear().apply();
        navigateStack.clear();
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
