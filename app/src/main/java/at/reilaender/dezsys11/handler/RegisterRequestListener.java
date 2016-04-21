package at.reilaender.dezsys11.handler;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
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
        //TODO retrolambda
        this.mainActivity.runOnUiThread(() -> {
            alertDialog.setMessage(responseEntity.getBody().toString());
            alertDialog.show();
        });
    }

    @Override
    public void onFailure(final HttpClientErrorException e) {
        //TODO retrolambda
        this.mainActivity.runOnUiThread(() -> {
            alertDialog.setMessage(e.getResponseBodyAsString());
            alertDialog.show();
        });
    }
}
