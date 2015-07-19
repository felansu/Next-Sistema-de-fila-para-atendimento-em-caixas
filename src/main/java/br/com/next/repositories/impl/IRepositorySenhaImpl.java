package br.com.next.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.next.repositories.IRepositorySenha;
import br.com.next.repositories.custom.IRepositorySenhaCustom;

public class IRepositorySenhaImpl implements IRepositorySenhaCustom {

    @Autowired
    IRepositorySenha repository;


}
