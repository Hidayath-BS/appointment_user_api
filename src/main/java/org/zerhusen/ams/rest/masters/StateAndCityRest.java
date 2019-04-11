package org.zerhusen.ams.rest.masters;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.security.Ak_city;
import org.zerhusen.ams.model.security.Ak_state;
import org.zerhusen.ams.repository.security.CityRepository;
import org.zerhusen.ams.repository.security.StateRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/states")
public class StateAndCityRest {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;

	@GetMapping("/")
	public @ResponseBody Iterable<Ak_state> stateList(){
		return stateRepository.findAll();
	}
	
	@GetMapping("/city/{id}")
	public @ResponseBody Iterable<Ak_city> cityList(@PathVariable int id){
		Ak_state state = (Ak_state)stateRepository.findById(id);
		return (cityRepository.findAll().stream().filter(i-> i.getSate_id() == state).collect(Collectors.toList()));
	}
	
	@GetMapping("/getCity/{id}")
	public Ak_city getcity(@PathVariable int id) {
		return cityRepository.findById(id);
	}
	
	@PutMapping("/editCity")
	public ResponseEntity<?> editCity(@RequestBody String request) throws JSONException{
		JSONObject req = new JSONObject(request);
		
		int cityid = req.getInt("city");
		
		int stateId = req.getInt("state");
		
		Ak_state state = stateRepository.findById(stateId);
		Ak_city city = cityRepository.findById(cityid);
		if(city != null && state != null) {
			city.setCity(req.getString("cityName"));
			city.setSate_id(state);
			cityRepository.save(city);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	
	
}
