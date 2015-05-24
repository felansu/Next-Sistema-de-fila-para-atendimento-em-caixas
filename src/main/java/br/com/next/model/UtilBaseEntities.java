package br.com.next.model;

import java.io.Serializable;

import org.springframework.data.jpa.domain.AbstractPersistable;

public abstract class UtilBaseEntities<T extends Serializable> extends AbstractPersistable<T> implements Serializable {
    private static final long serialVersionUID = 201210190226L;
}