package com.example.week_11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Phone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Phone extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText phoneNumber;

    TelecomManager telecomManager;
    Button call, sendmess,sendapp;

    TextView SMS;
    SmsManager smsManager = SmsManager.getDefault();
    TextView mymessage;
    String message = "";
    public Phone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Phone.
     */
    // TODO: Rename and change types and number of parameters
    public static Phone newInstance(String param1, String param2) {
        Phone fragment = new Phone();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int REQUEST_SMS_PERMISSION_CODE = 123;
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.RECEIVE_SMS}, REQUEST_SMS_PERMISSION_CODE);
        }
        telecomManager = (TelecomManager) getActivity().getSystemService(Context.TELECOM_SERVICE);

        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        getActivity().registerReceiver(SmsReceiver, filter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone,container,false);

        phoneNumber = view.findViewById(R.id.phoneNumber);
        call = view.findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneCall = new Intent(Intent.ACTION_DIAL);
                phoneCall.setData(Uri.parse("tel:"+phoneNumber.getText().toString()));
                startActivity(phoneCall);
            }
        });
        sendmess = view.findViewById(R.id.sendmess);
        SMS = view.findViewById(R.id.SMS);
        sendmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:"+phoneNumber.getText().toString()));
                send.putExtra("sms_body",SMS.getText().toString());
                startActivity(send);
                Toast.makeText(getContext(),"Send SMS",Toast.LENGTH_SHORT).show();
            }
        });
        sendapp = view.findViewById(R.id.sendapp);
        sendapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phoneNumber.getText().toString();
                String smsText = SMS.getText().toString();
                smsManager.sendTextMessage(phone, null, smsText, null, null);

                Toast.makeText(getContext(),"Send SMS",Toast.LENGTH_SHORT).show();

            }
        });

        mymessage = view.findViewById(R.id.mymessage);
        return view;

    }

    BroadcastReceiver SmsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = message.getDisplayOriginatingAddress();
                        String messageBody = message.getMessageBody();

                        // Display the SMS in your app's UI
                        updateUiWithSms(sender, messageBody);
                    }
                }
            }
        }
    };

    private void updateUiWithSms(String sender, String message) {
        // Update your app's UI to display the received SMS
        // You can use TextView or any other UI element to display the message.
        mymessage.setText(sender + " send you message: "+message);
    }



}