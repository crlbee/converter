package com.converter.services.impl;

import com.converter.services.ConverterService;
import com.converter.services.WriterService;
import com.converter.services.XmlReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class XmlReceiverServiceImpl implements XmlReceiverService {

    private final ConverterService converterService;
    private final WriterService writerService;

    @Autowired
    public XmlReceiverServiceImpl(ConverterService converterService, WriterService writerService) {
        this.converterService = converterService;
        this.writerService = writerService;
    }

    @Override
    public void receive(String xmlData) {
        Stream.of(converterService.convert(xmlData))
                .forEach(writerService::write);
    }
}
