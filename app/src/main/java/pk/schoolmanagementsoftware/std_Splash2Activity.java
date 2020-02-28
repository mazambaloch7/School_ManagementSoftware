package pk.schoolmanagementsoftware;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class std_Splash2Activity extends AppCompatActivity {

    private int splashTimer = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.std_activity_main2);

        new Handler().postDelayed(new Runnable() { // to ensure that the splash screen loads for 3 seconds
            @Override
            public void run() {
                Intent intent = new Intent(std_Splash2Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, splashTimer);
    }

}
