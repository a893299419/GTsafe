package com.example.apple.gtsafe;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    private HomeFragment myFragment1 = null;
    private LogFragment myFragment2 = null;
    private SettingFragment myFragment3 = null;



    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new HomeFragment();
        myFragment2 = new LogFragment();
        myFragment3 = new SettingFragment();

    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case menuActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case menuActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case menuActivity.PAGE_THREE:
                fragment = myFragment3;
                break;

        }
        return fragment;
    }
}
