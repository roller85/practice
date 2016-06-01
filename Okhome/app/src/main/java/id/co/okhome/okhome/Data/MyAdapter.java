package id.co.okhome.okhome.Data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.okhome.okhome.OrderHistoryActivity;
import id.co.okhome.okhome.R;

/**
 * Created by jhkim on 6/1/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<HashMap<String, String>> historyList;

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
    public MyAdapter(ArrayList<HashMap<String, String>> orderHistoryList)
    {
        historyList = orderHistoryList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        HashMap hashMap = historyList.get(position);

        String date = hashMap.get(OrderHistoryActivity.TAG_DATE).toString();
        String duration = hashMap.get(OrderHistoryActivity.TAG_DURATION).toString();
        String price = hashMap.get(OrderHistoryActivity.TAG_PRICE).toString();
        String supplier = hashMap.get(OrderHistoryActivity.TAG_SUPPLIER).toString();
        String rating = hashMap.get(OrderHistoryActivity.TAG_RATING).toString();

        StringBuilder dateDuration = new StringBuilder();
        dateDuration.append(date);
        dateDuration.append(" - ");
        dateDuration.append(duration);
        String orderHistoryDateDuration = dateDuration.toString();

        StringBuilder priceSupplier = new StringBuilder();
        priceSupplier.append(price);
        priceSupplier.append(" / ");
        priceSupplier.append(supplier);
        String orderHistoryPriceSupplier = priceSupplier.toString();

        int ratingBar = Integer.valueOf(rating);

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.orderHistoryDate.setText(orderHistoryDateDuration);
        holder.priceNameOfSupplier.setText(orderHistoryPriceSupplier);
        holder.ratingBar.setNumStars(ratingBar);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return historyList.size();
    }
}
