package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.exceptions.CustomException;

public interface InputService {
	
	List<String> getListOfCommandsFromInputFile(String filePath) throws CustomException;
	

}
