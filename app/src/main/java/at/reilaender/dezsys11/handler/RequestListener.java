package at.reilaender.dezsys11.handler;

/**
 * @author mreilaender
 * @version 21.04.2016
 */
public interface RequestListener<Success, Failure> {
    public void onSucess(Success success);
    public void onFailure(Failure failure);
}
