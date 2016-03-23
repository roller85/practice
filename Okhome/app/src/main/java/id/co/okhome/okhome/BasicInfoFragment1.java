package id.co.okhome.okhome;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import id.co.okhome.okhome.Server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class BasicInfoFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private int number_of_bedroom = 2;
    private int number_of_bathroom = 2;
    private String home_type = "";
    private String home_size = "";
    private boolean homeowner_existence;
    private boolean pet_cat_existence;
    private boolean pet_dog_existence;
    private boolean pet_other_existence;
    private boolean pet_nonexist;
    public TextView number_of_bedrooms;
    public TextView number_of_bathrooms;
    public String email;
    RadioGroup homeType;
    RadioGroup homeSize;
    RadioGroup homeExistence;

    public BasicInfoFragment1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BasicInfoFragment1 newInstance() {
        BasicInfoFragment1 fragment = new BasicInfoFragment1();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment1View = inflater.inflate(R.layout.fragment_basic_info_fragment1, container, false);
        number_of_bedrooms = (TextView) fragment1View.findViewById(R.id.number_of_bedrooms);
        number_of_bathrooms = (TextView) fragment1View.findViewById(R.id.number_of_bathrooms);
        email = getActivity().getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE2);

        //increasing number of bedroom
        fragment1View.findViewById(R.id.btn_increment_bedroom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOfBedroom();
            }
        });

        //decreasing number of bedroom
        fragment1View.findViewById(R.id.btn_decrement_bedroom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOfBedroom();
            }
        });

        fragment1View.findViewById(R.id.btn_increment_bathroom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOfBathroom();
            }
        });

        fragment1View.findViewById(R.id.btn_decrement_bathroom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementOfBathroom();
            }
        });

        homeType = (RadioGroup) fragment1View.findViewById(R.id.homeType);
        homeType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_apartment:
                        home_type = "APT";
                        break;
                    case R.id.btn_house:
                        home_type = "House";
                        break;
                    case R.id.btn_boarding_house:
                        home_type = "Boarding House";
                        break;
                }
            }
        });

        /*
        fragment1View.findViewById(R.id.btn_apartment).setOnClickListener(new View.OnClickListener() {
            private boolean pressed = false;
            @Override
            public void onClick(View v) {
                pressed = !pressed;
                v.setSelected(pressed);
                if(pressed = true) {
                    home_type = "APT";
                }
                else {
                    home_type = ""
                }
                home_type = "";
                home_type = "APT";
            }
        });

        fragment1View.findViewById(R.id.btn_house).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                home_type = "";
                home_type = "House";
            }
        });

        fragment1View.findViewById(R.id.btn_boarding_house).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                home_type = "";
                home_type = "Boarding House";
            }
        });
        */

        homeSize = (RadioGroup) fragment1View.findViewById(R.id.homeSize);
        homeSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_size_50:
                        home_size = "<50";
                        break;
                    case R.id.btn_size_50_100:
                        home_size = "50-100";
                        break;
                    case R.id.btn_size_100_150:
                        home_size = "100-150";
                        break;
                    case R.id.btn_size_150:
                        home_size = ">150";
                        break;
                }
            }
        });

        /*
        fragment1View.findViewById(R.id.btn_size_50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                home_size = "";
                home_size = "<50";
            }
        });

        fragment1View.findViewById(R.id.btn_size_50_100).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                home_size = "";
                home_size = "50 - 100";
            }
        });

        fragment1View.findViewById(R.id.btn_size_100_150).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                home_size = "";
                home_size = "100 - 150";
            }
        });

        fragment1View.findViewById(R.id.btn_size_150).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                home_size = "";
                home_size = ">150";
            }
        });
        */

        homeExistence = (RadioGroup) fragment1View.findViewById(R.id.homeExistence);
        homeExistence.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_home_existence_yes:
                        homeowner_existence = true;
                        break;
                    case R.id.btn_home_existence_no:
                        homeowner_existence = false;
                        break;
                }
            }
        });

        /*
        fragment1View.findViewById(R.id.btn_home_existence_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                homeowner_existence = true;
            }
        });

        fragment1View.findViewById(R.id.btn_home_existence_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(true);
                homeowner_existence = false;
            }
        });
        */

        fragment1View.findViewById(R.id.btn_cat_existence).setOnClickListener(new View.OnClickListener() {
            private boolean pressed = false;
            @Override
            public void onClick(View v) {
                pressed = !pressed;
                v.setSelected(pressed);
                pet_cat_existence = pressed;
            }
        });

        fragment1View.findViewById(R.id.btn_dog_existence).setOnClickListener(new View.OnClickListener() {
            private boolean pressed = false;
            @Override
            public void onClick(View v) {
                pressed = !pressed;
                v.setSelected(pressed);
                pet_dog_existence = pressed;
            }
        });

        fragment1View.findViewById(R.id.btn_other_existence).setOnClickListener(new View.OnClickListener() {
            private boolean pressed = false;
            @Override
            public void onClick(View v) {
                pressed = !pressed;
                v.setSelected(pressed);
                pet_other_existence = pressed;
            }
        });

        fragment1View.findViewById(R.id.btn_no_existence).setOnClickListener(new View.OnClickListener() {
            private boolean pressed = false;
            @Override
            public void onClick(View v) {
                pressed = !pressed;
                v.setSelected(pressed);
                pet_nonexist = pressed;
            }
        });

        fragment1View.findViewById(R.id.btn_end_of_basic_info1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (home_type != null & home_size != null) {
                    attempAddBasicInfo1();
                }
                else {
                    Toast.makeText(getActivity(), "Fill Out All The Information", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragment1View;
    }

    private void attempAddBasicInfo1() {

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("bedroom", String.valueOf(number_of_bedroom))
                .appendQueryParameter("bathroom", String.valueOf(number_of_bathroom))
                .appendQueryParameter("homeType", home_type)
                .appendQueryParameter("homeSize", home_size)
                .appendQueryParameter("homeownerExistence", String.valueOf(homeowner_existence))
                .appendQueryParameter("catExistence", String.valueOf(pet_cat_existence))
                .appendQueryParameter("dogExistence", String.valueOf(pet_dog_existence))
                .appendQueryParameter("otherExistence", String.valueOf(pet_other_existence))
                .appendQueryParameter("nonexist", String.valueOf(pet_nonexist));
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.BASICINFO1ADD)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getActivity().findViewById(R.id.fragment_basic_info1), "Okhttp : " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(getActivity().findViewById(R.id.fragment_basic_info1), "Okhttp : " + response.body().string(), Snackbar.LENGTH_LONG).show();
            OrderActivity activity = (OrderActivity) getActivity();
                activity.nextFragment(BasicInfoFragment2.newInstance(), "basic_info_fragment2");
            }

        });
    }

    public void incrementOfBedroom() {
        number_of_bedroom = number_of_bedroom + 1;
        displayNumberOfBedroom(number_of_bedroom, number_of_bedrooms);
    }

    public void decrementOfBedroom() {
        if(number_of_bedroom > 0) {
            number_of_bedroom = number_of_bedroom - 1;
            displayNumberOfBedroom(number_of_bedroom, number_of_bedrooms);
        }
        else {
            Toast.makeText(getActivity(), "Number Of Bedroom Cannot be Minus", Toast.LENGTH_SHORT).show();
            displayNumberOfBedroom(number_of_bedroom, number_of_bedrooms);
        }

    }

    public void incrementOfBathroom() {
        number_of_bathroom = number_of_bathroom + 1;
        displayNumberOfBathroom(number_of_bathroom, number_of_bathrooms);
    }

    public void decrementOfBathroom() {
        if(number_of_bathroom > 0) {
            number_of_bathroom = number_of_bathroom - 1;
            displayNumberOfBathroom(number_of_bathroom, number_of_bathrooms);
        }
        else {
            Toast.makeText(getActivity(), "Number Of Bathroom Cannot be Minus", Toast.LENGTH_SHORT).show();
            displayNumberOfBathroom(number_of_bathroom, number_of_bathrooms);
        }
    }

    private void displayNumberOfBedroom(int number, TextView tv) {
        this.number_of_bedrooms = tv;
        number_of_bedrooms.setText(String.valueOf(number));
    }

    private void displayNumberOfBathroom(int number, TextView tv) {
        this.number_of_bathrooms = tv;
        number_of_bathrooms.setText(String.valueOf(number));
    }
}
