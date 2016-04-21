package at.reilaender.dezsys11.handler;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import at.reilaender.dezsys11.R;
import at.reilaender.dezsys11.SendLoginRequest;
import at.reilaender.dezsys11.config.ServerConfig;
import at.reilaender.dezsys11.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * @author mreilaender
 * @version 21.04.2016
 */
public class LoginButtonHandler implements View.OnClickListener {
    final private Activity activity;

    public LoginButtonHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        String email = ((EditText) this.activity.findViewById(R.id.tf_login_email)).getText().toString(),
                password = ((EditText) this.activity.findViewById(R.id.tf_login_password)).getText().toString();
        User user = new User(email, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(user.toString(), headers);
        ResponseEntity responseEntity = null;
        AsyncTask asyncTask = new SendLoginRequest(httpEntity, ServerConfig.url, new LoginRequestListener(this.activity)).execute();
    }
}
