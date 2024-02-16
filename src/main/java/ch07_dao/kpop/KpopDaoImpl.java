package ch07_dao.kpop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ch07_dao.City;

public class KpopDaoImpl implements KpopDao{
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
	public List<Kpop> getKpopList() {
		Connection conn = getConnection();
		String sql = "select l.gid, l.name, l.debut, l.hit_song_id, r.title, r.lyrics from girl_group l"
				+ " JOIN song r"
				+ " ON l.hit_song_id=r.SID"
				+ " LIMIT 30 OFFSET 0";
		List<Kpop> list = new ArrayList<Kpop>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Kpop(rs.getInt(1), rs.getString(2), LocalDate.parse(rs.getString(3)), 
						rs.getInt(4), rs.getString(5), rs.getString(6)));
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
	public Artist getArtist(int aid) {
		Connection conn = getConnection();
		String sql = "select * from girl_group where gid=?";
		Artist artist = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				artist = new Artist(rs.getInt(1), rs.getString(2), 
						LocalDate.parse(rs.getString(3)), rs.getInt(4));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artist;
	}

	@Override
	public Song getSong(int sid) {
		Connection conn = getConnection();
		String sql = "select * from song where sid=?";
		Song song = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				song = new Song(rs.getInt(1), rs.getString(2), 
						rs.getString(3));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return song;
	}

	@Override
	public void isertArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "insert into girl_group values (default, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artist.getName());
			pstmt.setString(2, artist.getDebut().toString());
			pstmt.setInt(3, artist.getHitSongId());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void isertSong(Song song) {
		Connection conn = getConnection();
		String sql = "insert into song values (default, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateArtist(Artist artist) {
		Connection conn = getConnection();
		String sql = "UPDATE girl_group SET Name=?, hit_Song_Id=?, Debut=? where gid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, artist.getName());
			pstmt.setInt(2, artist.getHitSongId());
			pstmt.setString(3, artist.getDebut().toString());
			pstmt.setInt(4, artist.getAid());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateSong(Song song) {
		Connection conn = getConnection();
		String sql = "UPDATE song SET title=?, lyrics=? where sid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song.getTitle());
			pstmt.setString(2, song.getLyrics());
			pstmt.setInt(3, song.getSid());

			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteArtist(int aid) {
		Connection conn = getConnection();
		String sql = "delete from girl_group where gid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteSong(int sid) {
		Connection conn = getConnection();
		String sql = "delete from song where sid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
