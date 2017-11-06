package toptier.manager.user;

import toptier.model.user.User;

public interface IUserManager {

    // register the given user and return its id
    String registerUser(User user);

    // return the user with the given id, or null if non-existent
    User getUserById(String id);

    // logout the signed-in user and return whether or not operation was successful
    boolean logoutUser(String id);
}
