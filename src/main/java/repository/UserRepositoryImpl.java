package repository;

import model.Advert;
import model.User;

import java.util.List;

public interface UserRepositoryImpl {
    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    List<Advert> findAllByUserId(Long id);
}
