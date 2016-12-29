package com.cts.lettuce.BasicUsage;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisStringCommands;

public class BasicDataTypeUsage {
	
	RedisClient client;
	StatefulRedisConnection<String, String> connection;

	public static void main(String[] args) throws Exception{
		BasicDataTypeUsage basicDataTypeUsage = new BasicDataTypeUsage();
		basicDataTypeUsage.getConnection();
		basicDataTypeUsage.stringManipulation();
		basicDataTypeUsage.increment();
		basicDataTypeUsage.expire();
		
		basicDataTypeUsage.shutDown();
		
		
	}
	private void expire() throws Exception {
		RedisStringCommands command = connection.sync();
		command.setex("key3", 3, "value3");//value in seconds
		System.out.println("expire #"+command.get("key3"));
		Thread.sleep(4000);//value in miliseconds 
		System.out.println("expire #"+command.get("key3"));
		
	}
	private void increment() {
		RedisStringCommands command = connection.sync();
		command.set("key2", "4");
		System.out.println("Increment #"+command.incr("key2"));
		
	}
	
	private void stringManipulation(){
		RedisStringCommands command = connection.sync();
		command.set("key1", "value1");
		System.out.println("Get #"+command.get("key1"));
		
		command.append("key1", "value2");
		System.out.println("Append #"+command.get("key1"));
		
		System.out.println("range #"+command.getrange("key1", 0, 2));
		System.out.println("String length #"+command.strlen("key1"));
		
		
		
	}
	
	void getConnection(){
		client = RedisClient.create("redis://localhost");
		connection = client.connect();
	}
	private void shutDown() {
		connection.close();
		client.shutdown();
		
	}

}
