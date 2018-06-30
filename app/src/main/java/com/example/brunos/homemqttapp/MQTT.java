package com.example.brunos.homemqttapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static android.content.ContentValues.TAG;

public class MQTT {
    private static final String TAGmqtt = "mqttTAG";
    private static final String USERNAME = "Brunosugui";
    private static final String PASSWORD = "mqttpass";
    private static final String BROKER = "tcp://broker.hivemq.com:1883";
    private static final String topic1 = "Buddha/ESP32/serialdata/rx";
    private static final String topic2 = "Buddha/ESP32/serialdata/temperature";
    private static final String payload1 = "H = 2424";
    private static final int QOS = 0;
    private MqttAndroidClient client;
    private Context context;

    public MQTT(Context context){
        this.context = context;
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(context, BROKER,
                clientId);
    }

    public void connectMQTTserver() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());
        try {
            IMqttToken token = client.connect(options);
            Log.d(TAGmqtt,"attempt to connect" );
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d(TAGmqtt, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAGmqtt, "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    public void publish(String Topic, String Payload){
        try {
            client.publish(Topic, Payload.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void subscribe(String Topic){
        try {
            client.subscribe(Topic, QOS);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

public void setMQTTcallback(){
    client.setCallback(new MqttCallback() {
        @Override
        public void connectionLost(Throwable throwable) {

        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
            Log.d(TAG, "Message rcvd: " + mqttMessage.toString());


        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        }
    });
}
}


