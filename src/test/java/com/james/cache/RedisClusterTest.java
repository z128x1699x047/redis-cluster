package com.james.cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.james.cache.factory.JedisClusterFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/*
 * AUTHOR james
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisClusterTest {
    @Autowired
    JedisClusterFactory jf;
    @Test
    public void testPutCache() throws Exception {
    	JedisCluster jc = jf.getObject();
    	
        jc.set("class", "112233");

        System.out.println(jc.get("class"));
    }
   // @Test
    public void testBasic() throws IOException{
    	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("192.168.42.111", 6379));
        jedisClusterNodes.add(new HostAndPort("192.168.42.111", 6380));
        jedisClusterNodes.add(new HostAndPort("192.168.42.111", 6381));
        jedisClusterNodes.add(new HostAndPort("192.168.42.111", 6389));
        jedisClusterNodes.add(new HostAndPort("192.168.42.111", 6390));
        jedisClusterNodes.add(new HostAndPort("192.168.42.111", 6391));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        jc.set("james:age", "18");
        
        System.out.println("==set successful!!");
        String value = jc.get("james:age");
        System.out.println(value);
        jc.close();
    }
} 