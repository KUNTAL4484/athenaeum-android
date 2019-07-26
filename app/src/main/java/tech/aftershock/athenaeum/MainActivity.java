package tech.aftershock.athenaeum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tech.aftershock.athenaeum.fragments.Collection;
import tech.aftershock.athenaeum.fragments.Home;
import tech.aftershock.athenaeum.fragments.Result;
import tech.aftershock.athenaeum.fragments.Setting;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mMainContainer;
    private BottomNavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainContainer = findViewById(R.id.main_container);
        mNavigation = findViewById(R.id.main_bottom_navigation);

        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.menu_home: fragment = Home.newInstance(); break;
                    case R.id.menu_collection: fragment = Collection.newInstance(); break;
                    case R.id.menu_result: fragment = Result.newInstance(); break;
                    case R.id.menu_setting: fragment = Setting.newInstance(); break;
                    default: fragment = Home.newInstance();
                }
                loadFragment(fragment);

                return true;
            }
        });
        mNavigation.setSelectedItemId(R.id.menu_home);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }
}
