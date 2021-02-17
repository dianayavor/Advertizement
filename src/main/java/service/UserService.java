package service;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.CrudRepository;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.List;

public class UserService {
    private final Logger logger = LogManager.getLogger(UserService.class);

    private final CrudRepository<User> crudUserRepository = new UserRepository();

    private final UserRepositoryImpl userRepository = new UserRepository();

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public User update(User user) {
        return crudUserRepository.update(user);
    }

    public boolean delete(Long id) {
        crudUserRepository.delete(id);
        return crudUserRepository.findById(id) == null;
    }

    public User findById(Long id) {
        return crudUserRepository.findById(id);
    }

    public List<User> findAll() {
        return crudUserRepository.findAll();
    }

    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
