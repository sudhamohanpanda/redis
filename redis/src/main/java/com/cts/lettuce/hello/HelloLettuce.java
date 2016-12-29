package com.cts.lettuce.hello;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisStringCommands;

public class HelloLettuce {

	public static void main(String[] args) {
		RedisClient client = RedisClient.create("redis://localhost");
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringCommands sync = connection.sync();
		sync.set("name", "sudha");
		String value = (String)sync.get("name");
		System.out.println("Value:"+value);

	}

}
