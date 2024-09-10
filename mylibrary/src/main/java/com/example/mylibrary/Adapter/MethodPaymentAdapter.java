package com.example.mylibrary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.R;
import com.example.mylibrary.Listener.ItemClickListener;
import com.example.mylibrary.Models.MethodPayment;
import com.example.mylibrary.viewholder.MethodPaymentViewHolder;

import java.util.ArrayList;

public class MethodPaymentAdapter extends RecyclerView.Adapter<MethodPaymentViewHolder> {
    private Context context;
    private ArrayList<MethodPayment> paymentsList;
    int selectedPosition = -1;
    ItemClickListener itemClickListener;

    public MethodPaymentAdapter(Context context, ArrayList<MethodPayment> paymentsList, ItemClickListener itemClickListener) {
        this.context = context;
        this.paymentsList = paymentsList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MethodPaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_method_payment, parent, false);
        return new MethodPaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MethodPaymentViewHolder holder, int position) {
        MethodPayment methodPayment = paymentsList.get(position);
        holder.setName(methodPayment.getName());
        holder.setImage(methodPayment.getImage());
        holder.radioButton.setChecked(position == selectedPosition);
        if (holder.radioButton.isChecked()) {
            holder.linearBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_main_border));
        } else {
            holder.linearBtn.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_black_border));
        }

        // Set OnClickListener on LinearLayout
        holder.linearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update selected position
                selectedPosition = holder.getAdapterPosition();
                notifyDataSetChanged();  // Notify adapter to refresh the list

                // Trigger the item click listener if needed
                itemClickListener.onClick(methodPayment.getName());
            }
        });

//        Set OnClickListener on RadioButton
        holder.radioButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            selectedPosition = holder.getAdapterPosition();
                            itemClickListener.onClick(methodPayment.getName());
                        }
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return paymentsList == null ? 0 : paymentsList.size();
    }
}
