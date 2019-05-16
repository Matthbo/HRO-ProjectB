package app.matthbo.hro_projectb;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentOnClickable {

    private ActionBar toolbar;
    private BottomNavigationView navigation;
    private Fragment currentFragment;
    private FragmentOnClickable clickableFragment;

    private Fragment openDaysFragment;
    private Fragment questionsFragment;
    private Fragment signupFragment;
    private Fragment notificationsFragment;
    private Fragment navigationFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(!item.isChecked()) {
                switch (item.getItemId()) {
                    case R.id.navigation_openDays:
                        if(openDaysFragment == null) openDaysFragment = new OpenDaysFragment();
                        loadFragment(openDaysFragment, true);
                        return true;
                    case R.id.navigation_question:
                        if(questionsFragment == null) questionsFragment = new QuestionsFragment();
                        loadFragment(questionsFragment, true);
                        return true;
                    case R.id.navigation_signup:
                        if(signupFragment == null) signupFragment = new SignupFragment();
                        loadFragment(signupFragment, true);
                        return true;
                    case R.id.navigation_notifications:
                        if(notificationsFragment == null) notificationsFragment = new NotificationsFragment();
                        loadFragment(notificationsFragment, true);
                        return true;
                    case R.id.navigation_route:
                        if(navigationFragment == null) navigationFragment = new NavigationFragment();
                        loadFragment(navigationFragment, true);
                        return true;
                }
            }
            return false;
        }
    };

    private FragmentManager.OnBackStackChangedListener mOnBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            Fragment newCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
            if(newCurrentFragment != null){
                setCurrentFragment(newCurrentFragment);
                Menu menu = navigation.getMenu();

                if(currentFragment instanceof OpenDaysFragment){
                    toolbar.setTitle(getResources().getString(R.string.title_openDays));
                    menu.findItem(R.id.navigation_openDays).setChecked(true);
                }else if (currentFragment instanceof QuestionsFragment){
                    toolbar.setTitle(getResources().getString(R.string.title_question));
                    menu.findItem(R.id.navigation_question).setChecked(true);
                }else if (currentFragment instanceof SignupFragment){
                    toolbar.setTitle(getResources().getString(R.string.title_signup));
                    menu.findItem(R.id.navigation_signup).setChecked(true);
                }else if (currentFragment instanceof NotificationsFragment){
                    toolbar.setTitle(getResources().getString(R.string.title_notifications));
                    menu.findItem(R.id.navigation_notifications).setChecked(true);
                }else if (currentFragment instanceof NavigationFragment){
                    toolbar.setTitle(getResources().getString(R.string.title_route));
                    menu.findItem(R.id.navigation_route).setChecked(true);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().addOnBackStackChangedListener(mOnBackStackChangedListener);

        if (savedInstanceState != null) {
            //Restore the currentFragment's instance
            Fragment savedFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
            if(savedFragment != null) loadFragment(savedFragment, false);
        } else {
            if(openDaysFragment == null) openDaysFragment = new OpenDaysFragment();
            loadFragment(openDaysFragment, false);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the currentFragment's instance
        getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack){
        String fragmentTag =  fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStackImmediate(fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        if(addToBackStack)transaction.addToBackStack(fragmentTag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        if(!addToBackStack) setCurrentFragment(fragment);
    }

    public void fragmentOnClick(View v){
        if(clickableFragment != null) clickableFragment.fragmentOnClick(v);
    }

    private void setCurrentFragment(Fragment fragment){
        currentFragment = fragment;
        clickableFragment = currentFragment instanceof FragmentOnClickable ? (FragmentOnClickable)currentFragment : null;
    }

}
