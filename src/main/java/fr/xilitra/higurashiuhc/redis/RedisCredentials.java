package fr.xilitra.higurashiuhc.redis;

public class RedisCredentials {

    private String host;
    private int port;
    private String password;
    private int db;

    public RedisCredentials(String host, int port, String password, int db) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.db = db;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public int getDb() {
        return db;
    }
}
