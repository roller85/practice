package id.co.okhome.okhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity{

    public static final int REQ_TOPUP = 1003;
//    public static final String REQ_TOPUP = "OrderConfirmFragment_TopUp";
    public static final int REQ_TOPUP_RETURN = 1007;
    /*
    private SectionsPagerAdapter mSectionPagerAdapter;
    private ViewPager mViewPager;
    private Fragment[] arrFragments;
    */

//    private UserOrder userOrder;
/*
    public UserOrder GetUserOrder() {
        return userOrder;
    }
*/

    /*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("tempData", userOrder);
        super.onSaveInstanceState(outState);
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        userOrder = new UserOrder();
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

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                    return;
            }

            PackageSelectionFragment fragment = new PackageSelectionFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

            /*
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            getSupportFragmentManager().beginTransaction().add(datePickerFragment, "datePicker");
            */

            /*
            TimePickerFragment timePickerFragment = new TimePickerFragment();
            getSupportFragmentManager().beginTransaction().add(timePickerFragment, "time_picker_fragment");
            */
        }



        /*
        arrFragments = new Fragment[3];
        arrFragments[0] = BasicInfoFragment1.newInstance();
        arrFragments[1] = BasicInfoFragment2.newInstance();
        arrFragments[2] = BasicInfoFragment1.newInstance();
        //arrFragments[2] = PackageSelectionFragment.newInstance();
        //arrFragments[3] = TimeSelectionFragment1.newInstance();
        //arrFragments[4] = TimeSelectionFragment2.newInstance();
        //arrFragments[5] = AddressSelectionFragment.newInstance();
        //arrFragments[6] = SummaryAndPointFragment.newInstance();

        mSectionPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionPagerAdapter);
        */
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_TOPUP_RETURN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Order Complete", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Order failed, order again", Toast.LENGTH_SHORT).show();
                nextFragment(BasicInfoFragment1.newInstance(),BasicInfoFragment1.TAG);
            }
        }
    }

    public void nextFragment(Fragment fr, String mtag) {
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fr;

        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.fragment_container,fragment, mtag);
        ft.commit();
    }
/*
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
                    return BasicInfoFragment2.Name;
                case 2:
                    return BasicInfoFragment1.Name;
            }
            return null;
        }
    }
    */
}
