package com.delivery.BuenSabor.usuario.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.PutMapping;
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
@CrossOrigin(origins = "*")
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
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return ResponseEntity.badRequest().build();
		Usuario usuario = new Usuario();
		usuario.setNombre(nuevoUsuario.getNombre());
		usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		//usuario.setCliente(nuevoUsuario.getCliente());
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
	
	@PostMapping("/crear-cliente")
	public ResponseEntity<?> nuevoCliente(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return ResponseEntity.badRequest().build();
		Usuario usuario = new Usuario();
		usuario.setNombre(nuevoUsuario.getNombre());
		usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
		//usuario.setCliente(nuevoUsuario.getCliente());
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		usuario.setRoles(roles);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
	}
	
	/*Puede que no se implemente*/
	@PostMapping("/cargar")
	public ResponseEntity<?> cargar(@RequestBody Usuario usuario) {
		Optional<Usuario> o = usuarioService.getByEmail(usuario.getEmail());
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Usuario usuarioDb = o.get();
		usuarioDb.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioDb.setNombre(usuario.getNombre());
		usuarioDb.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDb.setEmail(usuario.getEmail());
		//usuarioDb.setCliente(usuario.getCliente());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDb));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();
		
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getPassword()));
		System.out.println(authentication.toString());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		System.out.println(authentication.toString());
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
		if(usuarioService.getByEmail(payload.getEmail()).isPresent()) {
			usuario = usuarioService.getByEmail(payload.getEmail()).get();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} else {
			usuario = saveUsuario(payload.getEmail());
		}
		TokenDto tokenRes = login (usuario);
		
		return ResponseEntity.status(HttpStatus.OK).body(tokenRes);
	}
	
	@PutMapping
	public ResponseEntity<?> updatePassword(@RequestBody LoginUsuario usuario) {
		Optional o = usuarioService.findByUsuario(usuario.getUsuario());
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.updatePassword(usuario.getPassword(), usuario.getUsuario());
		return ResponseEntity.ok().build();
		/*LoginUsuario usuarioDb = new LoginUsuario();
		usuarioDb.setUsuario(usuario.getUsuario());
		usuarioDb.setPassword(usuario.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDb);*/
	}
	
	private TokenDto login(Usuario usuario) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(), secretPsw));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setValue(jwt);
		return tokenDto;
	}

	private Usuario saveUsuario(String email) {
		Usuario usuario = new Usuario(email, email, passwordEncoder.encode(secretPsw));
		usuario.setNombreUsuario(email);
		Set<Rol> roles = new HashSet<>();
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_CLIENTE).get());
		usuario.setRoles(roles);
		return usuarioService.save(usuario);
	}
}
