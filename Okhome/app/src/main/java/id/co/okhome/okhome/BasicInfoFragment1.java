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

        fragment1View.findViewById(R.id.btn_increment_bedroom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementOfBedroom();
            }
        });

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

        return fragment1View;
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
