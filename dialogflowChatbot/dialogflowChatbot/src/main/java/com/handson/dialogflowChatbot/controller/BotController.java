package com.handson.dialogflowChatbot.controller;

import com.handson.dialogflowChatbot.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/bot")
public class BotController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> getShow(@RequestParam String keyword) {
        try {
            String searchResult = searchService.searchShows(keyword);
            return new ResponseEntity<>(searchResult, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred while processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
