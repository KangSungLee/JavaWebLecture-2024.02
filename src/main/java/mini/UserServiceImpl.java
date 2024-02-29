package mini;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDao();

	@Override
	public User getUserByuser_id(String user_id) {
		User user = userDao.getUserByUid(user_id);
		return user;
	}

	@Override
	public List<User> getUserList(int page) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		List<User> list = userDao.getUserList(COUNT_PER_PAGE, offset);
		return list;
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}

	@Override
	public void registerUser(User user) {
		User u = userDao.getUserByUid(user.getUser_id());
		if (u != null)
			return;
		
		String hashedPwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPwd);
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	@Override
	public void deleteUser(String user_id) {
		userDao.deleteUser(user_id);
		
	}

	@Override
	public int login(String user_id, String password) {
		User user = userDao.getUserByUid(user_id);
		if (user == null)
			return USER_NOT_EXIST;
		if (BCrypt.checkpw(password, user.getPassword()))
			return CORRECT_LOGIN;
		return WRONG_PASSWORD;
	}
}

