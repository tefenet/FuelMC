package fuelMC.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import fuelMC.DAO.FuelingDAO;
import fuelMC.model.ContentVolume;
import fuelMC.model.FuelTank;
import fuelMC.model.Fueling;
import fuelMC.serviceInterfaces.IFuelTankService;
import fuelMC.serviceInterfaces.IUsuarioService;
import fuelMC.services.ApplicationService;
import fuelMC.services.TokenService;
import fuelMC.services.UsuarioService;

@CrossOrigin
@RestController
public class FuelTankController {

	@Autowired
	FuelingDAO fuelDao;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	IFuelTankService tankService;
	@Autowired
	IUsuarioService UService;
	@Autowired
	TokenService tokenservice;
	
	@GetMapping(value = "/fueltanks")	
	public ResponseEntity<List<FuelTank>> listAll() {
		System.out.print("inside ftcontroller listAll \n");
//		if (tokenservice.validateToken(cadena)) {
			List<FuelTank> carteles = (List<FuelTank>) tankService.getAll();
			if (carteles.isEmpty()) {
				return new ResponseEntity<List<FuelTank>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<FuelTank>>(carteles, HttpStatus.OK);			
//		} else {
//			return new ResponseEntity<List<FuelTank>>(HttpStatus.FORBIDDEN);
		}

	//}

	@GetMapping(value = "/fueltanks/{id}")
	public ResponseEntity<FuelTank> getTank(@RequestHeader("Token") String token, @PathVariable("id") long id) {
		if (tokenservice.validateToken(token)) {
			FuelTank tank = tankService.recuperar(id);
			if (tank == null) {
				return new ResponseEntity<FuelTank>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<FuelTank>(tank, HttpStatus.OK);
		} else
			return new ResponseEntity<FuelTank>(HttpStatus.UNAUTHORIZED);

	}

	@PostMapping(value = "/fueltank")
	public ResponseEntity<FuelTank> addFuelTank(@RequestHeader("Token") String token, @RequestBody FuelTank FuelTank) {
		System.out.print("gregando T");
		if (UService.canAddFuelTank(token)) {
			if (tankService.fuelTankExist(FuelTank.getName())) {
				String s = "Ya existe una tanque con nombre" + FuelTank.getName();
				System.out.println(s);
				return new ResponseEntity<FuelTank>(HttpStatus.CONFLICT);
				// Código de respuesta 409
			}
			tankService.persistir(FuelTank);
			return new ResponseEntity<FuelTank>(HttpStatus.CREATED);

		} else {
			return new ResponseEntity<FuelTank>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping(value = "/fueltank/{id}")
	public ResponseEntity<List<FuelTank>> removeFuelTank(@PathVariable("id") long id) {
		tankService.delete(id);
		List<FuelTank> tanks = (List<FuelTank>) tankService.getAll();
		if (tanks.isEmpty()) {
			return new ResponseEntity<List<FuelTank>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FuelTank>>(tanks, HttpStatus.OK);
	}

	@PutMapping(value = "/fueltank/{id}")
	public ResponseEntity<FuelTank> updateFuelTank(@RequestHeader("Token") String token, @PathVariable("id") long id,
			@RequestBody FuelTank tankData) {
		if (applicationService.validarToken(token, id)) {
			FuelTank tank = tankService.recuperar(id);
			if (tank == null) {
				return new ResponseEntity<FuelTank>(HttpStatus.NOT_FOUND);
			} else {
				FuelTank newTank = tankService.validateData(tank, tankData);
				tank = tankService.update(newTank);
				return new ResponseEntity<FuelTank>(tank, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<FuelTank>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "/fueling")
	public ResponseEntity<FuelTank> addFueling(@RequestHeader("Token") String token, @RequestBody Fueling fueling) {
		if (tokenservice.validateToken(token)) {
			fuelDao.persistir(fueling);
			return new ResponseEntity<FuelTank>(HttpStatus.CREATED);

		} else {
			return new ResponseEntity<FuelTank>(HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(value = "/loads/{id}")
	public ResponseEntity<Set<Fueling>> listFuelings(@RequestHeader("Token") String cadena, @PathVariable("id") long id) {
		if (tokenservice.validateToken(cadena)) {
			Set<Fueling> fuelings =  tankService.getLoads(id);
			if (fuelings.isEmpty()) {
				return new ResponseEntity<Set<Fueling>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Set<Fueling>>(fuelings, HttpStatus.OK);
		} else {
			return new ResponseEntity<Set<Fueling>>(HttpStatus.FORBIDDEN);
		}

	}
	//given id of tank return the history
	@GetMapping(value = "/history/{id}")
	public ResponseEntity<List<ContentVolume>> getHistory(@RequestHeader("Token") String cadena, @PathVariable("id") long id) {
		if (tokenservice.validateToken(cadena)) {
			List<ContentVolume> history = (List<ContentVolume>) tankService.getHistory(id);
			if (history.isEmpty()) {
				return new ResponseEntity<List<ContentVolume>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<ContentVolume>>(history, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ContentVolume>>(HttpStatus.FORBIDDEN);
		}

	}
	
}
