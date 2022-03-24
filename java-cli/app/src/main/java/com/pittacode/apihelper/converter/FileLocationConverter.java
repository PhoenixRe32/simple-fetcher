package com.pittacode.apihelper.converter;

import com.pittacode.apihelper.domain.FileLocation;
import picocli.CommandLine;

public class FileLocationConverter implements CommandLine.ITypeConverter<FileLocation> {
    @Override
    public FileLocation convert(String value) {
        return new FileLocation(value);
    }
}
