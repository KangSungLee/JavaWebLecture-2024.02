package mini;

import java.util.List;

public class AuctionsServiceImpl implements AuctionsService {
	private AuctionsDao aDao = new AuctionsDao();

	@Override
	public void insertAuctions(Auctions auctions) {
		aDao.insertAuctions(auctions);
	}

	@Override
	public int getAuctionsCount() {
		return aDao.getAuctionsCount();
	}

	@Override
	public List<Auctions> getAuctionsList(int page) {
		int offset = (page - 1) * COUNT_PER_PAGE;
		List<Auctions> list = aDao.getAuctionsList(COUNT_PER_PAGE, offset);
		return list;
	}
	
	
}
