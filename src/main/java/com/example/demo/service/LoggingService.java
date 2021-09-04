package com.example.demo.service;


import com.example.demo.model.Constants;
import com.example.demo.model.dto.RatingDTO;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingService implements Observer {

    static private FileHandler fileHandler;
    static private SimpleFormatter formatter;
    static private Logger logger;

    public LoggingService() {
        logger = Logger.getLogger("");
        logger.setLevel(Level.INFO);
        try {
            fileHandler = new FileHandler(Constants.logFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);
    }

    @Override
    public void update(RatingDTO ratingDTO, String email) {
        logger.info(ratingDTO.toString());
    }
}
