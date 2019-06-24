package org.zerhusen.ams.rest.availabelSlots;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerhusen.ams.model.AmsAvailableTimeSlots;
import org.zerhusen.ams.repository.AmsAvailableTimeSlotRepository;
import org.zerhusen.ams.repository.AmsTimeSlotsRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/availableSlots")
public class AvailableSlotsRest {

	@Autowired
	public AmsAvailableTimeSlotRepository availSlotRepo;
	
	@GetMapping("/getAllSlots/{date}")
	public @ResponseBody Iterable<AmsAvailableTimeSlots> getByBrnach(@PathVariable("date") String date ) throws JSONException{
		LocalDate localDate = LocalDate.parse(date);
//		int hour = 0;
//		int minute=0;
//		int second=0;
		LocalTime localTime = LocalTime.now();
		LocalDate currDate = LocalDate.now();
		if(localDate.equals(currDate)){
		return availSlotRepo.findAll().stream().filter(i-> i.getDate().equals(localDate) && i.isActive()==true && i.getOnlineCount()<i.getOnlinelimit() && i.getSlot().getStartTime().isAfter(localTime)).collect(Collectors.toList());
		}
		else{
			return availSlotRepo.findAll().stream().filter(i-> i.getDate().equals(localDate) && i.isActive()==true && i.getOnlineCount()<i.getOnlinelimit()).collect(Collectors.toList());
		}
		}
}
