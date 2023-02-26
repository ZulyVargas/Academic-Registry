package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.mappers.ISubjectMapper;
import com.perficient.courseregistry.app.repository.ISubjectRepository;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectService  implements ISubjectService {

    @Autowired
    private final ISubjectRepository subjectRepository;

    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    private Set<String> findPrerrequisitesId(UUID subjectId){
        Set<String> prerrequisitesIds = this.subjectRepository.findPrerrequisitesById(subjectId).stream().map(UUID::toString).collect(Collectors.toSet());
        return prerrequisitesIds;
        }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        //subjectRepository.findAll().stream().forEach(s-> System.out.println("S? "+ findPrerrequisitesId(s.getSubjectId())));

        List<SubjectDTO> subjects = subjectRepository.findAll().stream().map(s -> ISubjectMapper.INSTANCE.subjectToSubjectDTO(s,findPrerrequisitesId(s.getSubjectId()) )).collect(Collectors.toList());
        return subjects;
    }
}
