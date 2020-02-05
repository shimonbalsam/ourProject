package com.example.ourproject.views.ui.slideshow;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ourproject.R;

import static androidx.core.content.ContextCompat.checkSelfPermission;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);


        final Button buttonsms = (Button) root.findViewById(R.id.sendsms);
        buttonsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(),0,new Intent("SMS_SENT"),0);
                PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(),0,new Intent("SMS_DELIVERED"),0);




                if(checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED) {
                    SmsManager sms = SmsManager.getDefault();
                    String massage = ((TextView) root.findViewById(R.id.editmassage)).getText().toString();
                    String number = ((TextView) root.findViewById(R.id.editPhonesms)).getText().toString();
                    sms.sendTextMessage(number, null, massage, sentPI,
                            deliveredPI);
                }



            }
        });

        final Button buttonInternet = (Button) root.findViewById(R.id.google);
        buttonInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ynet.co.il/home/0,7340,L-2,00.html"));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), "No application can handle this request."
                            + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        final Button buttonwhatsapp = (Button)root.findViewById(R.id.whatsapp);
        buttonwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This text from my app.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });


        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final TextView textView = root.findViewById(R.id.app);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("");
            }
        });
        return root;
    }
}