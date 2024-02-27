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

import project.entity.User;

public class AuctionsDao {
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

	public void insertAuctions(Auctions auctions) {
		Connection conn = getConnection();
		String modTime = LocalDateTime.now().plusDays(3).toString().substring(0, 19).replace(" ", "T");
		String sql = "insert auctions values (default, ?, ?, ?, ?, '" + modTime  + "', ?, default)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, auctions.getEquipment_id());
			pstmt.setString(2, auctions.getTitle());
			pstmt.setInt(3, auctions.getStart_price());
			pstmt.setInt(4, auctions.getStart_price());
			pstmt.setString(5, auctions.getContent());
			
			pstmt.executeUpdate();
			pstmt.close();conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	public int getAuctionsCount() {
		Connection conn = getConnection();
		String sql = "select count(auction_id) from auctions";
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
	
	//
	public List<Auctions> getAuctionsList(int num, int offset) {
		Connection conn = getConnection();
		String sql = "select * from auctions order BY end_time desc limit ? offset ?";
		List<Auctions> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, offset);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Auctions auctions = new Auctions(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getInt(5), LocalDateTime.parse(rs.getString(6).replace(" ", "T")),
						rs.getString(7), rs.getString(8));
				list.add(auctions);
			}
			rs.close(); pstmt.close();conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
