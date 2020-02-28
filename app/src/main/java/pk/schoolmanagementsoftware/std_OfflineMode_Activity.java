package pk.schoolmanagementsoftware;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.veyo.autorefreshnetworkconnection.AutoRefreshNetworkUtil;
import com.veyo.autorefreshnetworkconnection.CheckNetworkConnectionHelper;
import com.veyo.autorefreshnetworkconnection.listener.OnNetworkConnectionChangeListener;

public class std_OfflineMode_Activity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    LinearLayout linearLayout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.std_activity_offline_mode_);


        textView = findViewById(R.id.tv_network);
        imageView = findViewById(R.id.net_img);
        linearLayout = findViewById(R.id._layout_internet);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoRefreshNetworkUtil.removeAllRegisterNetworkListener();
                Intent intent=new Intent(std_OfflineMode_Activity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        CheckNetworkConnectionHelper
                .getInstance()
                .registerNetworkChangeListener(new OnNetworkConnectionChangeListener() {
                    @Override
                    public void onConnected() {
                         linearLayout.setVisibility(View.INVISIBLE);
                        Intent intent=new Intent(std_OfflineMode_Activity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                     }

                    @Override
                    public void onDisconnected() {
                         linearLayout.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public Context getContext() {
                        return std_OfflineMode_Activity.this;
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
     }
}
