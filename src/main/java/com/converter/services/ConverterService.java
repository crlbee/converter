package com.converter.services;

import com.converter.pojo.LogDataTransfer;

public interface ConverterService {

    LogDataTransfer convert(String xmlData);
}
