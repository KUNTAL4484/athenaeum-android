package tech.aftershock.athenaeum.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tech.aftershock.athenaeum.fragments.Exams;
import tech.aftershock.athenaeum.fragments.Library;
import tech.aftershock.athenaeum.fragments.PrevQuestions;
import tech.aftershock.athenaeum.fragments.Videos;

public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        String title;
        switch (position) {
            case 0: title = "Library"; break;
            case 1: title = "Videos"; break;
            case 2: title = "Exam"; break;
            case 3: title = "Questions"; break;
            default: title = "Library";
        }

        return title;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0: fragment = Library.newInstance(); break;
            case 1: fragment = Videos.newInstance(); break;
            case 2: fragment = Exams.newInstance(); break;
            case 3: fragment = PrevQuestions.newInstance(); break;
            default: fragment = Library.newInstance();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
