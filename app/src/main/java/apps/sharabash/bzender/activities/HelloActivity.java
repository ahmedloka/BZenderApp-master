package apps.sharabash.bzender.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import apps.sharabash.bzender.R;
import apps.sharabash.bzender.activities.login.Login;
import apps.sharabash.bzender.activities.signUp.SignUp;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
            //finish();
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
            //finish();
        });

    }
}
