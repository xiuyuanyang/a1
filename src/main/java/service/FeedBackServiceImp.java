package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beans.FeedBack;
import dao.FeedBackDao;

@Transactional
@Service("FeedBackService")
public class FeedBackServiceImp implements FeedBackService{

    @Autowired
    private FeedBackDao feedbackMapper;
	
	@Override
	public int addFeedBack(FeedBack fb) {
		
		return feedbackMapper.addFeedBack(fb);
	}

	@Override
	public FeedBack getFeedBack() {
		// TODO Auto-generated method stub
		return feedbackMapper.getFeedBack();
	}

}
