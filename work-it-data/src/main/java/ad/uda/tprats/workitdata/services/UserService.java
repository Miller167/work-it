package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // CREATE
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // READ
    public User getUserById(Long userId) {
        return userRepository.getById(userId);
    }

    // READ
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // DELETE
    public void deleteUser(Long userId) {userRepository.deleteById(userId);
    }

    // UPDATE
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId).get();
        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setAdministrator(userDetails.isAdministrator());

        return userRepository.save(user);
    }

  /*  // UPDATE ADMIN
    public void updateAdmin(Long userId, Boolean admin) {
        User user = userRepository.findById(userId).get();
        user.setAdministrator(admin);
    }*/
}
