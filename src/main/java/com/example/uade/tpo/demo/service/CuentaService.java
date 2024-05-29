package com.example.uade.tpo.demo.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;

public interface CuentaService {
    Page<Cuenta> getCuentas(PageRequest pageRequest);

    Optional<Cuenta> getCuentaById(Long id);

    Cuenta newCuenta(String name, String lastName, String username, String password, int discount) throws CuentaDuplicateException;
}