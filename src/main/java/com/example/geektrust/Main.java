package com.example.geektrust;

import java.util.List;

import com.example.geektrust.exceptions.CustomException;
import com.example.geektrust.services.InputService;
import com.example.geektrust.services.InputServiceImpl;
import com.example.geektrust.services.OutputService;
import com.example.geektrust.services.OutputServiceImpl;
import com.example.geektrust.utils.Constants;

public class Main {
	
    public static void main(String[] args) throws CustomException {
    	InputService inputService = new InputServiceImpl();
    	List<String> commands = inputService.getListOfCommandsFromInputFile(args[Constants.INDEX_ZERO]);
    	
    	OutputService outputService = new OutputServiceImpl();
    	for(String command:commands) {
    		List<String> results = outputService.produceResultFromSingleCommand(command);
    		for(String result:results)
    			System.out.println(result);
    	}
	}
}
