package id.co.okhome.okhome;



import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.okhome_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        myDataset = new ArrayList<>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        for (int i = 0; i < 30; i++) {
            MyData myData = new MyData(
                    "a", "b",
                    //이 부분에 이제 database에서 불러와서 넣어야하는데 어떻게 하는지 모르겠음
            );
            myDataset.add(i, myData);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
private ArrayList<MyData> mDataset;

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public static class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView orderHistoryDate, priceNameOfSupplier;
    public RatingBar ratingBar;

    public ViewHolder(View itemView) {
        super(itemView);
        orderHistoryDate = (TextView) itemView.findViewById(R.id.order_history_date);
        priceNameOfSupplier = (TextView) itemView.findViewById(R.id.price_name_of_supplier);
        ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
    }
}

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<MyData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyData data = mDataset.get(position);
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.orderHistoryDate.setText(data.orderHistoryData);
        holder.priceNameOfSupplier.setText(data.priceNameSupplier);
        holder.ratingBar.setNumStars(data.ratingBar.getNumStars());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

class MyData{
    public String orderHistoryData;
    public String priceNameSupplier;
    public RatingBar ratingBar;
    public MyData(String orderHistoryData, String priceNameSupplier, RatingBar ratingBar){
        this.orderHistoryData = orderHistoryData;
        this.priceNameSupplier = priceNameSupplier;
        this.ratingBar = ratingBar;
    }
}

