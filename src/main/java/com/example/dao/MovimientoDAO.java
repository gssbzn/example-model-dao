package com.example.dao;

import java.util.List;

import com.example.model.Movimiento;

/**
 * DAO for Movimiento DAO
 * @author Gustavo Bazan
 *
 */
public interface MovimientoDAO extends AbstractDAO<Movimiento, Integer> {
	public List<Movimiento> findMovimientosCuenta(Integer cuenta_id);
}
