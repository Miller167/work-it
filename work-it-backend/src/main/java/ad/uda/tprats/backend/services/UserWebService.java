package ad.uda.tprats.backend.services;

import ad.uda.tprats.backend.filter.FilterUser;
import ad.uda.tprats.backend.repositories.UserWebRepository;
import ad.uda.tprats.workitdata.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class UserWebService {

	@Autowired
    UserWebRepository userWebRepository;

	public List<User> findAll() {
		return userWebRepository.findAll();
	}

    public User findById(Long id) {
        return userWebRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found: " + id.toString()));
    }
    
    public void saveUser(User user) {
        User oldUser = userWebRepository.findById(user.getId());
        user.setPassword(oldUser.getPassword());
        userWebRepository.save(user);
    }
    
    public DataTablesOutput<User> findAll(@Valid DataTablesInput input) {

        return userWebRepository.findAll(input, new FilterUser(input));
    }
    
    public boolean deleteUser(Long id) {

        User user = findById(id);
        if (user != null) {
            userWebRepository.delete(user);
            return true;
        }
        return false;
    }
}
