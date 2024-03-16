package com.kilimanjaroz.tips.ui.sub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.kilimanjaroz.tips.databinding.ActivitySubscriptionBinding;
import com.kilimanjaroz.tips.response.AppUtils2;
import com.kilimanjaroz.tips.ui.home.MainActivity;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubscriptionActivity extends AppCompatActivity implements PurchasesUpdatedListener {
    ActivitySubscriptionBinding binding;
    private ImageView back;
    public BillingClient billingClient;

    List<SkuDetails> skuDetailsList = new ArrayList<>();
    List<String> skuList = new ArrayList<>();
    boolean enableAll = false;
    boolean enableyearAll = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubscriptionBinding.inflate(getLayoutInflater());

        initBillingClient();
        onClickListeners();
        setContentView(binding.getRoot());

    }

    private void initBillingClient() {

        // binding.llAllVIP.setVisibility(View.GONE);
        billingClient = BillingClient.newBuilder(getApplicationContext())
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
        skuList.add(AppUtils2.ALL_VIP_YEarly);

    }

    private void loadPurchaseAsync() {
        billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, (billingResult, list) -> {
            for (Purchase purchase : list) {
                for (String sku : purchase.getSkus()) {
                    if (sku.equalsIgnoreCase(AppUtils2.ALL_VIP)) {
                        enableAll = true;


                    }
                    if (sku.equalsIgnoreCase(AppUtils2.ALL_VIP_YEarly)) {
                        enableyearAll = true;


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
                        Toast.makeText(getApplicationContext(), "No Product Found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    this.skuDetailsList.addAll(skuDetailsList);
                    for (SkuDetails skudetail : skuDetailsList) {
                        if (skudetail.getSku().equalsIgnoreCase(AppUtils2.ALL_VIP)) {
                            binding.tvAllVIP.setText(enableAll ? getString(R.string.unlocked_all_vip) : getString(R.string.access_all_vip));
                            // binding.llAllVIP.setBackgroundResource(enableAll ? R.drawable.grad1 : R.drawable.allsub);
                            binding.tvAllVIPDesc.setVisibility(enableAll ? View.GONE : View.VISIBLE);
                        }
                        if (skudetail.getSku().equalsIgnoreCase(AppUtils2.ALL_VIP_YEarly)) {
                            binding.tvAllVIP.setText(enableAll ? getString(R.string.unlocked_all_vip) : getString(R.string.access_all_vip));
                            // binding.llAllVIP.setBackgroundResource(enableAll ? R.drawable.grad1 : R.drawable.allsub);
                            binding.tvAllVIPDesc.setVisibility(enableAll ? View.GONE : View.VISIBLE);
                        }
                    }

                   runOnUiThread(() -> {

                        binding.btnPurchaseAllPlan.setVisibility(View.VISIBLE);
                        binding.btnPurchasemonthlyPlan.setVisibility(View.VISIBLE);

                    });

                });
    }

    private void onClickListeners() {

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubscriptionActivity.this, MainActivity.class));
            }
        });

        binding.btnPurchaseAllPlan.setOnClickListener(view -> {
            if (!enableAll) {
                callInAppPurchasePlan(AppUtils2.ALL_VIP);
            }
            else {

                Toast.makeText(getApplicationContext(), "Subscribed", Toast.LENGTH_SHORT).show();



            }
        });
        binding.btnPurchasemonthlyPlan.setOnClickListener(view -> {
            if (!enableAll) {
                callInAppPurchasePlan(AppUtils2.ALL_VIP_YEarly);
            }
            else {

                Toast.makeText(getApplicationContext(), "Subscribed", Toast.LENGTH_SHORT).show();



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
            billingClient.launchBillingFlow(this, billingFlowParams);
        } else {
            Toast.makeText(getApplicationContext(), "This Product not avialable temporary", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(), "Your purchase has been canceled, we hope that you will return soon", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
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
                    runOnUiThread(() -> {

                        binding.btnPurchaseAllPlan.setVisibility(View.GONE);
                        binding.btnPurchasemonthlyPlan.setVisibility(View.GONE);
                        loadPurchaseAsync();
                    });
                });
            }
        }
        if (load) {

            binding.btnPurchaseAllPlan.setVisibility(View.GONE);
            binding.btnPurchasemonthlyPlan.setVisibility(View.GONE);
            loadPurchaseAsync();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SubscriptionActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (billingClient != null) {
            billingClient.endConnection();
        }
    }
}
