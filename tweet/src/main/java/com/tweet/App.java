package com.tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tweet.service.TweetService;
import com.tweet.service.TweetServiceImpl;

public class App 
{
	 static TweetServiceImpl tweetService = new TweetServiceImpl();
	
    public static void main( String[] args ) throws IOException
    {
    	String option= null;
    	String value= null;
    	int selection=0;
    	
    	boolean isLoggedIn=false;
    	boolean forgotPwd = false;
    	boolean tweetPost=false;
    	boolean resetPwd=false;
    	boolean result = false;
    	List<String> user = new ArrayList<String>();
    	List<String> login = new ArrayList<String>();
    	List<String> reset = new ArrayList<String>();
    	List<String> resultList = new ArrayList<String>();
    	InputStreamReader input = new InputStreamReader(System.in);
    	BufferedReader reader = new BufferedReader(input); 
    	
		
		try { labelf :  do{
			System.out.println( "Welcome to Tweet App please select a option below" );
		    System.out.println("1. Register");
		    System.out.println("2. Login");
		    System.out.println("3. Forgot Password");
		    selection =Integer.parseInt(reader.readLine());
		    switch(selection) {
		    case 1:			user.removeAll(user);
		    				System.out.println("Please Enter your Details");
					        System.out.println("Please enter your FirstName");
					        user.add(reader.readLine());
					        System.out.println("Please enter your LastName");
					        user.add(reader.readLine());
					        System.out.println("Please enter your Gender");
					        user.add(reader.readLine());
					        System.out.println("Please enter your Date of Birth(dd-mm-yyyy)");
					        user.add(reader.readLine());
					        System.out.println("Please enter your Email");
					        user.add(reader.readLine());
					        System.out.println("Please enter Password(\"Your password should have 6 to 20 charaters, at least one digit and at least one of the following special characters @, #, $\")");
					        user.add(reader.readLine());
							result = tweetService.register(user);
					        break;
		    case 2 : 		user.removeAll(user);
		    				System.out.println("Please enter your Email");
		    				user.add(reader.readLine());
					        System.out.println("Please enter Password");
					        user.add(reader.readLine());
					        isLoggedIn = tweetService.login(user);
					        if(!isLoggedIn) {
					        	continue labelf;
					        }
					        labelx : while(isLoggedIn) {
						   		 System.out.println( "Hello "+tweetService.getCurrentUser()+" please select an option below" );
						   		    System.out.println("1. Post a tweet");
						   		    
						   		    System.out.println("2. View my tweets");
						   		    
						   		    System.out.println("3. View all tweets");
						   		   
						   		    System.out.println("4. View all users");
						   		    
						   		    System.out.println("5. Reset Password");
						   		    
						   		    System.out.println("6. Logout");
						   		 selection =Integer.parseInt(reader.readLine());
						   		switch(selection) {
							    case 1:		System.out.println("Post a tweet");
							    			login.removeAll(login);
							    			login.add(reader.readLine());
							    			tweetPost = tweetService.postTweet(login);
							    			break;
							    case 2 : 	System.out.println("Your tweets");
							    			resultList = tweetService.getMyTweet();
							    			for(String tweet : resultList)
							    			System.out.println(tweet);
							    			break;
							    case 3 :    System.out.println("All tweet");
							    			resultList=tweetService.getAllTweet();
							    			for(String tweet : resultList)
								    		System.out.println(tweet);						    		
							    			break;
							    case 4 :    System.out.println("All user");
							    			resultList=tweetService.getAllUser();
							    			for(String tweet : resultList)
									    	System.out.println(tweet);
							    			break;
							    case 5 :   	reset.removeAll(reset);
							    			System.out.println("Please enter your Email");
							    			reset.add(reader.readLine());
									        System.out.println("Please enter Password");
									        reset.add(reader.readLine());
									        System.out.println("Please enter your new Password");
							    			reset.add(reader.readLine());
							    			resetPwd= tweetService.reset(reset); 
							    			break;
							    case 6 : 	System.out.println("You have logged out");
							    			continue labelf;
							    default : 	System.out.println("You have entered incorrect option");    
						   } 
							  
							}
		    case 3 :		user.removeAll(user);
		    				System.out.println("Please enter your Email");
		    				user.add(reader.readLine());
					        System.out.println("Please enter your new Password");
					        user.add(reader.readLine());
					        forgotPwd=tweetService.forgotPassword(user);
					        break;
			default : 		System.out.println("You have entered incorrect option");
			
		    }
		   
		 }
		while(selection !=4);
		input.close();
		reader.close();
		
        // Printing the read line 
		}
		
		
		catch(Exception e) {
			System.out.println("Please enter correct value");
			
		}
    }
    
    }

        
    


