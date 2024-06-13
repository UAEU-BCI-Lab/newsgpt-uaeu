package com.example.nmt;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.os.Bundle;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.aldebaran.qi.sdk.object.locale.Language;
import com.aldebaran.qi.sdk.object.locale.Locale;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.object.conversation.Chat;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.locale.Region;
import android.media.MediaPlayer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.net.URISyntaxException;
import android.widget.Toast;


public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {
    // Store the Chat action.
    private Chat chat;
    private MediaPlayer mediaPlayer;
    private static final String TAG = "MyChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);

    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    private WebSocketClient webSocketClient;
//    private QiContext qiContext;  // Store the QiContext for later use.

    public void onRobotFocusGained(QiContext qiContext) {
       try {
           URI uri = new URI("ws://192.168.146.105:80");
           WebSocketClient webSocketClient = new WebSocketClient(uri) {

               @Override
               public void onOpen(ServerHandshake handshakedata) {
                   Log.d("WebSocket", "onOpen");
                   // Send 'CHAT' message upon connecting
                   this.send("CHAT");
               }

               @Override
               public void onMessage(String message) {
                   Log.d("WebSocket", "onMessage: " + message);
                   utterMessage(message);

               }

               @Override
               public void onClose(int code, String reason, boolean remote) {
                   Log.d("WebSocket", "onClose: " + reason);
                   // Check if the closure was not intended by the client
                   if (remote) {
                       reconnect();
                   }
               }

               @Override
               public void onError(Exception ex) {
                   Log.e("WebSocket", "onError: " + ex.getMessage());
               }

               private void utterMessage(String message) {
                   if (qiContext != null) {
                       Say say = SayBuilder.with(qiContext)
                               .withText(message)
                               .build();
                       say.run();

                       // Display the message in a toast
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                           }
                       });

                       this.send("CHAT");
                   }
               }

           };

           webSocketClient.connect();

       } catch (URISyntaxException e) {
           e.printStackTrace();
       }


    }

    @Override
    public void onRobotFocusLost() {
        // Remove on started listeners from the Chat action.
        if (webSocketClient != null) {
            webSocketClient.close();
        }

    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
}