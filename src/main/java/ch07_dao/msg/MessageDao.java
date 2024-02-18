package ch07_dao.msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MessageDao implements MessageService {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public Message getMessageByMid(int mid) {
		Connection conn = getConnection();
		String sql = "select * from message where mid=?";
		Message msg = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				msg = new Message(rs.getInt(1), rs.getString(2), rs.getString(3),
						LocalDateTime.parse(rs.getString(4).replace(" ", "T")), rs.getInt(5));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	@Override
	public List<Message> getMessageList() {
		Connection conn = getConnection();
		String sql ="SELECT * FROM message WHERE isDeleted=0 LIMIT 30 OFFSET 0";
		List<Message> list = new ArrayList<>();
		try {
			Statement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(new Message(rs.getInt(1), rs.getString(2), rs.getString(3), 
						LocalDateTime.parse(rs.getString(4).replace(" ", "T")), rs.getInt(5)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Message> getMessageListBySearch(String searchList, String search) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM message WHERE " +  searchList + " like ? and isDeleted=0 LIMIT 30 OFFSET 0";
		List<Message> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Message(rs.getInt(1), rs.getString(2), rs.getString(3), LocalDateTime.parse(rs.getString(4).replace(" ", "T")), rs.getInt(5)));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public void insertMessage(Message message) {
		Connection conn = getConnection();
		String sql ="insert into message values(default, ?, ?, default, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getContent());
			pstmt.setString(2, message.getWriter());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMessage(Message message) {
		Connection conn = getConnection();
		String sql ="update message set content=?, writer=?, modTime=now() where mid=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getContent());
			pstmt.setString(2, message.getWriter());
			pstmt.setInt(3, message.getMid());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMessage(int mid) {
		Connection conn = getConnection();
		String sql ="update message set isDeleted=1 where mid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
