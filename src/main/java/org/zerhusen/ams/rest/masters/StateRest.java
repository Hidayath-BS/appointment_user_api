package org.zerhusen.ams.rest.masters;



import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.security.Ak_state;
import org.zerhusen.ams.repository.security.StateRepository;


@RestController()
@RequestMapping(value="/masters")
@CrossOrigin("*")
public class StateRest {
	@Autowired
	private StateRepository stateRepo;
	@PostMapping(value="/addstate")
	public ResponseEntity<?> addstate(@RequestBody String akstate)throws JSONException{
		JSONObject jsonobj = new JSONObject(akstate);
		Ak_state state= new Ak_state(jsonobj.getString("state"));
		stateRepo.save(state);
	return new ResponseEntity<>("State is Added Successfully", HttpStatus.OK);
}
	
	@GetMapping(value="/getstates")
	private Iterable<Ak_state> getStates(){
		return stateRepo.findAll();
		
	}
	
	@GetMapping(value="/getState/{id}")
	private Ak_state getState(@PathVariable("id") int id ) {
		return stateRepo.findById(id);
	}
	
	@PutMapping("/editState")
	public ResponseEntity<?> editState(@RequestBody String request) throws JSONException{
		JSONObject req = new JSONObject(request);
		Ak_state state = stateRepo.findById(req.getInt("id"));
		if(state != null) {
			state.setState(req.getString("state"));
			stateRepo.save(state);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}

