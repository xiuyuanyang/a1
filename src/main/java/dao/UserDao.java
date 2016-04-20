package dao;

import java.util.List;

import beans.User;

public interface UserDao {
	User findUserById(int uid);
	
	User findUserByName(String name);

	User findUserByMobile(String mobile);
	
	User findUserByMobileAndPassword(User user);
	
	int addUser(User user);

	int deleteUser(User user);

	int updateUser(User user);

	int countUsers();

	List<User> findAllUsers();
}
