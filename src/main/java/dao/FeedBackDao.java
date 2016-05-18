package dao;

import beans.FeedBack;

public interface FeedBackDao {

	int addFeedBack(FeedBack fb);
	
	FeedBack getFeedBack();
	
}
