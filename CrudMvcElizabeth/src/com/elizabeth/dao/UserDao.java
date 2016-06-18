package com.elizabeth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.elizabeth.model.entities.User;
import com.elizabeth.util.DbUtil;

public class UserDao {

	private Connection connection;

	public UserDao() {
		connection = DbUtil.getConnection();
	}

	public void addUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into users(firstname,lastname,dob,email,password,login) values (?, ?, ?, ?, ?,?)");
			// Parameters start with 1
            int i = 1 ;
			preparedStatement.setString(i++, user.getFirstName());
			preparedStatement.setString(i++, user.getLastName());
			preparedStatement.setDate(i++, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(i++, user.getEmail());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getLogin());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int userId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from users where userid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?,login=?,password=?" +
							"where userid=?");
			// Parameters start with 1
            int i = 1;
			preparedStatement.setString(i++, user.getFirstName());
			preparedStatement.setString(i++, user.getLastName());
			preparedStatement.setDate(i++, new java.sql.Date(user.getDob().getTime()));
			preparedStatement.setString(i++, user.getEmail());
            preparedStatement.setString(i++, user.getLogin());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setInt(i++, user.getUserid());
            preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from users");
			while (rs.next()) {
				User user = getUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	private User getUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserid(rs.getInt("userid"));
		user.setFirstName(rs.getString("firstname"));
		user.setLastName(rs.getString("lastname"));
		user.setDob(rs.getDate("dob"));
		user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		return user;
	}

	public User getUserById(int userId) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from users where userid=?");
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean login(String login, String password) {

		if(login == null && password == null){
            return false;
        }else if(login.equalsIgnoreCase("admin") && password.length() == 6){
            return true;
        }
		return false;
	}
}
