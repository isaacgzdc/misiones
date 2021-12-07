package com.example.misiones.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.exception.MissionException;
import com.example.misiones.model.Mission;
import com.example.misiones.service.MissionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

	private final MissionService missionService;
	
	@GetMapping
	public ResponseEntity<List<Mission>> getAll(){
		List<Mission> missions = missionService.findAll();
		log.debug("Retrieved {} missions", missions.size());
		return new ResponseEntity<List<Mission>>(missions, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mission> get(@PathVariable("id") Long id){
		Optional<Mission> opt = missionService.findById(id);
		opt.map(mission -> {
			log.debug("Find mission with id({})",id);
			log.debug("Mission found: {}",opt.get().toString());
			return new ResponseEntity<Mission>(opt.get(), HttpStatus.OK);
		}).orElseGet(()->{
			log.debug("Cannot find mission with id({})",id);
			return new ResponseEntity<Mission>(new Mission(), HttpStatus.NO_CONTENT);
		});
		log.debug("Something wrong when trying to get mission with id({})",id);
		return new ResponseEntity<Mission>(new Mission(), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Mission> save(@Valid @RequestBody MissionRequest requestData) throws MissionException{
		Mission mission = missionService.save(requestData);
		if(mission == null) {
			log.error("The mission cannot be created!");
			log.error(requestData.toString());
			throw new MissionException("The mission cannot be created with provied data!");
		}
		log.debug("Mission saved");
		return new ResponseEntity<Mission>(mission, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Mission> save(@PathVariable("id") Long id, @RequestBody MissionRequest dto) throws MissionException{
		 return missionService.findById(id)
		      .map(mission -> {
		        mission.setName(dto.getName());
		        mission.setInitDate(dto.getInitDate());
		        mission.setEndDate(dto.getEndDate());
		        var missionUpdated = new ResponseEntity<Mission>(missionService.save(mission), HttpStatus.CREATED);
		        log.debug("Mission saved");
		        return missionUpdated;
		      })
		      .orElseThrow(() -> {
		    	 return new MissionException("Mission with id("+id+") not found to be updated");
		      });
		 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id){
		missionService.deleteById(id);
		log.debug("Deleted mission with id({})", id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
}
