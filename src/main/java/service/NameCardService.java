package service;

import java.util.List;

import beans.NameCard;

public interface NameCardService {
	
	NameCard getOneCard(int id);
	
	boolean addOneCard(String jsonCard);
	
	boolean deleteOneCard(int id);
	
	boolean modifyOneCard(String jsonCard);
	
	int countCards();
	
	List<NameCard> getAllCards();
		
		
}
