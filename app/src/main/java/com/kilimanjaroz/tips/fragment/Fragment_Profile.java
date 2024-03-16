package com.kilimanjaroz.tips.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.kilimanjaroz.tips.BuildConfig;
import com.kilimanjaroz.tips.R;
import com.kilimanjaroz.tips.ui.home.NotificationActivity;

public class Fragment_Profile extends Fragment {


    View view;
    CardView shareapp, share, noti,whshare,rate,emailshare;
    private  CardView telegram;
    FrameLayout addView;
    private static final String TAG = "updateprofile";
    public Fragment_Profile() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
      //  calladsapi();
        
        addView = view.findViewById(R.id.adContainer);
        telegram = view.findViewById(R.id.cdtel);
        emailshare = view.findViewById(R.id.cdemail);
        whshare = view.findViewById(R.id.cdwhasapp);
        rate = view.findViewById(R.id.rate);
        shareapp = view.findViewById(R.id.cdshare);
        noti = view.findViewById(R.id.notificationID);
        emailshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] recipients = {"mamboka@gmail.com"}; // Replace with desired email recipients
                String subject = "Email subject"; // Replace with desired email subject
                String body = "Email body"; // Replace with desired email body

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, body);

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } else {

                }
            }
        });
        whshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  number="+255716599505";
                String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });


        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "killimanjaroz");
                    String shareMessage = "\nDOWNLOAD and use killimanjaroz app \n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });



        return view;

    }



    private void launchMarket() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kilimanjaroz.tips&hl=en&gl=US"));
        startActivity(browserIntent);


    }
}
