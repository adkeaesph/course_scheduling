package com.example.geektrust.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.geektrust.exceptions.CustomException;

public class InputServiceImpl implements InputService {
	
	@Override
	public List<String> getListOfCommandsFromInputFile(String filePath) throws CustomException{
		if(filePath == null || filePath.isEmpty())
			throw new CustomException("Filepath cannot be null or empty.");
		
		File file = new File(filePath);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (IOException exception) {
			closeBufferederReader(bufferedReader);
			throw new CustomException("File does not exist or file could not be opened.");
		} 
		
		String line;
		List<String> commands = new ArrayList<>();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				commands.add(line);
			}
		} catch (IOException exception) {
			throw new CustomException(exception.getMessage());
		} finally {
			closeBufferederReader(bufferedReader);
		}
		return commands;
	}

	private static void closeBufferederReader(BufferedReader bufferedReader) {
		try {
			if(bufferedReader != null)
				bufferedReader.close();
		} catch (IOException e) {
		} 
	}
}
