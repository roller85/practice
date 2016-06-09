package id.co.okhome.okhome.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.co.okhome.okhome.R;
import id.co.okhome.okhome.model.OrderHistoryModel;
import id.co.okhome.okhome.view_holder.OrderHistoryViewHolder;

/**
 * Created by jhkim on 6/1/2016.
 */
public class OrderHistoryAdapter extends RecyclerView.Adapter {
    public List<OrderHistoryModel> historyList = null;
    private int size = 0;
    private LayoutInflater lf = null;

    public OrderHistoryAdapter(Context context) {
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<OrderHistoryModel> orderHistoryModels) {
        this.historyList = orderHistoryModels;
        this.size = orderHistoryModels.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = lf.inflate(R.layout.order_history_view, null);
        return new OrderHistoryViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderHistoryViewHolder orderHistoryViewHolder = (OrderHistoryViewHolder) holder;
        OrderHistoryModel model = historyList.get(position);

        String date = model.date;
        String duration = model.duration;
        String price = model.price;
        String supplier = model.supplier;
        String rating = model.performance;

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
        orderHistoryViewHolder.orderHistoryDate.setText(orderHistoryDateDuration);
        orderHistoryViewHolder.priceNameOfSupplier.setText(orderHistoryPriceSupplier);
        orderHistoryViewHolder.ratingBar.setRating(ratingBar);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return size;
    }

}
