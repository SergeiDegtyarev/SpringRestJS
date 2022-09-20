package ru.kata.spring.rest.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.rest.dao.RoleDao;
import ru.kata.spring.rest.dao.UserDao;
import ru.kata.spring.rest.model.User;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private PasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserDao userDao, RoleDao roleDao, @Lazy PasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }
    private void encodePassword(User user) {
       if(user.getPassword().equals("")){
          user.setPassword(findUserById(user.getId()).getPassword());
       } else {
          user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      }

    }



    @Transactional
    @Override
    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        encodePassword(user);
        user.setRoles(roleDao.getRolesSet(user.getRoles()));
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        user.setRoles(roleDao.getRolesSet(user.getRoles()));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        encodePassword(user);
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long id) {
        Optional<User> user = userDao.findUserById(id);
        return user.orElseThrow(() -> new RuntimeException("User by id = " + id + " not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findByUsername(name);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return user;
    }

    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
