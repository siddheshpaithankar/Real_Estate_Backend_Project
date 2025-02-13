package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Types;
import com.example.repository.TypesRepository;
import java.util.List;

@Service
public class TypesService {

    private final TypesRepository typesRepository;

    @Autowired
    public TypesService(TypesRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    public Types addType(Types type) {
        return typesRepository.save(type);
    }

    public List<Types> getAllTypes() {
        return typesRepository.findAll();
    }

    public Types getTypeById(Long id) {
        return typesRepository.findById(id).orElse(null);
    }

    public void deleteType(Long id) {
        typesRepository.deleteById(id);
    }
}
