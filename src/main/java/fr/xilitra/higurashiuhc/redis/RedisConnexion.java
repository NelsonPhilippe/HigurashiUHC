package fr.xilitra.higurashiuhc.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisClientConfig;
import org.redisson.config.Config;

public class RedisConnexion {

    private RedissonClient client;
    private RedisCredentials credentials;

    public RedisConnexion(RedisCredentials credentials) {
        this.credentials = credentials;
        init();
    }

    private void init(){
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://" + credentials.getHost() + ":" + credentials.getPort());
        client = Redisson.create(config);
    }

    public RedissonClient getClient() {
        return client;
    }
}
