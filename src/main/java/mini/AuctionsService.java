package mini;

import java.util.List;

import project.entity.User;

public interface AuctionsService {
	public static final int COUNT_PER_PAGE = 10;

	void insertAuctions(Auctions auctions);
	
	int getAuctionsCount();
	
	List<Auctions> getAuctionsList(int page);
}
