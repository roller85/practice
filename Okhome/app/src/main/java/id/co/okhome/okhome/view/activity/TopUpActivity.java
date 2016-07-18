package id.co.okhome.okhome.view.activity;

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

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.view.fragment.SignUpRecommendFragment;
import id.co.okhome.okhome.view.fragment.topup_fragment.TopUpInfoFragment;
import id.co.okhome.okhome.view.fragment.topup_fragment.TopUpPackageFragment;

public class TopUpActivity extends AppCompatActivity {

    public static final int REQ_SIGNUP = 1004;
    public static final int REQ_SIGNUP_RETURN = 1008;
    public static final int REQ_LOGIN = 1009;
    public static final int REQ_LOGIN_RETURN = 1010;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            int requestCode = getIntent().getIntExtra("requestCode", -1);

            if (requestCode == LoginActivity.REQ_TOPUP) {
                nextFragment(TopUpInfoFragment.newInstance(), TopUpInfoFragment.TAG);
            } else {
                TopUpPackageFragment fragment = new TopUpPackageFragment();
                fragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
            }


        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_SIGNUP_RETURN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Sing Up Success", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TopUpActivity.this, LoginActivity.class);
                intent.putExtra("requestCode", REQ_LOGIN);
                startActivity(intent);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                nextFragment(SignUpRecommendFragment.newInstance(), SignUpRecommendFragment.TAG);
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

}
