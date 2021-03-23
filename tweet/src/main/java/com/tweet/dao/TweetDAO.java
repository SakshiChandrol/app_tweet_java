package com.tweet.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.tweet.bean.Tweet;
import com.tweet.bean.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class TweetDAO {
	static Connection con;
	String season = null;
	Object obb=null;
	 static String driverclass;
	static String url;
	static String username;
	static String password;
	static String loggedInUser;
	public String currentUser;
	public static Properties loadPropertiesFile() throws Exception {
		//C:/Users/User/eclipse-workspace/tweet/
		Properties property = new Properties();
		InputStream file = new FileInputStream("src/main/resources/db.properties");
		property.load(file);
		file.close();
		return property;
	}
	public static Connection createConnection() {
	
    	try{
    		Properties property = loadPropertiesFile();
    		driverclass = property.getProperty("dname");
			url = property.getProperty("url");
			username = property.getProperty("username");
			password = property.getProperty("password");
			Class.forName(driverclass);
			con = DriverManager.getConnection(url, username, password);
    	}catch(Exception e){
    		System.out.println("We are sorry your request cannot be processed right now");
    	}
    return con;	
	}
   
    public boolean registerUser(User user) {
    	boolean result=false;
    	try {
    		Connection con = createConnection();
    		String query = "insert into tweetserver.user(firstname,lastname,gender,dob,email,password) values (?,?,?,?,?,?)";
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1, user.getFirstName());
			pstm.setString(2, user.getLastName());
			pstm.setString(3, user.getGender());
			pstm.setString (4, user.getDob());
			pstm.setString(5, user.getEmail());
			pstm.setString(6, user.getPassword());
			pstm.executeUpdate();
			result = true;
    	}
    	catch(SQLException e1) {
        	System.out.println("Dear user you entered incorrect value. please enter correct values");
        } catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("We are sorry your request cannot be processed right now");
    	} finally {
        	try {
        		if(con != null) {
        			con.close();
        		}
        	}
        		catch(Exception e) {
        			e.printStackTrace();
        			System.out.println("We are sorry your request cannot be processed right now");
        		}
    	}
    	return result;
    }
	public boolean login(User user) {
		boolean result=false;
    	try {
    		Connection con = createConnection();
    		String query = "select firstname from tweetserver.user where user.email= ? and user.password = ?";
    		PreparedStatement pstm=con.prepareStatement(query);
    		pstm.setString (1, user.getEmail());
			pstm.setString(2, user.getPassword());
			loggedInUser=user.getEmail();
			ResultSet rs=pstm.executeQuery();
			while(rs.next()){  
    			currentUser=rs.getString(1);  
    			} 
			if(currentUser==null) {
				result = false;
			}
			else
			result = true;
    	}
		// TODO Auto-generated method stub
			catch(SQLException e1) {
				System.out.println("Dear user you entered incorrect value. please enter correct values");
	        } catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("We are sorry your request cannot be processed right now");
	    	} finally {
	        	try {
	        		if(con != null) {
	        			con.close();
	        		}
	        	}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        			System.out.println("We are sorry your request cannot be processed right now");
	        		}
	    	}
	    	return result;
	    }
	public boolean forgotPassword(User user) {
		boolean result=false;
    	try {
    		System.out.println(user.getEmail());
    		System.out.println(user.getPassword());
    		Connection con = createConnection();
    		String query = "update tweetserver.user set password=? where email=?";
    		PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1, user.getPassword());
			pstm.setString (2, user.getEmail());
			pstm.executeUpdate();
			result = true;
    	}
		// TODO Auto-generated method stub
			catch(SQLException e1) {
	        	System.out.println(e1.getClass().getName() + ": " +e1.getMessage());
	        } catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("We are sorry your request cannot be processed right now");
	    	} finally {
	        	try {
	        		if(con != null) {
	        			con.close();
	        		}
	        	}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        			System.out.println("We are sorry your request cannot be processed right now");
	        		}
	    	}
	    	return result;
	}
	public boolean postTweet(Tweet tweet) {
		// TODO Auto-generated method stub
		boolean result=false;
    	try {
    		Connection con = createConnection();
    		String query = "insert into tweetserver.tweetpost(tweet,email) values (?,?)";
			PreparedStatement pstm=con.prepareStatement(query);
			pstm.setString(1, tweet.getTweet());
			pstm.setString(2,loggedInUser );
			pstm.executeUpdate();
			result = true;
    	}
    	catch(SQLException e1) {
    		System.out.println("Dear user you entered incorrect value. please enter correct values");
        } catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("We are sorry your request cannot be processed right now");
    	} finally {
        	try {
        		if(con != null) {
        			con.close();
        		}
        	}
        		catch(Exception e) {
        			e.printStackTrace();
        			System.out.println("We are sorry your request cannot be processed right now");
        		}
    	}
    	return result;
    }
	public List<String> getMyTweet() {
		List<String> myTweet = new ArrayList<String>();
    	try {
    		Connection con = createConnection();
    		String query = "select tweet from tweetserver.tweetpost where tweetpost.email= ? ";
    		PreparedStatement pstm=con.prepareStatement(query);
    		pstm.setString (1, loggedInUser);
    		ResultSet rs=pstm.executeQuery();
    		while(rs.next()){  
    			myTweet.add(rs.getString(1));  
    			}  
    	}
		// TODO Auto-generated method stub
			catch(SQLException e1) {
				System.out.println("Dear user you entered incorrect value. please enter correct values");
	        } catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("We are sorry your request cannot be processed right now");
	    	} finally {
	        	try {
	        		if(con != null) {
	        			con.close();
	        		}
	        	}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        			System.out.println("We are sorry your request cannot be processed right now");
	        		}
	    	}
	    	return myTweet;
	}
	public List<String> getAllTweet() {
		// TODO Auto-generated method stub
		List<String> myTweet = new ArrayList<String>();
    	try {
    		Connection con = createConnection();
    		String query = "select tweet from tweetserver.tweetpost";
    		PreparedStatement pstm=con.prepareStatement(query);
    		ResultSet rs=pstm.executeQuery();
    		while(rs.next()){  
    			myTweet.add(rs.getString(1));  
    			}  
    	}
		// TODO Auto-generated method stub
			catch(SQLException e1) {
				System.out.println("Dear user you entered incorrect value. please enter correct values");
	        } catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("We are sorry your request cannot be processed right now");
	    	} finally {
	        	try {
	        		if(con != null) {
	        			con.close();
	        		}
	        	}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        			System.out.println("We are sorry your request cannot be processed right now");
	        		}
	    	}
	    	return myTweet;
	}
	public List<String> getAllUser() {
		List<String> myTweet = new ArrayList<String>();
    	try {
    		Connection con = createConnection();
    		String query = "select firstname from tweetserver.user";
    		PreparedStatement pstm=con.prepareStatement(query);
    		ResultSet rs=pstm.executeQuery();
    		while(rs.next()){  
    			myTweet.add(rs.getString(1));  
    			}  
    	}
		// TODO Auto-generated method stub
			catch(SQLException e1) {
				System.out.println("Dear user you entered incorrect value. please enter correct values");
	        } catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("We are sorry your request cannot be processed right now");
	    	} finally {
	        	try {
	        		if(con != null) {
	        			con.close();
	        		}
	        	}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        			System.out.println("We are sorry your request cannot be processed right now");
	        		}
	    	}
	    	return myTweet;
	}
	public boolean reset(User user, String newPassword) {
		boolean result=false;

    	try {
    		System.out.println(user.getEmail());
    		System.out.println(user.getPassword());
    		Connection con = createConnection();
    		String query = "update tweetserver.user set password=? where email=? and password=?";
    		PreparedStatement pstm=con.prepareStatement(query);
    		pstm.setString(1, newPassword);
    		pstm.setString (2, user.getEmail());
			pstm.setString(3, user.getPassword());
			pstm.executeUpdate();
			result = true;
    	}
		// TODO Auto-generated method stub
			catch(SQLException e1) {
				System.out.println("Dear user you entered incorrect value. please enter correct values");
	        } catch (Exception e) {
	    		e.printStackTrace();
	    		System.out.println("We are sorry your request cannot be processed right now");
	    	} finally {
	        	try {
	        		if(con != null) {
	        			con.close();
	        		}
	        	}
	        		catch(Exception e) {
	        			e.printStackTrace();
	        			System.out.println("We are sorry your request cannot be processed right now");
	        		}
	    	}
	    	return result;
	}
	}
    
    	
  

