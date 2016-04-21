package at.reilaender.dezsys11.handler;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import at.reilaender.dezsys11.R;
import at.reilaender.dezsys11.config.ServerConfig;
import at.reilaender.dezsys11.entities.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

/**
 * @author mreilaender
 * @version 20.04.2016
 */
public class RegisterButtonHandler implements View.OnClickListener {
    final private Activity activity;
    private RestTemplate restTemplate;

    public RegisterButtonHandler(Activity activity) {
        this.activity = activity;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void onClick(View view) {
        String username = ((EditText) this.activity.findViewById(R.id.tf_username)).getText().toString(),
                email = ((EditText) this.activity.findViewById(R.id.tf_email)).getText().toString(),
                password = ((EditText) this.activity.findViewById(R.id.tf_email)).getText().toString();
        User user = new User(email, username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<String>(user.toString(), headers);
        try {
            ResponseEntity responseEntity = new at.reilaender.dezsys11.RegisterButtonHandler(httpEntity, ServerConfig.url).execute().get();
            if(responseEntity != null)
                System.out.println(responseEntity.getBody().toString());
            // TODO responseBody irgendwo in der App ausgeben -> kurze infobox unten (diese schwarze die kurz da is und wieder verschwindet)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
