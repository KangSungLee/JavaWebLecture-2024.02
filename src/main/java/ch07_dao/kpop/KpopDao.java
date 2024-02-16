package ch07_dao.kpop;

import java.util.List;

public interface KpopDao {

	List<Kpop> getKpopList();

	Artist getArtist(int aid);

	Song getSong(int sid);

	void isertArtist(Artist artist);

	void isertSong(Song song);

	void updateArtist(Artist artist);

	void updateSong(Song song);

	void deleteSong(int sid);
	
	void deleteArtist(int aid);
}
