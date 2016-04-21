package at.reilaender.dezsys11.handler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import at.reilaender.dezsys11.activities.WelcomeActivity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author mreilaender
 * @version 21.04.2016
 */
public class LoginRequestListener implements RequestListener<ResponseEntity, HttpClientErrorException> {
    private Activity mainActivity;
    private AlertDialog alertDialog;

    public LoginRequestListener(Activity activity) {
        this.mainActivity = activity;
        this.alertDialog = new AlertDialog.Builder(this.mainActivity).create();
    }

    @Override
    public void onSucess(ResponseEntity responseEntity) {
        this.mainActivity.runOnUiThread(() -> {
            Intent intent = new Intent(mainActivity.getApplicationContext(), WelcomeActivity.class);
            intent.putExtra("user", responseEntity.getBody().toString());
            mainActivity.navigateUpTo(intent);
        });
    }

    @Override
    public void onFailure(HttpClientErrorException e) {
        this.mainActivity.runOnUiThread(() -> {
            alertDialog.setMessage(e.getResponseBodyAsString());
            alertDialog.show();
        });
    }
}
