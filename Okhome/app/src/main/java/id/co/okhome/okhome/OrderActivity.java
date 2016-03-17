package id.co.okhome.okhome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OrderActivity extends AppCompatActivity {

    //public final static String EXTRA_MESSAGE3 = "id.co.okhome.okhome.MESSAGE3";
    private SectionsPagerAdapter mSectionPagerAdapter;
    private ViewPager mViewPager;
    private Fragment[] arrFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        arrFragments = new Fragment[3];
        arrFragments[0] = BasicInfoFragment1.newInstance();
        arrFragments[1] = BasicInfoFragment1.newInstance();
        arrFragments[2] = BasicInfoFragment1.newInstance();
        //arrFragments[2] = PackageSelectionFragment.newInstance();
        //arrFragments[3] = TimeSelectionFragment1.newInstance();
        //arrFragments[4] = TimeSelectionFragment2.newInstance();
        //arrFragments[5] = AddressSelectionFragment.newInstance();
        //arrFragments[6] = SummaryAndPointFragment.newInstance();

        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionPagerAdapter);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return arrFragments[position];
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return arrFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return BasicInfoFragment1.Name;
                case 1:
                    return BasicInfoFragment1.Name;
                case 2:
                    return BasicInfoFragment1.Name;
            }
            return null;
        }
    }

}
