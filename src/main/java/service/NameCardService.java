package service;

import java.util.List;

import beans.NameCard;

public interface NameCardService {
	
	NameCard getOneCard(int id , int uid);
	
	boolean addOneCard(NameCard jsonCard);
	
	boolean deleteOneCard(int id , int uid);
	
	boolean modifyOneCard(NameCard jsonCard);
	
	List<Integer> countCards(int uid);
	
	List<NameCard> getAllCards(int uid);
		
		
}
