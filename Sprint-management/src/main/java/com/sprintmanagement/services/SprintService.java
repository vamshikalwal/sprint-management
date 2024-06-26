package com.sprintmanagement.services;

import java.util.List;

import com.sprintmanagement.dto.SprintsDTO;

public interface SprintService {

	String persistSprint(SprintsDTO sprintsDTO);
	List<SprintsDTO> getAllSprints();
}
