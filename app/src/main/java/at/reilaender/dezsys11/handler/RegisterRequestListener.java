package at.reilaender.dezsys11.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;
import at.reilaender.dezsys11.R;
import at.reilaender.dezsys11.SendLoginRequest;
import at.reilaender.dezsys11.activities.LoginActivity;
import at.reilaender.dezsys11.activities.RegisterActivity;
import at.reilaender.dezsys11.activities.WelcomeActivity;
import at.reilaender.dezsys11.config.ServerConfig;
import at.reilaender.dezsys11.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author mreilaender
 * @version 21.04.2016
 */
public class RegisterRequestListener implements RequestListener<ResponseEntity, HttpClientErrorException> {
    private Activity mainActivity;
    private AlertDialog alertDialog;

    public RegisterRequestListener(Activity mainActivity) {
        this.mainActivity = mainActivity;
        this.alertDialog = new AlertDialog.Builder(this.mainActivity).create();
    }

    @Override
    public void onSucess(final ResponseEntity responseEntity) {
        this.mainActivity.runOnUiThread(() -> {
            String email = ((EditText) this.mainActivity.findViewById(R.id.tf_register_email)).getText().toString(),
                    password = ((EditText) this.mainActivity.findViewById(R.id.tf_register_password)).getText().toString();
            User user = new User(email, password);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<String>(user.toString(), headers);
            AsyncTask asyncTask = new SendLoginRequest(httpEntity, ServerConfig.url, new LoginRequestListener(this.mainActivity)).execute();
            //Intent intent = new Intent(mainActivity.getApplicationContext(), WelcomeActivity.class);
            //intent.putExtra("user", responseEntity.getBody().toString());
            //mainActivity.navigateUpTo(intent);
        });
    }

    @Override
    public void onFailure(final HttpClientErrorException e) {
        this.mainActivity.runOnUiThread(() -> {
            alertDialog.setMessage(e.getResponseBodyAsString());
            alertDialog.show();
        });
    }
}
