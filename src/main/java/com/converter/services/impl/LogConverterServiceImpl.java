package com.converter.services.impl;

import com.converter.pojo.LogDataTransfer;
import com.converter.services.ConverterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class LogConverterServiceImpl implements ConverterService {

    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;
    private final String logDir;

    @Autowired
    public LogConverterServiceImpl(ObjectMapper objectMapper, XmlMapper xmlMapper, @Value("${converter.dir}") String logDir) {
        this.objectMapper = objectMapper;
        this.xmlMapper = xmlMapper;
        this.logDir = logDir;
    }

    public LogDataTransfer convert(String xmlData) {
        try {
            JsonNode jsonNode = xmlMapper.readTree(xmlData);
            JsonNode type = jsonNode.get("Type");
            if (type == null) {
                throw new RuntimeException("Неизвестный формат xml");
            }
            return new LogDataTransfer(objectMapper.writeValueAsString(jsonNode), generateLogFileName(type.asText()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось обработать xml");
        }
    }

    private Path generateLogFileName(String type) {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filename = logDir + type + "-" + dateStr + ".log";

        return Path.of(filename);
    }
}
