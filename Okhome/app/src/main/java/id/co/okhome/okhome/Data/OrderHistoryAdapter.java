package id.co.okhome.okhome.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.model.OrderHistoryModel;

/**
 * Created by jhkim on 6/1/2016.
 */
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    public ArrayList<HashMap<String, String>> historyList;

    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder




    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new OrderHistoryAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(OrderHistoryAdapter.ViewHolder holder, int position) {
        HashMap hashMap = historyList.get(position);

        String date = hashMap.get(OrderHistoryModel.TAG_DATE).toString();
        String duration = hashMap.get(OrderHistoryModel.TAG_DURATION).toString();
        String price = hashMap.get(OrderHistoryModel.TAG_PRICE).toString();
        String supplier = hashMap.get(OrderHistoryModel.TAG_SUPPLIER).toString();
        String rating = hashMap.get(OrderHistoryModel.TAG_RATING).toString();

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
        holder.ratingBar.setRating(ratingBar);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return historyList.size();
    }

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
    public OrderHistoryAdapter(ArrayList<HashMap<String, String>> orderHistoryList)
    {
        historyList = orderHistoryList;
    }
}
