package com.zikozee.sprinboot.consumingwebservices.jwt;

import com.zikozee.sprinboot.consumingwebservices.DatabaseEntity.Users;
import com.zikozee.sprinboot.consumingwebservices.Service.UserRepositoryService;
import com.zikozee.sprinboot.consumingwebservices.repository.AuthorityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class JwtDatabaseUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> dataBaseUserList = new ArrayList<>();
  private UserRepositoryService userRepositoryService;
  private AuthorityRepository repository;

  JwtDatabaseUserDetailsService(UserRepositoryService userRepositoryService, AuthorityRepository repository) {
    this.userRepositoryService = userRepositoryService;
    this.repository = repository;
  }

//  static {
//
//    dataBaseUserList.add(new JwtUserDetails(1L, "in28minutes",
//            "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
//    dataBaseUserList.add(new JwtUserDetails(2L, "zikozee",
//            "$2a$10$vYEOK0uVzOwAzmGGpivaEO1.XPiYhp4sVYR/DHWLAsCiazE8Fg/UC", "ROLE_USER_2"));// Bcrypted password
//  }

  private void loadUsers(){
    for(Users user: userRepositoryService.findAll()){
      String authority = repository.findByUsername(user.getUsername()).getAuthority();
      dataBaseUserList.add(new JwtUserDetails(user.getId(), user.getUsername(), user.getPasswordHash(), authority));
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    loadUsers();
    Optional<JwtUserDetails> findFirst = dataBaseUserList.stream()
            .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException("USER_NOT_FOUND " + username);
    }

    return findFirst.get();
  }

}

