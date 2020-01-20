package com.example.ourproject.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourproject.R;
import com.example.ourproject.data.model.Parcel;

import java.util.List;

public class RecyclerViewAdater extends RecyclerView.Adapter<RecyclerViewAdater.holderCard> {
    List<Parcel> parcels;

    public RecyclerViewAdater(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    @NonNull
    @Override
    public holderCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        return new holderCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderCard holder, int position) {
        Parcel p=parcels.get(position);
        holder.firstName.setText(p.getAddressee().getFirstName());

    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }

    public class holderCard extends RecyclerView.ViewHolder{

        TextView firstName;

        public holderCard(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name_card_view);
        }
    }
}
