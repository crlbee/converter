package com.converter.services.impl;

import com.converter.fileIO.FileIO;
import com.converter.pojo.LogDataTransfer;
import com.converter.services.WriterService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Override
    public void write(LogDataTransfer logData) {
        if (Files.exists(logData.file())) {
            List<String> lines = FileIO.readAllLines(logData.file());

            int existingLineCount = Integer.parseInt(lines.get(0));
            lines.set(0, String.valueOf(existingLineCount + 1));
            lines.add(logData.json());

            File tempFile = FileIO.createTempFile();
            FileIO.writeToFile(tempFile.toPath(), lines);
            FileIO.move(tempFile.toPath(), logData.file());
        } else {
            FileIO.writeToFile(logData.file(), List.of("1", logData.json()));
        }
    }

}
