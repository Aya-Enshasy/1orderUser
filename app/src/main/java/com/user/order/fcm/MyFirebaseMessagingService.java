package com.user.order.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.user.order.R;
import com.user.order.ui.activivtes.chat.ChatActivity;
import com.user.order.ui.activivtes.home.publicOrders.PublicOrderDetailsActivity;
import com.user.order.ui.activivtes.orders.OrderDetailsActiviy;
import com.user.order.utils.Const;
import com.user.order.utils.PreferencesManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    String TAG = "MyFirebaseMessagingService";
    Context context = MyFirebaseMessagingService.this;


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        PreferencesManager.setStringPreferences(Const.KEY_FCM_TOKEN, token);

        Log.e("FCM_TOKEN", token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message_data_payload: " + remoteMessage.getData());

        if (remoteMessage.getData().size() > 0) {
            Map<String, String> params = remoteMessage.getData();
            JSONObject object = new JSONObject(params);
            Log.e("JSON OBJECT", object.toString());
            try {
                String message = object.getString("message");
                JSONObject data = new JSONObject(message);
                JSONObject data2 = data.getJSONObject("data");

                if (data2.has("type")) {
                    Log.e("type", data2.toString());
                    if (remoteMessage.getNotification() != null) {
                        sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), data2);
                        sendData(data2);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("printStackTrace", e.toString());
            }
        }
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }

    private void sendNotification(String title, String body, JSONObject data2) {

        PendingIntent contentIntent = null;
        Intent intent = null;
        String type = null;
        String status = null;
        String user_id = null;
        String invoice_no = null;
        try {
            user_id = data2.getString("user_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            invoice_no = data2.getString("invoice_no");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            type = data2.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            status = data2.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (type.equals("public") && status.equals("new_message")) {
            String public_order_id = null;
            try {
                public_order_id = data2.getString("public_order_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            intent = new Intent(getBaseContext(), ChatActivity.class);
            intent.putExtra(Const.KEY_INVOICE_NUMBER, invoice_no);
            intent.putExtra(Const.KEY_CLIENT_ID, user_id);
            intent.putExtra(Const.KEY_ORDER_ID, public_order_id);
            intent.putExtra(Const.KEY_PUBLIC_CHAT, Const.KEY_PUBLIC_CHAT);
            intent.putExtra(Const.KEY_STATUS, status);
            intent.putExtra(Const.KEY_ORDER_ID, public_order_id);

        } else if (type.equals("public")) {
            String public_order_id = null;
            try {
                public_order_id = data2.getString("public_order_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            intent = new Intent(getBaseContext(), PublicOrderDetailsActivity.class);
            intent.putExtra("public_order_id", public_order_id);
            intent.putExtra(Const.KEY_ORDER_ID, public_order_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }

        else if (type.equals("1order")) {
            String order_id = null;
            try {
                order_id = data2.getString("order_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            intent = new Intent(getBaseContext(), OrderDetailsActiviy.class);
            intent.putExtra(Const.KEY_ORDER_ID, order_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            //  }
        } else {
            intent = new Intent();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
        } else {
            contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        }


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence tickerText = body;

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "M_CH_ID");

        notificationBuilder.setAutoCancel(true)
                .setSmallIcon(R.drawable.logo)
                .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(contentIntent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(tickerText));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            String channelId = getString(R.string.app_name);
            NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(body);
            channel.enableVibration(true);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.notification);
            mp.start();
            channel.setSound(sound, audioAttributes);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelId);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

    private void sendData(JSONObject obj) {
        String type = "";
        try {
            type = obj.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String status = "";
        try {
            status = obj.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String order_id = "";
        try {
            order_id = obj.getString("order_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String public_order_id = "";
        try {
            public_order_id = obj.getString("public_order_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String pickup_address_en = "";
        try {
            pickup_address_en = obj.getString("pickup_address_en");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String destination_address = "";
        try {
            destination_address = obj.getString("destination_address");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String store_name = "";
        try {
            store_name = obj.getString("store_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String type_of_receive = "";
        try {
            type_of_receive = obj.getString("type_of_receive");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventBus.getDefault().post(new MyEventBus(type, status, order_id, destination_address, pickup_address_en, public_order_id, store_name, type_of_receive));

    }


}

