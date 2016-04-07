package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import beans.NameCard;
import dao.NameCardDao;

@Transactional
@Service("nameCardService")
public class NameCardServiceImp implements NameCardService{
	
	@Autowired
	private NameCardDao nameCardDao;

	public NameCard getOneCard(int id) {

		return nameCardDao.getNameCard(id);
	}

	public boolean addOneCard(String jsonCard) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteOneCard(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean modifyOneCard(String jsonCard) {
		// TODO Auto-generated method stub
		return false;
	}

	public int countCards() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<NameCard> getAllCards() {
		// TODO Auto-generated method stub
		return null;
	}

}
