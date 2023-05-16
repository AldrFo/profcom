package ru.mpei.profcom;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Stack;

import ru.mpei.profcom.core.NavigationController;
import ru.mpei.profcom.entry.ui.CategoryChooseFragment;
import ru.mpei.profcom.entry.ui.EntryFragment;
import ru.mpei.profcom.entry.ui.RegisterFragment;
import ru.mpei.profcom.main.ui.GratitudesFragment;
import ru.mpei.profcom.main.ui.LearnFragment;
import ru.mpei.profcom.main.ui.RequestFragment;
import ru.mpei.profcom.main.ui.EventsFragment;
import ru.mpei.profcom.main.ui.InfoFragment;
import ru.mpei.profcom.main.ui.LearningFragment;
import ru.mpei.profcom.main.ui.NewsFragment;
import ru.mpei.profcom.main.ui.OrgFragment;
import ru.mpei.profcom.main.ui.OrganizationsFragment;
import ru.mpei.profcom.main.ui.ProfileFragment;
import ru.mpei.profcom.main.ui.TaskFragment;

public class MainActivity extends AppCompatActivity implements NavigationController, NavigationBarView.OnItemSelectedListener {

    public static final int REGISTER_FRAGMENT = -1;
    public static final int ENTRY_FRAGMENT = 1;
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
    public static final int LEARN_FRAGMENT = 13;
    public static final int GRATITUDES_FRAGMENT = 14;

    public static SharedPreferences prefs;

    private final Stack<Integer> navigateStack = new Stack<>();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_bottom_navigation);

        prefs = getPreferences(MODE_PRIVATE);
        if(prefs.getInt("id", -1) == -1) {
            navigate(ENTRY_FRAGMENT, null);
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            bottomNavigationView.setVisibility(View.VISIBLE);
            navigate(PROFILE_FRAGMENT, null);
        }
        bottomNavigationView.setOnItemSelectedListener(this);
    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                navigate(PROFILE_FRAGMENT, null);
                break;
            case R.id.news:
                navigate(NEWS_FRAGMENT, null);
                break;
            case R.id.orgs:
                navigate(ORGS_FRAGMENT, null);
                break;
            case R.id.info:
                navigate(INFO_FRAGMENT, null);
                break;
        }
        return true;
    }

    @Override
    public void navigate(int fragmentId, Bundle bundle) {
        if (fragmentId == ENTRY_FRAGMENT || fragmentId == REGISTER_FRAGMENT || fragmentId == CATEGORY_FRAGMENT) {
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        navigateStack.push(fragmentId);
        switch (fragmentId){
            case ENTRY_FRAGMENT:
                ft.replace(R.id.main_container, new EntryFragment());
                break;
            case REGISTER_FRAGMENT:
                ft.replace(R.id.main_container, new RegisterFragment());
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
            case LEARN_FRAGMENT:
                ft.replace(R.id.main_container, new LearnFragment(bundle));
                break;
            case GRATITUDES_FRAGMENT:
                ft.replace(R.id.main_container, new GratitudesFragment());
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
        if(id != 0 && id != CATEGORY_FRAGMENT && id != REGISTER_FRAGMENT && id != ENTRY_FRAGMENT){
            navigate(id, null);
        }
    }
}
