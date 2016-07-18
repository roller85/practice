package id.co.okhome.okhome.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.okhome.okhome.view.activity.OrderChangeActivity;
import id.co.okhome.okhome.R;

/**
 * Created by jhkim on 6/7/2016.
 */
public class OrderChangeAdapter extends RecyclerView.Adapter<OrderChangeAdapter.ViewHolder> {
    public ArrayList<HashMap<String, String>> changeList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_change_view, parent, false);
        ViewHolder vh = new OrderChangeAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap hashMap = changeList.get(position);

        String date = hashMap.get(OrderChangeActivity.TAG_DATE).toString();
        String time = hashMap.get(OrderChangeActivity.TAG_TIME).toString();
        String duration = hashMap.get(OrderChangeActivity.TAG_DURATION).toString();
        String price = hashMap.get(OrderChangeActivity.TAG_PAYAMOUNT).toString();

        StringBuilder time_duration = new StringBuilder();
        time_duration.append(time);
        time_duration.append(" - ");
        time_duration.append(duration);
        time_duration.append(" hours");
        String change_time_duration = time_duration.toString();

        holder.orderChangeDate.setText(date);
        holder.orderChangeTimeDuration.setText(change_time_duration);
        holder.orderChangePrice.setText(price);
    }

    @Override
    public int getItemCount() {
        return changeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView orderChangeDate, orderChangeTimeDuration, orderChangePrice;

        public ViewHolder(View itemView) {
            super(itemView);
            orderChangeDate = (TextView) itemView.findViewById(R.id.order_change_date);
            orderChangeTimeDuration = (TextView) itemView.findViewById(R.id.order_change_time_duration);
            orderChangePrice = (TextView) itemView.findViewById(R.id.order_change_price);
        }
    }

    public OrderChangeAdapter(ArrayList<HashMap<String, String>> orderChangeList){
        changeList = orderChangeList;
    }
}
