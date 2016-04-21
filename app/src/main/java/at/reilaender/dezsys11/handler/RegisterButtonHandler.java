package at.reilaender.dezsys11.handler;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import at.reilaender.dezsys11.R;
import at.reilaender.dezsys11.SendRegisterRequest;
import at.reilaender.dezsys11.config.ServerConfig;
import at.reilaender.dezsys11.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author mreilaender
 * @version 20.04.2016
 */
public class RegisterButtonHandler implements View.OnClickListener {
    final private Activity activity;

    public RegisterButtonHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        String username = ((EditText) this.activity.findViewById(R.id.tf_register_username)).getText().toString(),
                email = ((EditText) this.activity.findViewById(R.id.tf_register_email)).getText().toString(),
                password = ((EditText) this.activity.findViewById(R.id.tf_register_password)).getText().toString();
        User user = new User(email, username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(user.toString(), headers);
        AsyncTask asyncTask = new SendRegisterRequest(httpEntity, ServerConfig.url, new RegisterRequestListener(this.activity)).execute();
    }
}
