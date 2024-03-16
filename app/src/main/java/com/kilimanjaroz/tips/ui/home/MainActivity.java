package com.kilimanjaroz.tips.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryPurchasesParams;
import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.fragment.Fragment_Profile;
import com.kilimanjaroz.tips.fragment.Fragment_free;
import com.kilimanjaroz.tips.fragment.Fragment_homeCategory;
import com.kilimanjaroz.tips.fragment.Fragment_notification;
import com.kilimanjaroz.tips.fragment.Fragment_viptipslist;
import com.kilimanjaroz.tips.fragment.MatchesFragment;
import com.kilimanjaroz.tips.ui.sub.Security;
import com.kilimanjaroz.tips.ui.sub.SubscriptionActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private BillingClient billingClient;


    boolean isSuccess = false;
    boolean isSubscribed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment_free()).commit();
        }

        billingClient = BillingClient.newBuilder(this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        purchaseQuery();

        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        Fragment_homeCategory homeFragment = new Fragment_homeCategory();
        Fragment_Profile profileFragment = new Fragment_Profile();
        Fragment_viptipslist notiFragment = new Fragment_viptipslist();
        MatchesFragment vipFragment = new MatchesFragment();
        Fragment_free freeFragment = new Fragment_free();



        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, freeFragment).commit();
                    return true;

                case R.id.nav_live:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, vipFragment).commit();
                    return true;
                case R.id.nav_tab:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                    return true;

                case R.id.nav_message:

                    if (isSuccess) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, notiFragment).commit();
                    } else {
                        startActivity(new Intent(MainActivity.this, SubscriptionActivity.class));
                        finish();
                    }

                    return true;
                case R.id.nav_account:

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();

                    return true;
            }
            return false;
        });

    }



    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
                for (Purchase purchase : purchases) {
                    String packageName = purchase.getOrderId();
                    Log.d("billingClient", "packageName: " + packageName);
                    handlePurchase(purchase);
                }
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
                Toast.makeText(MainActivity.this, "ITEM_ALREADY_OWNED", Toast.LENGTH_SHORT).show();
                isSuccess = true;
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_UNAVAILABLE) {
                Toast.makeText(MainActivity.this, "ITEM_UNAVAILABLE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void handlePurchase(Purchase purchase) {
        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.getPurchaseToken())
                .build();

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // Handle successful purchase
                }
            }
        };

        billingClient.consumeAsync(consumeParams, listener);

        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
                Toast.makeText(this, "Invalid Purchase", Toast.LENGTH_SHORT).show();
                return;
            }

            if (purchase.isAcknowledged()) {
                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
                Toast.makeText(this, "Subscribed", Toast.LENGTH_SHORT).show();
                isSuccess = true;
            } else {
                Toast.makeText(this, "Already Subscribed", Toast.LENGTH_SHORT).show();
            }
        } else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING) {
            Toast.makeText(this, "PENDING", Toast.LENGTH_SHORT).show();
        } else if (purchase.getPurchaseState() == Purchase.PurchaseState.UNSPECIFIED_STATE) {
            Toast.makeText(this, "UNSPECIFIED_STATE", Toast.LENGTH_SHORT).show();
        }
    }

    private AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                Toast.makeText(getApplicationContext(), "Subscribed", Toast.LENGTH_SHORT).show();
                isSuccess = true;
            }
        }
    };

    private boolean verifyValidSignature(String signedData, String signature) {
        try {
            String base64key = getString(R.string.base64key);
            Security security = new Security();
            return security.verifyPurchase(base64key, signedData, signature);
        } catch (IOException e) {
            return false;
        }
    }


    private void purchaseQuery() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                // Not yet implemented
            }

            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            billingClient.queryPurchasesAsync(
                                    QueryPurchasesParams.newBuilder()
                                            .setProductType(BillingClient.ProductType.SUBS)
                                            .build(),
                                    new PurchasesResponseListener() {
                                        @Override
                                        public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> purchaseList) {
                                            for (Purchase purchase : purchaseList) {
                                                if (purchase != null) {
                                                    isSuccess = true;
                                                    Toast.makeText(MainActivity.this, "Now Subscribed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

//                                        @Override
//                                        public void onQueryPurchasesFinished(BillingResult billingResult, List<Purchase> purchaseList) {
//                                            for (Purchase purchase : purchaseList) {
//                                                if (purchase != null) {
//                                                    isRemoveAds = true;
//                                                    Log.d("billingClient", "state: " + isRemoveAds);
//                                                }
//                                            }
//                                        }
                                    });

                        } catch (Exception e) {

                        }
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // Handle interruption exception
                        }
                    }
                });
            }
        });
    }
}
