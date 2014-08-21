package com.example.dao;

import java.util.List;

import com.example.model.Cuenta;
import com.example.model.Movimiento;

/**
 * DAO for Cuenta Model
 * @author Gustavo Bazan
 *
 */
public interface CuentaDAO extends AbstractDAO<Cuenta, Integer> {
    public List<Cuenta> findCuentasCliente(Integer cliente_id);
    public void actualizarCuenta(Movimiento mov);
}
