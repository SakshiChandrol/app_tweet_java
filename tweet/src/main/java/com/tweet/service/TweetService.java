package com.tweet.service;

import java.util.List;


public interface TweetService {

	public abstract boolean register(List<String> user);
	   public abstract boolean login(List<String> login);
	   public abstract boolean forgotPassword(List<String> reset);
	   public abstract boolean postTweet(List<String> login);
	   public abstract List<String> getMyTweet();
	   public abstract List<String> getAllTweet();
	   public abstract List<String> getAllUser();
	   public abstract boolean reset(List<String> reset);   
}
