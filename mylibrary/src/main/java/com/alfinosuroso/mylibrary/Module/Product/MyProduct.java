package com.alfinosuroso.mylibrary.Module.Product;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mylibrary.R;
import com.alfinosuroso.mylibrary.Api.retrofit.MyAPIService;
import com.alfinosuroso.mylibrary.Api.retrofit.MyApiClient;
import com.alfinosuroso.mylibrary.Api.retrofit.ProductAdapter;
import com.alfinosuroso.mylibrary.Api.ProductResponse.ProductData;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyProduct {
    public Context ctx;
    public Dialog dialogProduct;
    public Dialog dialogAddProduct;
    public ProductAdapter adapter;
    public MyAPIService service;
    public Call<List<ProductData>> call;
    public RecyclerView recyclerView;
    public ImageView btnBack, btnBackAdd;
    public FloatingActionButton fab;
    public EditText editTitle, editPrice, editDesc, editImage, editCategory;
    public Button btnPost;
    public ProgressBar pbLoading;
    public LinearLayout linearAppbar, linearAppbarAdd;
    public TextView tvAppbarProduct, tvFail, tvAppbarAdd, tvResponse;
    public ShimmerFrameLayout shimmerProduct;
    public SwipeRefreshLayout swipeProduct;

    public MyProduct(final Context context) {
        this.ctx = context;
        (dialogProduct = new Dialog(context, android.R.style.Theme_Holo_Light_NoActionBar)).setContentView(R.layout.product_activity);
        this.linearAppbar = (LinearLayout) dialogProduct.findViewById(R.id.include_appbar_add);
        this.tvAppbarProduct = (TextView) linearAppbar.findViewById(R.id.tv_title_appbar);
        this.fab = (FloatingActionButton) dialogProduct.findViewById(R.id.fab_add);
        this.recyclerView = (RecyclerView) dialogProduct.findViewById(R.id.rv_product);
        this.tvFail = (TextView) dialogProduct.findViewById(R.id.tv_fail_product);
        this.btnBack = (ImageView) linearAppbar.findViewById(R.id.iv_back);
        this.shimmerProduct = (ShimmerFrameLayout) dialogProduct.findViewById(R.id.shimmer_product);
        this.swipeProduct = (SwipeRefreshLayout) dialogProduct.findViewById(R.id.swipe_product);

        tvAppbarProduct.setText("Produk");
        this.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogProduct.dismiss();
            }
        });
        swipeProduct.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataProduct();
            }
        });
        getDataProduct();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (dialogAddProduct = new Dialog(ctx, android.R.style.Theme_Holo_Light_NoActionBar)).setContentView(R.layout.product_activity_add_data);
                linearAppbarAdd = (LinearLayout) dialogAddProduct.findViewById(R.id.include_appbar_add);
                tvAppbarAdd = (TextView) linearAppbarAdd.findViewById(R.id.tv_title_appbar);
                btnBackAdd = (ImageView) linearAppbarAdd.findViewById(R.id.iv_back);
                tvAppbarAdd.setText("Tambah Produk");
                btnBackAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAddProduct.dismiss();
                    }
                });
                editTitle = (EditText) dialogAddProduct.findViewById(R.id.edt_title);
                editPrice = (EditText) dialogAddProduct.findViewById(R.id.edt_price);
                editDesc = (EditText) dialogAddProduct.findViewById(R.id.edt_description);
                editImage = (EditText) dialogAddProduct.findViewById(R.id.edt_image);
                editCategory = (EditText) dialogAddProduct.findViewById(R.id.edt_category);
                btnPost = (Button) dialogAddProduct.findViewById(R.id.btn_post);
                pbLoading = (ProgressBar) dialogAddProduct.findViewById(R.id.pb_loading);
                tvResponse = (TextView) dialogAddProduct.findViewById(R.id.tv_response);

                btnPost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (editTitle.getText().toString().isEmpty() &&
                                editPrice.getText().toString().isEmpty() &&
                                editDesc.getText().toString().isEmpty() &&
                                editImage.getText().toString().isEmpty() &&
                                editCategory.getText().toString().isEmpty()) {
                            Toast.makeText(ctx, "Please enter all values", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        postProduct(service, ctx, editTitle.getText().toString(), editPrice.getText().toString(), editDesc.getText().toString(),
                                editImage.getText().toString(), editCategory.getText().toString()
                        );
                    }
                });
                dialogAddProduct.setCancelable(false);
                dialogAddProduct.show();
            }
        });
        this.dialogProduct.setCancelable(false);
        this.dialogProduct.show();
    }

    private void getDataProduct() {
        this.shimmerProduct.setVisibility(View.VISIBLE);
        this.shimmerProduct.startShimmer();
        this.recyclerView.removeAllViewsInLayout();
        this.service = MyApiClient.getRetrofitInstance().create(MyAPIService.class);
        this.call = this.service.getAllProducts();
        this.call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    generateDataList(response.body());
                    shimmerProduct.stopShimmer();
                    shimmerProduct.setVisibility(View.GONE);
                    tvFail.setVisibility(View.GONE);
                    swipeProduct.setRefreshing(false);
                } else {
//                    Handle null/empty
                    shimmerProduct.stopShimmer();
                    shimmerProduct.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    tvFail.setVisibility(View.VISIBLE);
                    tvFail.setText(response.message());
                    Toast.makeText(ctx, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Error: " + response.message());
                    swipeProduct.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                Log.d("DEBUGG", "shimmerProduct: " + (shimmerProduct != null));
                Log.d("DEBUGG", "recyclerView: " + (recyclerView != null));
                Log.d("DEBUGG", "tvFail: " + (tvFail != null));
                if (t instanceof java.net.SocketTimeoutException) {
                    Toast.makeText(ctx, "Connection timed out. Please try again.2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ctx, "Something went wrong...Please try later!2", Toast.LENGTH_SHORT).show();
                }
                shimmerProduct.stopShimmer();
                shimmerProduct.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                tvFail.setVisibility(View.VISIBLE);
                tvFail.setText(t.getMessage());
                swipeProduct.setRefreshing(false);
                Toast.makeText(ctx, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                Log.e("API Error2", "Error: " + t.getMessage());
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<ProductData> productList) {
        this.adapter = new ProductAdapter(ctx, productList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(this.adapter);
    }

    /* Add data product */
    private void postProduct(MyAPIService service, Context context, String title, String price, String desc, String image, String category) {
        this.pbLoading.setVisibility(View.VISIBLE);
        ProductData data = new ProductData();
        data.setTitle(title);
        data.setPrice(Double.parseDouble(price));
        data.setDescription(desc);
        data.setImage(image);
        data.setCategory(category);
        Call<ProductData> call = service.addProduct(data);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                // this method is called when we get response from our api.
                Toast.makeText(context, "Data berhasil masuk", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                pbLoading.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                editTitle.setText("");
                editPrice.setText("");
                editDesc.setText("");
                editImage.setText("");
                editCategory.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                ProductData responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nID : " + responseFromAPI.getId();

                // below line we are setting our
                // string to our text view.
                tvResponse.setText(responseString);
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                tvResponse.setText("Error found is : " + t.getMessage());
            }
        });
    }
}
