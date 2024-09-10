package com.example.mylibrary.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.R;


public class ProductViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public TextView title;
    public TextView description;
    public ImageView image;

    public ProductViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        title = (TextView) mView.findViewById(R.id.tv_title);
        description = (TextView) mView.findViewById(R.id.tv_description);
        image = (ImageView) mView.findViewById(R.id.iv_product);
    }

    public void setTitle(String titleName) {
        this.title.setText(titleName);
    }
    public void setDescription(String desc) {
        this.description.setText(desc);
    }
}
