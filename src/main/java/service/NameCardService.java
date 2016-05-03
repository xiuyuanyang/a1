package service;

import java.util.List;

import beans.NameCard;

public interface NameCardService {
	
	NameCard getOneCard(String id , int uid);
	
	boolean addOneCard(NameCard jsonCard);
	
	boolean deleteAllCard(int uid);
	
	boolean modifyOneCard(NameCard jsonCard);
	
	List<Integer> countCards(int uid);
	
	List<NameCard> getAllCards(int uid);
		
		
}
