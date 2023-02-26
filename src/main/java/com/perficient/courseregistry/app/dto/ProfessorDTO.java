package com.perficient.courseregistry.app.dto;

import java.io.Serializable;
import java.util.UUID;

public class ProfessorDTO extends UserDTO implements Serializable {
    private UUID professorId;
    private String degree;
}
