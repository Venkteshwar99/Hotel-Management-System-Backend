package com.Receptionist.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.Receptionist.Models.Reservation;
import com.Receptionist.Models.ReservationList;
import com.Receptionist.Models.Room;



@RestController
@RequestMapping("/receptionist/reservation")
public class ReceptionReservationController {
	
	@Autowired
	RestTemplate rest;
	
	@GetMapping("/hello")
	public String helloMsgs() {
		return "Reservation Microservice";
	}
	
	@PostMapping("/addReservation")
	public String addReservation(@RequestBody Reservation book) 
	{
		return rest.postForObject("http://Reservation-Microservice/reservation/addReservation/", book, String.class);
		
	}


	@PutMapping("/update/{id}")
	public void updateReservation(@RequestBody Reservation book, @PathVariable long id)
	{
		rest.put("http://Reservation-Microservice/reservation/update/"+id,book, Reservation.class);
		
	}
	
		
	
	

	@DeleteMapping("/cancelReservation/{id}")
	public String deleteReservation(@PathVariable("id") String id) 
	{
		rest.delete("http://Reservation-Microservice/reservation/cancelReservation/"+id);
		return "Deleted room "+id;
	}
	

	@GetMapping("/ShowAllReservations")
	public ReservationList getResList()
	{
		return rest.getForObject("http://Reservation-Microservice/reservation/ShowAllReservations/", ReservationList.class);
	}
	
	@GetMapping("/getByreservation/{id}")
	public Reservation getReservation(@PathVariable("id") String id)
	{
		return rest.getForObject("http://Reservation-Microservice/reservation/getByreservation/"+id, Reservation.class);

	}
	

}
