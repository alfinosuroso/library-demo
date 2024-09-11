package com.alfinosuroso.mylibrary.Api.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfinosuroso.mylibrary.viewholder.ProductViewHolder;
import com.alfinosuroso.mylibrary.Api.ProductResponse.ProductData;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<ProductData> dataList;
    private Context context;

    public ProductAdapter(Context ctx, List<ProductData> dataList) {
        this.context = ctx;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(io.github.alfinosuroso.R.layout.product_activity_component_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductData productData = dataList.get(position);

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(productData.getImage())
                .placeholder((io.github.alfinosuroso.R.drawable.ic_launcher_background))
                .error(io.github.alfinosuroso.R.drawable.ic_launcher_background)
                .into(holder.image);

        holder.setTitle(productData.getTitle());
        holder.setDescription(productData.getDescription());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
