import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;

public class Test {



    public static void main(String[] args) {
        JedisPoolConfig config=new JedisPoolConfig();

        ArrayList<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo("192.168.64.150",7000));
        shards.add(new JedisShardInfo("192.168.64.150",7001));
        shards.add(new JedisShardInfo("192.168.64.150",7002));

        ShardedJedisPool jedisPool = new ShardedJedisPool(config, shards);
        ShardedJedis resource = jedisPool.getResource();
        for (int i=0;i<100;i++){
            resource.set("key"+i, "value"+i);
        }
        jedisPool.close();
    }
}
