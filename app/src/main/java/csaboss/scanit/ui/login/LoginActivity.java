package csaboss.scanit.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import csaboss.scanit.R;
import csaboss.scanit.ScanITApplication;
import csaboss.scanit.ui.documentlist.DocumentListActivity;

public class LoginActivity extends AppCompatActivity implements LoginScreen {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginbtn = (Button) findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("Jenő","Nagyonjelszo");
            }
        });
        ScanITApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.detachScreen();
    }

    public void login(String name, String password){
        //TODO: login;
        loginPresenter.login(name,password);

    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, DocumentListActivity.class);
        startActivity(i);
    }

    @Override
    public void loginError(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
