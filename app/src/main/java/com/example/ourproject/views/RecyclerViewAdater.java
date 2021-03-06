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
        Parcel p = parcels.get(position);
        holder.firstName.setText(p.getAddressee().getFirstName());
        holder.lastName.setText(p.getAddressee().getSurname());
        holder.phone.setText(p.getAddressee().getPhoneNumber() + "");
        holder.parcelsize.setText(p.getParcelSize().name());
        holder.parcelweight.setText(p.getParcelWeight().name());
        holder.parcelfrigile.setText(p.getParcelFragile().name());
        holder.addressauto.setText(p.getParcelAddressAuto());



    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }

    public class holderCard extends RecyclerView.ViewHolder {

        TextView firstName;
        TextView lastName;
        TextView phone;
        TextView parcelsize;
        TextView parcelfrigile;
        TextView parcelweight;
        TextView addressauto;


        public holderCard(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name_card_view);
            lastName = itemView.findViewById(R.id.last_name_card_view);
            phone = itemView.findViewById(R.id.phone);
            parcelsize = itemView.findViewById(R.id.parcelSize);
            parcelfrigile = itemView.findViewById(R.id.parcelFrigile);
            parcelweight = itemView.findViewById(R.id.parcelweight);
            addressauto = itemView.findViewById(R.id.adressauto);

        }
    }
}
