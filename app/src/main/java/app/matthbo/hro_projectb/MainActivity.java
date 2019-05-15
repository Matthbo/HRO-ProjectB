package app.matthbo.hro_projectb;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragmentOnClickable {

    private ActionBar toolbar;
    private Fragment fragment;
    private FragmentOnClickable clickableFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_openDays:
                    toolbar.setTitle(getResources().getString(R.string.title_openDays));
                    fragment = new OpenDaysFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_question:
                    toolbar.setTitle(getResources().getString(R.string.title_question));
                    fragment = new QuestionsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_signup:
                    toolbar.setTitle(getResources().getString(R.string.title_signup));
                    fragment = new SignupFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    toolbar.setTitle(getResources().getString(R.string.title_notifications));
                    fragment = new NotificationsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_route:
                    toolbar.setTitle(getResources().getString(R.string.title_route));
                    fragment = new NavigationFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState != null) {
            //Restore the fragment's instance
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragement");
            loadFragment(fragment);
        } else {
            toolbar.setTitle("Open Days");
            fragment = new OpenDaysFragment();
            loadFragment(fragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "currentFragement", fragment);
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        if(fragment instanceof FragmentOnClickable) {
            clickableFragment = (FragmentOnClickable)fragment;
        } else {
            clickableFragment = null;
        }
    }

    public void fragmentOnClick(View v){
        if(clickableFragment != null) clickableFragment.fragmentOnClick(v);
    }

}
