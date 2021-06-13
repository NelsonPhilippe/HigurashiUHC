package fr.xilitra.higurashiuhc.redis;

import org.redisson.api.RedissonClient;

public class Redis {

    private static RedissonClient client;

    public static RedissonClient getRedissonClient(){
        return client;
    }

    public static void createConnexion(RedisCredentials credentials){
        client = new RedisConnexion(credentials).getClient();
    }

}
