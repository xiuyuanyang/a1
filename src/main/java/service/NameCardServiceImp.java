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

	public NameCard getOneCard(String id , int uid) {

		return nameCardDao.getNameCard(id , uid);
	}

	public boolean addOneCard(NameCard jsonCard) {
		int r=0;
		try{
			r = nameCardDao.addNameCard(jsonCard);
		}
		catch(org.springframework.dao.DuplicateKeyException e){
			e.printStackTrace();
		}
		System.out.println(r);
		return r!=0?true:false;
	}

	public boolean deleteOneCard(int id , int uid) {

		int r = nameCardDao.deleteNameCard(id, uid);
		System.out.println(r);		
		return r!=0?true:false;
	}

	public boolean modifyOneCard(NameCard jsonCard) {
		
		int r = nameCardDao.updateNameCard(jsonCard);
		System.out.println(r);	
		return r!=0?true:false;
	}

	public List<Integer> countCards(int uid) {
		return nameCardDao.countCards(uid);
	}

	public List<NameCard> getAllCards(int uid) {
		return nameCardDao.getNameCards(uid);
	}

	@Override
	public boolean deleteAllCard(int uid) {
		int r = nameCardDao.deleteAllCard(uid);
		System.out.println(r);		
		return r!=0?true:false;
	}

	
}
