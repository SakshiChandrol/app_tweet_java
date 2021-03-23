package com.tweet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tweet.bean.Tweet;
import com.tweet.bean.User;
import com.tweet.dao.TweetDAO;

public class TweetServiceImpl implements TweetService{
	
	static TweetDAO tweetDAO = new TweetDAO();
	 User userBean = new User();
	 Tweet tweetBean =new Tweet();

	@Override
	public boolean register(List<String> user) {
		// TODO Auto-generated method stub
		String value;
		String s1=user.get(3);
		String email =user.get(4) ;
		String password=user.get(5);
		boolean invalidValue;
		Date d1 = null;
		boolean result=false;
		for(int i =0;i<3;i++) {
			value = user.get(i);
			invalidValue = value.matches("[a-zA-Z]+");
			if(!invalidValue) {
				System.out.println("You have entered value containing characters other the Alphabet "+value+" Please enter correct Value");
				result=false;
				return result;
			}
		}
		
		 result = emailValidator(email);
		 if(!result) {
			 System.out.println("You Have enterred incorrect email address please enter again");
			 return result;
		 }
		 
		 result = passwordValidator(password);
		 if(!result) {
				System.out.println("Your password should have 6 to 20 charaters and Must contain at least one digit and Must contain at least one of the following special characters @, #, $");
			 return result;
		 }
		
		if(s1.matches("[0-9]{2}[-]{1}[0-9]{2}[-]{1}[0-9]{4}"))
		{
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		try {
		d1=sdf.parse(s1);
		result = true;
		} catch (ParseException e) {
			result = false;
			System.out.println("You have enterred date of birth in incorrect Format");
			return result;
		}
		}
		else {
			result = false;
			System.out.println("You have enterred date of birth in incorrect Format");
			return result;
		}
		if(result) {
			  userBean.setFirstName(user.get(0));
			  userBean.setLastName(user.get(1));
			  userBean.setGender(user.get(2));
			  userBean.setDob(s1);
			  userBean.setEmail(email);
			  userBean.setPassword(password);
			  result=tweetDAO.registerUser(userBean);	 
		}
		
		return result;
	}


	@Override
	public boolean login(List<String> user) {
		boolean result= false;
		String email =user.get(0) ;
		String password=user.get(1);
		result = emailValidator(email);
		 if(!result) {
			 System.out.println("You Have enterred incorrect email address please enter again");
			 return result;
		 }
		 
		 result = passwordValidator(password);
		 if(!result) {
				System.out.println("Your password should have 6 to 20 charaters and Must contain at least one digit and Must contain at least one of the following special characters @, #, $");
			 return result;
		 }
		 if(result) {
			 userBean.setEmail(email);
			 userBean.setPassword(password);
			 result=tweetDAO.login(userBean);
		 }
		
		return result;
	}

	@Override
	public boolean forgotPassword(List<String> user) {
		boolean result= false;
		String email =user.get(0) ;
		String password=user.get(1);
		result = emailValidator(email);
		 if(!result) {
			 System.out.println("You Have enterred incorrect email address please enter again");
			 return result;
		 }
		 
		 result = passwordValidator(password);
		 if(!result) {
				System.out.println("Your password should have 6 to 20 charaters and Must contain at least one digit and Must contain at least one of the following special characters @, #, $");
			 return result;
		 }
		 if(result) {
			 userBean.setEmail(email);
			 userBean.setPassword(password);
			 result=tweetDAO.forgotPassword(userBean);
		 }
		
		return result;
	}

	@Override
	public boolean postTweet(List<String> login) {
		// TODO Auto-generated method stub
		tweetBean.setTweet(login.get(0));
		boolean result= false;
		result=tweetDAO.postTweet(tweetBean);
		return true;
	}

	@Override
	public List<String> getMyTweet() {
		// TODO Auto-generated method stub
		List<String> myTweet = new ArrayList<String>();
		myTweet=tweetDAO.getMyTweet();
		return myTweet;
	}

	@Override
	public List<String> getAllTweet() {
		// TODO Auto-generated method stub
		List<String> myTweet = new ArrayList<String>();
		myTweet=tweetDAO.getAllTweet();
		return myTweet;
	}

	@Override
	public List<String> getAllUser() {
		// TODO Auto-generated method stub
		List<String> myTweet = new ArrayList<String>();
		myTweet=tweetDAO.getAllUser();
		return myTweet;
	}

	@Override
	public boolean reset(List<String> reset) {
		boolean result= false;
		String email =reset.get(0) ;
		String password=reset.get(1);
		String newPassword=reset.get(2);
		result = emailValidator(email);
		 if(!result) {
			 System.out.println("You Have enterred incorrect email address please enter again");
			 return result;
		 }
		 
		 result = passwordValidator(password);
		 if(!result) {
				System.out.println("Your password should have 6 to 20 charaters and Must contain at least one digit and Must contain at least one of the following special characters @, #, $");
			 return result;
		 }
		 if(result) {
			 userBean.setEmail(email);
			 userBean.setPassword(password);
			 result=tweetDAO.reset(userBean,newPassword );
		 }
		
		return result;
	}
	
	boolean emailValidator(String email) {
		String regex = "^(.+)@(.+)$";
		boolean result = true;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches())
		{
		result = false;
		}
		return result;
}
	boolean passwordValidator(String password) {
		boolean result = false;
		if(password.matches(".*[0-9]{1,}.*") && password.matches(".*[@#$]{1,}.*") && password.length()>=6 && password.length()<=20)
        {
			result = true;
        }
        else
        {
        	result = false;
			
        }
		return result;
	}
	
	public String getCurrentUser() {
		return tweetDAO.currentUser;
	}
}