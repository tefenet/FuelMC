package fuelMC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fuelMC.DAO.UsuarioDAO;
import fuelMC.model.Credentials;
import fuelMC.model.Usuario;
import fuelMC.serviceInterfaces.IUsuarioService;
import fuelMC.services.ApplicationService;
import fuelMC.services.TokenService;

//imports
@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	TokenService tokenService;
	@Autowired
	ApplicationService applicationService;

	private final int EXPIRATION_IN_SEC = 1600;

	@GetMapping(value = "/usuarios")
	public ResponseEntity<List<Usuario>> listAllUsers() {
		@SuppressWarnings("unchecked")
		List<Usuario> users = (List<Usuario>) usuarioService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}
	
	@PostMapping(value = "/usuarios")
	public ResponseEntity<Usuario> createUser(@RequestBody Usuario user, @RequestHeader int profile) {
		System.out.println("Creando el usuario " + user.getUsername());
		if (usuarioService.userExist(user.getUsername())) {
			String s = "Ya existe un usuario con nombre" + user.getNombre();
			System.out.println(s);
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
			// Código de respuesta 409
		}
		user.setProfile(profile);
		usuarioService.persistir(user);
		return new ResponseEntity<Usuario>(HttpStatus.CREATED); // Código 201
	}

//	@CrossOrigin(origins = { "http://localhost:4200" })
	@PostMapping(value = "/autenticacion")
	public ResponseEntity<Credentials> autenticacion(@RequestHeader("username") String username,
			@RequestHeader("clave") String clave) {
		Usuario u = usuarioService.autenticacion(username, clave);
		if (u != null) {
			// HttpHeaders responseHeaders = new HttpHeaders();
			// responseHeaders.set("X-Auth-Token", u.getId().toString() + "123456");
			String token = tokenService.generateToken(u, EXPIRATION_IN_SEC);
			return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, username, u.getProfile()));
			// return ResponseEntity.ok().headers(responseHeaders).body(null);
		} else

			return new ResponseEntity<Credentials>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUser(@RequestHeader("Token") String token, @PathVariable("id") long id) {
		if (applicationService.validarToken(token, id)) {
			Usuario user = usuarioService.recuperar(id);
			if (user == null) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<Usuario>(user, HttpStatus.OK);
		} else
			return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);

	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> updateUser(@RequestHeader("Token") String token, @PathVariable("id") long id,
			@RequestBody Usuario userDatos) {
		if (applicationService.validarToken(token, id)) {
			Usuario user = usuarioService.recuperar(id);
			if (user == null) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			} else {
				Usuario usuarioNuevo = usuarioService.validarDatos(user, userDatos);
				user = usuarioService.actualizarUsuario(usuarioNuevo);
				return new ResponseEntity<Usuario>(user, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
		}
	}

}