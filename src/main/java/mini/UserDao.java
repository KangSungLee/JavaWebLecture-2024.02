package mini;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDao {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/auction_db");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public User getUserByUid(String uid) {
		Connection conn = getConnection();
		String sql = "select * from users where user_id=?";
		User user = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5),LocalDateTime.parse(rs.getString(6).replace(" ", "T")), rs.getInt(7));
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getUserList(int num, int offset) {
		Connection conn = getConnection();
		String sql = "select * from users where isDeleted=0"
					+ " order by regDate desc, uid limit ? offset ?";
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5),LocalDateTime.parse(rs.getString(6).replace(" ", "T")),rs.getInt(7));
				list.add(user);
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertUser(User user) {
		Connection conn = getConnection();
		String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUser_id());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPhone_number());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		Connection conn = getConnection();
		String sql = "UPDATE users SET password=?, username=?, email=?, phone_number=?   WHERE user_id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhone_number());
            pstmt.setString(5, user.getUser_id());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
		}
	}
	
	public void deleteUser(String uid) {
		Connection conn = getConnection();
		String sql = "UPDATE users SET isDeleted=1 WHERE user_id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uid);

            pstmt.executeUpdate(); pstmt.close(); conn.close();
        } catch (Exception e) {
            e.printStackTrace();
		}
	}	
	
	public int getUserCount() {
		Connection conn = getConnection();
		String sql = "SELECT COUNT(user_id) FROM users WHERE isDeleted=0";
		int count = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close(); stmt.close(); conn.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
