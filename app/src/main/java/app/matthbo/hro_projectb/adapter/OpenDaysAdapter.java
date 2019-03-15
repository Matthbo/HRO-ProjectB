package app.matthbo.hro_projectb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.matthbo.hro_projectb.R;

public class OpenDaysAdapter extends RecyclerView.Adapter<OpenDaysAdapter.OpenDaysViewHolder> {
    private String[] dataset;

    public OpenDaysAdapter(String[] openDaysDataset) {
        this.dataset = openDaysDataset;
    }

    @NonNull
    @Override
    public OpenDaysAdapter.OpenDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.opendays_view_item, parent, false);

        return new OpenDaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OpenDaysViewHolder holder, int position) {
        holder.textView.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }

    public static class OpenDaysViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public OpenDaysViewHolder(@NonNull View view) {
            super(view);
            this.textView = view.findViewById(R.id.textView_openDays_view_item);
        }
    }
}
