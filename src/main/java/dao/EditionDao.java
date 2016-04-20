package dao;

import beans.Edition;

public interface EditionDao {

	Edition getLatestEdition();
	
	Edition getEditonByVersion(String version);
	
	int addEdition(Edition e);
	
	int removeEdition(Edition e);
	
}
