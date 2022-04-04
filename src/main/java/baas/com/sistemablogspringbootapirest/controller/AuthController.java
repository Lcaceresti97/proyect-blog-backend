package baas.com.sistemablogspringbootapirest.controller;

import baas.com.sistemablogspringbootapirest.dto.LoginDto;
import baas.com.sistemablogspringbootapirest.dto.RegisterDTO;
import baas.com.sistemablogspringbootapirest.dto.RolDto;
import baas.com.sistemablogspringbootapirest.dto.UserDto;
import baas.com.sistemablogspringbootapirest.model.Rol;
import baas.com.sistemablogspringbootapirest.model.User;
import baas.com.sistemablogspringbootapirest.repository.RolRepository;
import baas.com.sistemablogspringbootapirest.repository.UserRepository;
import baas.com.sistemablogspringbootapirest.response.BaseResponse;
import baas.com.sistemablogspringbootapirest.response.Response;
import baas.com.sistemablogspringbootapirest.security.JWTAuthResponseDTO;
import baas.com.sistemablogspringbootapirest.security.JwtTokenProvider;
import baas.com.sistemablogspringbootapirest.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

/**
 * Controller for Auth entity operations.
 * @autor Laurent Cáceres
 * @version 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RolRepository rolRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    private final RolService rolService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticationUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Get el token del jwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);

        return  ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("That username already exists", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(registerDTO.getEmail())){
            return  new ResponseEntity<>("That user email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setId(registerDTO.getId());
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Rol rol = rolRepository.findByRolName("ROLE_ADMIN").get();
        user.setUserRols(Collections.singletonList(rol));

        userRepository.save(user);
        return  new ResponseEntity<>("Successfully registered user", HttpStatus.OK);

    }

    @PostMapping("/rol")
    public ResponseEntity<? extends Response<RolDto>> savePublication(@RequestBody @Valid RolDto rolDto) {
        RolDto savedRol = rolService.saveRol(rolDto);
        BaseResponse<RolDto> publicationBaseResponse = new BaseResponse<>();
        return publicationBaseResponse
                .buildResponseEntity(HttpStatus.CREATED, "Publication saved successfully",savedRol);
    }


}
