package id.co.okhome.okhome.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import id.co.okhome.okhome.R;

/**
 * Created by jhkim on 6/9/2016.
 */
public class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView orderHistoryDate, priceNameOfSupplier;
    public RatingBar ratingBar;
    public OrderHistoryViewHolder(View itemView) {
        super(itemView);
        orderHistoryDate = (TextView) itemView.findViewById(R.id.order_history_date);
        priceNameOfSupplier = (TextView) itemView.findViewById(R.id.price_name_of_supplier);
        ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
    }
}
