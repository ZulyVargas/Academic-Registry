package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
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

    private Set<Subject> findPrerrequisitesById(UUID subjectId) {
        Set<Subject> prerrequisites = this.subjectRepository.findPrerrequisitesById(subjectId).stream().map(p -> {p.setPrerrequisites(findPrerrequisitesById(p.getSubjectId()));
            return p;
            }
            ).collect(Collectors.toSet());
        return prerrequisites;
    }
    @Override
    public Set<SubjectDTO> getAllSubjects() {
        Set<SubjectDTO> subjects =subjectRepository.findAll().stream().map(s -> {s.setPrerrequisites(this.findPrerrequisitesById(s.getSubjectId()));
            SubjectDTO subjectDTO = ISubjectMapper.INSTANCE.subjectToSubjectDTO(s);
            return subjectDTO;
             }).collect(Collectors.toSet());

        return subjects;
    }
}
