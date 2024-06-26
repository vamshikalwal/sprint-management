	package com.sprintmanagement.services;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprintmanagement.dto.SprintsDTO;
import com.sprintmanagement.entities.Sprints;
import com.sprintmanagement.repository.SprintRepository;

@Service
public class SprintServiceImpl implements SprintService  {
	
	@Autowired
	private SprintRepository sprintRepository;
	
//	--This method is used to get all sprints present in the database--
	@Override
	public List<SprintsDTO> getAllSprints() {
		

		Iterable<Sprints> sprintsLists = sprintRepository.findAll();
		List<SprintsDTO> sprintdtoList=new ArrayList<SprintsDTO>();
 		for(Sprints Sprintsp : sprintsLists)
 		{
 			SprintsDTO sprintsDto1 = new SprintsDTO();
 			sprintsDto1.setSprintId(Sprintsp.getSprintId());
 			sprintsDto1.setSprintName(Sprintsp.getSprintName());
 			sprintsDto1.setStartDate(Sprintsp.getStartDate());
 			sprintsDto1.setEndDate(Sprintsp.getEndDate());
 			sprintsDto1.setProjectCode(Sprintsp.getProjectCode());
 			sprintsDto1.setCreatedOn(Sprintsp.getCreatedOn());
 			
 			sprintdtoList.add(sprintsDto1);

 		}
 		
 		return sprintdtoList;
	}
	
//	--This method is used to create a new Sprint--
	@Override
	public String persistSprint(SprintsDTO sprintsDTO) {
		
		Sprints	sprint=new Sprints(); 	
		sprint.setStartDate(sprintsDTO.getStartDate());
		sprint.setEndDate(sprintsDTO.getEndDate());
		sprint.setProjectCode(sprintsDTO.getProjectCode());  
		sprint.setCreatedOn(LocalDate.now());
		//validation
 		LocalDate startDate = sprintsDTO.getStartDate();
 		LocalDate endDate = sprintsDTO.getEndDate();
 		if (startDate != null && endDate!=null)
 		{
 			long days=ChronoUnit.DAYS.between(startDate, endDate);
 			if (days >28 || days<7)
 			{
 				throw new IllegalArgumentException("Sprint duration must be between 1 week and 4 weeks.");
 			}
 		}
 		else {
			return "fail";
		}
 		
		Sprints saveSprints = sprintRepository.save(sprint);
		
		System.out.println(saveSprints);
		if(saveSprints!=null)return "success";
		else return "fail";

	}
	

}
