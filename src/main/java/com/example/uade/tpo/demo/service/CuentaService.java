package com.example.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.uade.tpo.demo.entity.Cuenta;
import com.example.uade.tpo.demo.entity.Pedido;
import com.example.uade.tpo.demo.exceptions.CuentaDuplicateException;

public interface CuentaService {
    Page<Cuenta> getCuentas(PageRequest pageRequest);

    Optional<Cuenta> getCuentaById(Long id);
    
    Optional<Cuenta> getCuentaByUsername(String username);

    Cuenta newCuenta(String name, String lastName, String username, String password, int discount) throws CuentaDuplicateException;
}