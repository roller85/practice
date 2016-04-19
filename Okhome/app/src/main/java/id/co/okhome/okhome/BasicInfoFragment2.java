package id.co.okhome.okhome;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.IOException;

import id.co.okhome.okhome.Data.OrderInfo;
import id.co.okhome.okhome.Server.ServerAPI;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class BasicInfoFragment2 extends Fragment {

    public static final String TAG = "BasicInfoFragment2";

    private EditText detailedInfo;
    public String email;

    public BasicInfoFragment2() {
        // Required empty public constructor
    }

    public static BasicInfoFragment2 newInstance() {
        BasicInfoFragment2 fragment = new BasicInfoFragment2();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment2View = inflater.inflate(R.layout.fragment_basic_info_fragment2, container, false);
        detailedInfo = (EditText) fragment2View.findViewById(R.id.detailed_info);
        email = OrderInfo.getInstance().GetUserEmailInfo();

        fragment2View.findViewById(R.id.btn_end_of_basic_info2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attempAddBasicInfo2();
            }
        });
        return fragment2View;
    }

    private void attempAddBasicInfo2() {

        String detail = detailedInfo.getText().toString();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("email", email)
                .appendQueryParameter("details", detail);
        String content = builder.build().getEncodedQuery();

        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(ServerAPI.BASICINFO2ADD)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(getActivity().findViewById(R.id.fragment_basic_info2), "Okhttp : " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Snackbar.make(getActivity().findViewById(R.id.fragment_basic_info2), "Okhttp : " + response.body().string(), Snackbar.LENGTH_LONG).show();

                OrderActivity activity = (OrderActivity) getActivity();

                String homeDetailInfo = detailedInfo.getText().toString();
                OrderInfo.getInstance().AddHomeDetailInfo(homeDetailInfo);

                activity.nextFragment(PackageSelectionFragment.newInstance(), PackageSelectionFragment.TAG);
            }
        });
    }
}
