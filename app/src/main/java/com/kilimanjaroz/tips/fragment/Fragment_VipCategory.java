package com.kilimanjaroz.tips.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.adapter.VIPCategoryAdapter;
import com.kilimanjaroz.tips.databinding.FragmentVipcategoryBinding;
import com.kilimanjaroz.tips.network.RetrofitClient;
import com.kilimanjaroz.tips.response.AppUtils2;
import com.kilimanjaroz.tips.response.details.ViewDetailsActivity;
import com.kilimanjaroz.tips.response.vip.VIPCategoryList;
import com.kilimanjaroz.tips.response.vip.VIPCategoryResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_VipCategory extends Fragment implements PurchasesUpdatedListener {


    View view;
    FragmentVipcategoryBinding binding;
    private VIPCategoryAdapter dashboardAdapter;
    RecyclerView rvvip;
    ArrayList<VIPCategoryList> ReportMOdels2 = new ArrayList<>();
    public BillingClient billingClient;

    List<SkuDetails> skuDetailsList = new ArrayList<>();
    List<String> skuList = new ArrayList<>();
    boolean enableAll = false;

    public Fragment_VipCategory() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVipcategoryBinding.inflate(inflater, container, false);
        // initView();
        CategoryList();
        onClickListeners();
        initBillingClient();
        return binding.getRoot();
    }

    private void onClickListeners() {
        binding.onevip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (enableAll) {

                    String id1 = String.valueOf(ReportMOdels2.get(0).getId());
                    Intent mIntent = new Intent(getActivity(), ViewDetailsActivity.class);
                    mIntent.putExtra("id", id1);
                    mIntent.putExtra("name", ReportMOdels2.get(0).getCategoryName());
                    startActivity(mIntent);


                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.twoVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (enableAll) {

                    String id2 = String.valueOf(ReportMOdels2.get(1).getId());
                    Intent mIntent = new Intent(getActivity(), ViewDetailsActivity.class);
                    mIntent.putExtra("id", id2);
                    mIntent.putExtra("name", ReportMOdels2.get(1).getCategoryName());
                    startActivity(mIntent);

                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.threeVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {

                    String id3 = String.valueOf(ReportMOdels2.get(2).getId());
                    Intent mIntent = new Intent(getActivity(), ViewDetailsActivity.class);
                    mIntent.putExtra("id", id3);
                    mIntent.putExtra("name", ReportMOdels2.get(2).getCategoryName());
                    startActivity(mIntent);

                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }

            }
        });
        binding.fourVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (enableAll) {

                    String id4 = String.valueOf(ReportMOdels2.get(3).getId());
                    Intent mIntent = new Intent(getActivity(), ViewDetailsActivity.class);
                    mIntent.putExtra("id", id4);
                    mIntent.putExtra("name", ReportMOdels2.get(3).getCategoryName());
                    startActivity(mIntent);
                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
//        binding.fiveVIP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (enableAll) {
//
//                    String id5 = String.valueOf(ReportMOdels2.get(4).getId());
//                    Intent mIntent = new Intent(getActivity(), ViewDetailsActivity.class);
//                    mIntent.putExtra("id", id5);
//                    mIntent.putExtra("name", ReportMOdels2.get(4).getCategoryName());
//                    startActivity(mIntent);
//                } else {
//
//                    openPopUp(AppUtils2.ALL_VIP);
//
//                }
//            }
//        });
//        binding.sixVIP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (enableAll) {
//
//                    String id6 = String.valueOf(ReportMOdels2.get(5).getId());
//                    Intent mIntent = new Intent(getActivity(), ViewDetailsActivity.class);
//                    mIntent.putExtra("id", id6);
//                    mIntent.putExtra("name", ReportMOdels2.get(5).getCategoryName());
//                    startActivity(mIntent);
//                } else {
//
//                    openPopUp(AppUtils2.ALL_VIP);
//
//                }
//            }
//
//        });
        binding.llAllVIP.setOnClickListener(view -> {
            if (!enableAll) {
                callInAppPurchasePlan(AppUtils2.ALL_VIP);
            }
        });
    }

    private void initBillingClient() {

        // binding.llAllVIP.setVisibility(View.GONE);
        billingClient = BillingClient.newBuilder(getContext())
                .setListener(this)
                .enablePendingPurchases()
                .build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    loadPurchaseAsync();

                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });

        skuList.add(AppUtils2.ALL_VIP);

    }

    private void loadPurchaseAsync() {
        billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, (billingResult, list) -> {
            for (Purchase purchase : list) {
                for (String sku : purchase.getSkus()) {
                    if (sku.equalsIgnoreCase(AppUtils2.ALL_VIP)) {
                        enableAll = true;

                    }

                }
                //consumePurchase(purchase);
            }
            onGetInAppProductList();
        });
    }

    private void onGetInAppProductList() {
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder().setSkusList(skuList);
        billingClient.querySkuDetailsAsync(params.setType(BillingClient.SkuType.INAPP).build(),
                (billingResult, skuDetailsList) -> {
                    if (skuDetailsList.isEmpty()) {
                        Toast.makeText(getContext(), "No Product Found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    this.skuDetailsList.addAll(skuDetailsList);
                    for (SkuDetails skudetail : skuDetailsList) {
                        if (skudetail.getSku().equalsIgnoreCase(AppUtils2.ALL_VIP)) {
                            binding.tvAllVIP.setText(enableAll ? getActivity().getString(R.string.unlocked_all_vip) : getActivity().getString(R.string.access_all_vip));
                            // binding.llAllVIP.setBackgroundResource(enableAll ? R.drawable.grad1 : R.drawable.allsub);
                            binding.tvAllVIPDesc.setVisibility(enableAll ? View.GONE : View.VISIBLE);
                        }
                    }

                    getActivity().runOnUiThread(() -> {

                        binding.llAllVIP.setVisibility(View.VISIBLE);

                    });

                });
    }

    private void openPopUp(String plan) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_purchase_vip);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
//        String allPrice = getSkuDetails(AppUtils2.ALL_VIP).getPrice();
        String planName = "";


//        ((AppCompatButton) dialog.findViewById(R.id.btnPurchaseAllPlan))
//                .setText("ALL VIP Access\n (Life Time Access)\n" + allPrice);
        ((AppCompatButton) dialog.findViewById(R.id.btnPurchaseAllPlan))
                .setText("ALL VIP Access\n (Life Time Access)\n");


        dialog.findViewById(R.id.btnPurchaseAllPlan).setOnClickListener(v -> {
            callInAppPurchasePlan(AppUtils2.ALL_VIP);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public SkuDetails getSkuDetails(String id) {
        for (SkuDetails skuDetails : skuDetailsList) {
            if (skuDetails.getSku().equals(id)) {
                return skuDetails;
            }
        }
        return null;
    }

    private void callInAppPurchasePlan(String sku) {
        SkuDetails skuDetails = getSkuDetails(sku);
        if (skuDetails != null) {
            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setObfuscatedAccountId(UUID.randomUUID().toString())
                    .setSkuDetails(skuDetails)
                    .build();
            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
        } else {
            Toast.makeText(getContext(), "This Product not avialable temporary", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
        int responseCode = billingResult.getResponseCode();
        if (responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(getActivity(), "Your purchase has been canceled, we hope that you will return soon", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void handlePurchase(Purchase purchase) {
        boolean load = true;
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                load = false;

                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
                    getActivity().runOnUiThread(() -> {

                        binding.llAllVIP.setVisibility(View.GONE);
                        loadPurchaseAsync();
                    });
                });
            }
        }
        if (load) {

            binding.llAllVIP.setVisibility(View.GONE);
            loadPurchaseAsync();
        }
    }

    private void CategoryList() {

        Call<VIPCategoryResponse> call = RetrofitClient.getInstance().getApi().getGameVipResponse();
        call.enqueue(new Callback<VIPCategoryResponse>() {

            @Override
            public void onResponse(Call<VIPCategoryResponse> call, Response<VIPCategoryResponse> response) {

                if (response.isSuccessful()) {


                    VIPCategoryResponse list = response.body();
                    ReportMOdels2 = list.getData();

                    if (Objects.equals(ReportMOdels2.get(0).getCategoryName(), "Daily VIP - GH¢ 10")) {
                        binding.tvOne.setText("Daily VIP - GH¢ 10");

                    }

                    if (Objects.equals(ReportMOdels2.get(1).getCategoryName(), "Weekly VIP - GH¢ 50")) {
                        binding.tvTwo.setText("Weekly VIP - GH¢ 50");

                    }

                    if (Objects.equals(ReportMOdels2.get(2).getCategoryName(), "Bi Weekly VIP- GH¢ 90")) {
                        binding.tvThree.setText("Bi Weekly VIP- GH¢ 90");

                    }
                    if (Objects.equals(ReportMOdels2.get(3).getCategoryName(), "Monthly VIP - GH¢ 170")) {
                        binding.tvFour.setText("Monthly VIP - GH¢ 170");

                    }



                }
            }

            @Override
            public void onFailure(Call<VIPCategoryResponse> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        CategoryList();
    }

    @Override
    public void onStart() {
        super.onStart();
        CategoryList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }


}

//    private void initView() {
//        rvvip = view.findViewById(R.id.rvVIPCAT);
//
//        final lottiedialogfragment lottie = new lottiedialogfragment(getActivity());
//        lottie.show();
//
//
//        Call<VIPCategoryResponse> call = RetrofitClient.getInstance().getApi().getGameVipResponse();
//        call.enqueue(new Callback<VIPCategoryResponse>() {
//
//            @Override
//            public void onResponse(Call<VIPCategoryResponse> call, Response<VIPCategoryResponse> response) {
//
//                if (response.isSuccessful()) {
//                    lottie.dismiss();
//                    VIPCategoryResponse list = response.body();
//                    ReportMOdels2 = list.getData();
//                    showCatData();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<VIPCategoryResponse> call, Throwable t) {
//                lottie.dismiss();
//                t.printStackTrace();
//
//            }
//        });
//
//    }
//
//    private void showCatData() {
//
//        dashboardAdapter = new VIPCategoryAdapter(getActivity(), ReportMOdels2);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        rvvip.setLayoutManager(layoutManager);
//        rvvip.setAdapter(dashboardAdapter);
//
//    }

