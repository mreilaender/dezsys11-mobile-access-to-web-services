package at.reilaender.dezsys11;

import android.os.AsyncTask;
import at.reilaender.dezsys11.handler.RequestListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author mreilaender
 * @version 20.04.2016
 */
public class SendLoginRequest extends AsyncTask<HttpEntity, Void, Void> {
    private RestTemplate restTemplate;
    private HttpEntity<String> httpEntity;
    private String url;
    private RequestListener requestListener;

    public SendLoginRequest(HttpEntity<String> httpEntity, String url, RequestListener requestListener) {
        this.httpEntity = httpEntity;
        this.url = url;
        this.restTemplate = new RestTemplate();
        this.requestListener = requestListener;
    }

    @Override
    protected Void doInBackground(HttpEntity... httpEntities) {
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = this.restTemplate.postForEntity(this.url + "/login", this.httpEntity, String.class);
            this.requestListener.onSucess(responseEntity);
        } catch (HttpClientErrorException e) {
            this.requestListener.onFailure(e);
        }
        return null;
    }
}
