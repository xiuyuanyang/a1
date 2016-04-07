package dao;

import java.util.List;

import beans.NameCard;

public interface NameCardDao {

	NameCard getNameCard(int id);

	boolean addNameCard(String jsonCard);

	boolean deleteNameCard(int id);

	boolean updateNameCard(String jsonCard);

	int countCards();

	List<NameCard> getNameCards();

}
