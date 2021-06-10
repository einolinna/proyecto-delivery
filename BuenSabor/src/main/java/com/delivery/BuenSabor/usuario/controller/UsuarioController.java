package com.delivery.BuenSabor.usuario.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.BuenSabor.security.dto.JwtDto;
import com.delivery.BuenSabor.security.dto.LoginUsuario;
import com.delivery.BuenSabor.security.dto.NuevoUsuario;
import com.delivery.BuenSabor.security.dto.TokenDto;
import com.delivery.BuenSabor.security.enums.RolNombre;
import com.delivery.BuenSabor.security.jwt.JwtProvider;
import com.delivery.BuenSabor.usuario.entity.Rol;
import com.delivery.BuenSabor.usuario.entity.Usuario;
import com.delivery.BuenSabor.usuario.service.RolServiceImpl;
import com.delivery.BuenSabor.usuario.service.UsuarioServiceImpl;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/usuario/auth")
public class UsuarioController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private RolServiceImpl rolService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Value("${google.clientId}")
	private String googleClientId;
	
	@Value("${secretPsw}")
	private String secretPsw;
	
	@PostMapping("/crear")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		if(usuarioService.existsByUsuario(nuevoUsuario.getUsuario()))
			return ResponseEntity.badRequest().build();
		Usuario usuario = new Usuario();
		usuario.setUsuario(nuevoUsuario.getUsuario());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setClave(passwordEncoder.encode(nuevoUsuario.getPassword()));
		usuario.setCliente(nuevoUsuario.getCliente());
		Set<Rol> roles = new HashSet<>();
		if(nuevoUsuario.getRoles().contains("cliente"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		if(nuevoUsuario.getRoles().contains("jefe"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		if(nuevoUsuario.getRoles().contains("cajero"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CAJERO).get());
		if(nuevoUsuario.getRoles().contains("cocinero"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_COCINERO).get());
		usuario.setRoles(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
	
	@PostMapping("/google")
	public ResponseEntity<TokenDto> google(@RequestBody TokenDto tokenDto) throws IOException {
		final NetHttpTransport transport = new NetHttpTransport();
		final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
		GoogleIdTokenVerifier.Builder verifier = 
				new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
				.setAudience(Collections.singletonList(googleClientId));
		final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
		final GoogleIdToken.Payload payload = googleIdToken.getPayload();
		Usuario usuario = new Usuario();
		if(usuarioService.existsEmail(payload.getEmail()))
			usuario = usuarioService.getByEmail(payload.getEmail()).get();
		else
			usuario = saveUsuario(payload.getEmail());
		TokenDto tokenRes = login (usuario);
		return ResponseEntity.status(HttpStatus.OK).body(tokenRes);
		//return new ResponseEntity(tokenRes, HttpStatus.OK);
	}
	
	private TokenDto login(Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getUsuario(), secretPsw));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setValue(jwt);
		return tokenDto;
	}
	private TokenDto loginEmail(Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getEmail(), secretPsw)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setValue(jwt);
		return tokenDto;
	}

	private Usuario saveUsuario(String email) {
		Usuario usuario = new Usuario(email, passwordEncoder.encode(secretPsw));
		Set<Rol> roles = new HashSet<>();
		if(usuario.getRoles().contains("cliente"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		if(usuario.getRoles().contains("jefe"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		if(usuario.getRoles().contains("cajero"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CAJERO).get());
		if(usuario.getRoles().contains("cocinero"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_COCINERO).get());
		usuario.setRoles(roles);
		return usuarioService.save(usuario);
	}
}
