package com.icolak.mapper;

import com.icolak.dto.ProjectDTO;
import com.icolak.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convert(Object objectToBeConverted, T convertedObject) {
        return modelMapper.map(objectToBeConverted, (Type) convertedObject.getClass());
    }

//    public <T> T convert2(Object objectToBeConverted, Class<T> convertedObject) {
//        return modelMapper.map(objectToBeConverted, convertedObject);
//    }

}
