package at.reilaender.dezsys11.config;

/**
 * @author mreilaender
 * @version 20.04.2016
 */
public class ServerConfig {
    public static String protocol = "http";
    public static String host = "10.0.2.2";
    public static int port = 8080;
    public static String url = String.format("%s://%s:%d", protocol, host, port);
}
