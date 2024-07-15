package com.example.lms.login;

import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import com.example.lms.user.UserRepository;

public class CrudUserDetailsManager implements UserDetailsManager {
  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.example.lms.user.User user =
        userRepository.findById(username).orElseThrow(new Supplier<UsernameNotFoundException>() {

          @Override
          public UsernameNotFoundException get() {
            return new UsernameNotFoundException("ユーザが見つかりませんでした");
          }

        });
    UserDetails userDetails = User.withUsername(user.getName()).password(user.getPassword())
        .roles(user.getPermission().getName()).build();
    return userDetails;
  }

  @Override
  public void createUser(UserDetails user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
  }

  @Override
  public void updateUser(UserDetails user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
  }

  @Override
  public void deleteUser(String username) {
    userRepository.deleteById(username);
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'changePassword'");
  }

  @Override
  public boolean userExists(String username) {
    return userRepository.existsById(username);
  }

}
