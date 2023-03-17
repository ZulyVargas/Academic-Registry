package com.perficient.courseregistry.app.hateoas;

import org.springframework.hateoas.RepresentationModel;

public abstract class Resource<T extends RepresentationModel<? extends T>> extends RepresentationModel<T> {

    public abstract T generateLinks();
}
