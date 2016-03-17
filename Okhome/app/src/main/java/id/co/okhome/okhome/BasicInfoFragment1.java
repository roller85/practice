package id.co.okhome.okhome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class BasicInfoFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String Name = "Basic Information";

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

        fragment1View.findViewById(R.id.btn_apartment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_type = "";
                home_type = "APT";
            }
        });

        fragment1View.findViewById(R.id.btn_house).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_type = "";
                home_type = "House";
            }
        });

        fragment1View.findViewById(R.id.btn_boarding_house).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_type = "";
                home_type = "Boarding House";
            }
        });

        fragment1View.findViewById(R.id.btn_size_50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_size = "";
                home_size = "<50";
            }
        });

        fragment1View.findViewById(R.id.btn_size_50_100).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_size = "";
                home_size = "50 - 100";
            }
        });

        fragment1View.findViewById(R.id.btn_size_100_150).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_size = "";
                home_size = "100 - 150";
            }
        });

        fragment1View.findViewById(R.id.btn_size_150).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home_size = "";
                home_size = ">150";
            }
        });

        fragment1View.findViewById(R.id.btn_home_existence_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeowner_existence = true;
            }
        });

        fragment1View.findViewById(R.id.btn_home_existence_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeowner_existence = false;
            }
        });

        fragment1View.findViewById(R.id.btn_cat_existence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pet_cat_existence = true;
            }
        });

        fragment1View.findViewById(R.id.btn_dog_existence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pet_dog_existence = true;
            }
        });

        fragment1View.findViewById(R.id.btn_other_existence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pet_other_existence = true;
            }
        });

        fragment1View.findViewById(R.id.btn_no_existence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pet_nonexist = true;
            }
        });

        fragment1View.findViewById(R.id.btn_end_of_basic_info1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempAddBasicInfo1();
            }
        });

        return fragment1View;
    }

    private void attempAddBasicInfo1() {

    }

    public void incrementOfBedroom() {
        number_of_bedroom = number_of_bedroom + 1;
        displayNumberOfBedroom(number_of_bedroom, number_of_bedrooms);
    }

    public void decrementOfBedroom() {
        number_of_bedroom = number_of_bedroom - 1;
        displayNumberOfBedroom(number_of_bedroom, number_of_bedrooms);
    }

    public void incrementOfBathroom() {
        number_of_bathroom = number_of_bathroom + 1;
        displayNumberOfBathroom(number_of_bathroom, number_of_bathrooms);
    }

    public void decrementOfBathroom() {
        number_of_bathroom = number_of_bathroom - 1;
        displayNumberOfBathroom(number_of_bathroom, number_of_bathrooms);
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
