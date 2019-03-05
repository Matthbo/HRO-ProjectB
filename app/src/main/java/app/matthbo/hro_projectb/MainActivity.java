package app.matthbo.hro_projectb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_openDays:
                    toolbar.setTitle("Open Days");
                    fragment = new OpenDaysFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_signup:
                    toolbar.setTitle("Sign up");
                    fragment = new SignupFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    toolbar.setTitle("Notifications");
                    fragment = new NotificationsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_route:
                    toolbar.setTitle("Navigation");
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

        toolbar.setTitle("Open Days");
        loadFragment(new OpenDaysFragment());
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
