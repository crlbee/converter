package com.converter.controllers;

import com.converter.services.XmlReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    private final XmlReceiverService xmlReceiverService;

    @Autowired
    public LogController(XmlReceiverService xmlReceiverService) {
        this.xmlReceiverService = xmlReceiverService;
    }

    @PostMapping("/")
    public ResponseEntity<String> receiveXml(@RequestBody String xmlData) {
        xmlReceiverService.receive(xmlData);
        return new ResponseEntity<>("Data received and saved successfully.", HttpStatus.OK);
    }
}