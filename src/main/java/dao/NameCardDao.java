package dao;

import java.util.List;

import beans.NameCard;

public interface NameCardDao {

	NameCard getNameCard(int id, int uid);

	int addNameCard(NameCard jsonCard);

	int deleteNameCard(int id , int uid);

	int updateNameCard(NameCard jsonCard);

	List<Integer> countCards(int uid);

	List<NameCard> getNameCards(int uid);

}
