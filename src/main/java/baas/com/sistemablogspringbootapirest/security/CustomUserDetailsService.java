package baas.com.sistemablogspringbootapirest.security;

import baas.com.sistemablogspringbootapirest.model.Rol;
import baas.com.sistemablogspringbootapirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        baas.com.sistemablogspringbootapirest.model.User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with that userName or email : " + usernameOrEmail));

        return new User(user.getEmail(), user.getPassword(), mapRols(user.getUserRols()));
    }

    private Collection<? extends GrantedAuthority> mapRols(List<Rol> rols){
        return rols.stream().map(rol -> new SimpleGrantedAuthority(rol.getRolName())).collect(Collectors.toList());
    }
}
