package com.example.exam.models;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam.R;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder>{
    private List<Tour> tourList;
    private List<Tour> tourListBackup;
    private Context context;
    private OnItemClickListener listener;


    public TourAdapter(Context context) {
        this.context = context;
        this.tourList = new ArrayList<>();
        this.tourListBackup = new ArrayList<>();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<Tour> getTourListBackup() {
        return tourListBackup;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        if (tour == null)
            return;
        holder.imgTransport.setImageResource(tour.getImgTransport());
        holder.tvName.setText(tour.getName());
        holder.tvTime.setText(tour.getTime());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning");
                builder.setMessage("Bạn có chắc chắn muốn xóa" + tour.getName() + "không?");
                builder.setIcon(R.drawable.th);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tourListBackup.remove(position);
                        tourList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tourList != null)
            return tourList.size();
        return 0;
    }

    public Tour getItem(int position){
        return tourList.get(position);
    }

    public void filterList (List<Tour> filter){
        tourList = filter;
        notifyDataSetChanged();
    }
    public void add(Tour tour){
        tourListBackup.add(tour);
        tourList.add(tour);
        notifyDataSetChanged();
    }

    public void update(int position, Tour tour){
        tourListBackup.set(position, tour);
        tourList.set(position, tour);
        notifyDataSetChanged();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder{
        ImageView imgTransport;
        TextView tvName, tvTime;
        Button btnDelete;
        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTransport = itemView.findViewById(R.id.imgTransport);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
