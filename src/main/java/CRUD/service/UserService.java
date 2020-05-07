package CRUD.service;

import CRUD.dao.UserDAO;
import CRUD.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {



    private UserDAO userDao;

    @Autowired
    public UserService(UserDAO userDao) {
        this.userDao=userDao;
    }


    public List<User> getAll(){
        return userDao.getAll();
    }


    public void delete(User user){
        userDao.delete(user);
    }


    public void add(User user){
        userDao.add(user);
    }


    public void update(User user){
        userDao.update(user);
    }

    public User getbyID(int id){
        return userDao.getbyID(id);
    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User byLogin = userDao.findByUsername(s);

        if (byLogin==null) {
            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }

        return byLogin;
    }
}
