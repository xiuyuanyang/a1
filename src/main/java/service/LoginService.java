package service;

import beans.User;

public interface LoginService {

    boolean isRegistered(User user);

    boolean addUser(User user);

    User getLogin(User user);
}
