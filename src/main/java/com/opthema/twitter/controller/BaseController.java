package com.opthema.twitter.controller;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BaseController {

    private final ModelMapper modelMapper;

    public BaseController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S, D> D map(S sourceInstance, Class<D> destinationTypeClass) {
        return modelMapper.map(sourceInstance, destinationTypeClass);
    }

    public <S, D> D map(S sourceInstance, Type destinationTypeClass) {
        return modelMapper.map(sourceInstance, destinationTypeClass);
    }

    <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass) {
        return entityList.stream().map(entity -> map(entity, outClass)).collect(Collectors.toList());
    }
}
