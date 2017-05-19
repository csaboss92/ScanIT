package csaboss.scanit.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.ui.documentlist.DocumentListActivity;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity implements LoginScreen {

    EditText userName;
    EditText password;
    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fabric.with(this, new Crashlytics());
        Button loginbtn = (Button) findViewById(R.id.login);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(userName.getText().toString(),password.getText().toString());
            }
        });
        ScanITApplication.injector.inject(this);
        Button crash = (Button) findViewById(R.id.crash);
        crash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> a = new ArrayList<String>();
            a.get(150);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
        loginPresenter.getUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    public void login(String name, String password){
        loginPresenter.login(name,password);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, DocumentListActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void loginError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
