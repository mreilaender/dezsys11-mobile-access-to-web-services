package at.reilaender.dezsys11;

import android.os.AsyncTask;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author mreilaender
 * @version 20.04.2016
 */
public class SendRegisterRequest extends AsyncTask<HttpEntity, Void, ResponseEntity> {
    private RestTemplate restTemplate;
    private HttpEntity httpEntity;
    private String url;

    public SendRegisterRequest(HttpEntity httpEntity, String url) {
        this.httpEntity = httpEntity;
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    @Override
    protected ResponseEntity doInBackground(HttpEntity... httpEntities) {
        try {
            return this.restTemplate.postForEntity(this.url + "/register", this.httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
