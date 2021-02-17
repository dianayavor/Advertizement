package repository;

import model.User;

public interface UserRepositoryImpl {
    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);
}
