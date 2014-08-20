package com.example.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Cliente;
import com.example.model.Cuenta;
import com.example.model.Movimiento;
import com.example.model.TipoCuenta;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class CuentaDAOMemoryImplTest {
	
	private static CuentaDAOMemoryImpl cuentaDao;
	private static ClienteDAOMemoryImpl clienteDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clienteDao = (ClienteDAOMemoryImpl) DAOFactory.getClienteDAO();
		cuentaDao = (CuentaDAOMemoryImpl) DAOFactory.getCuentaDAO();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		clienteDao = null;
		cuentaDao = null;
	}

	@Before
	public void setUp() throws Exception {
		Cliente cliente = clienteDao.create(new Cliente(1, "Prueba 1", "V-1"));
		Cuenta cuenta = new Cuenta(1,BigDecimal.ZERO,TipoCuenta.ACREENCIA.getValue());
		cuenta.setCliente(cliente);
		cuentaDao.create(cuenta);
	}

	@After
	public void tearDown() throws Exception {
		clienteDao.empty();
		cuentaDao.empty();
		
	}
	
	@Test
	public void testCreate() {
		Cuenta cuenta = new Cuenta();
		cuenta.setTipo(TipoCuenta.ACREENCIA.getValue());
		Cuenta cuenta2 = cuentaDao.create(cuenta);
		assertEquals(cuenta.getTipo(), cuenta2.getTipo());
	}
	
	@Test
	public void testUpate(){
		Cuenta cuenta = cuentaDao.first();
		cuenta.setSaldo(BigDecimal.TEN);
		assertTrue(cuentaDao.update(cuenta));
		Cuenta cu = cuentaDao.first();
		assertEquals(cuenta, cu);
		assertEquals(cuenta.getSaldo(), cu.getSaldo());
	}

	@Test
	public void testRemove() {
		Cuenta cuenta = cuentaDao.first();
		assertTrue(cuentaDao.remove(cuenta));
		cuenta = cuentaDao.first();
		assertNull(cuenta);
	}

	@Test
	public void testFind() {
		Cuenta cuenta = new Cuenta(1,BigDecimal.ZERO,TipoCuenta.ACREENCIA.getValue());
		Cuenta cuenta2 = cuentaDao.find(1);
		assertEquals(cuenta, cuenta2);
	}
	
	@Test
	public void testActualizarCuenta(){
		Cuenta cuenta = new Cuenta(1,BigDecimal.ZERO,TipoCuenta.ACREENCIA.getValue());
		Movimiento mov = new Movimiento(1, "+", new BigDecimal(100));
		mov.setCuenta(cuenta);
		cuentaDao.actualizarCuenta(mov);
		assertEquals(new BigDecimal(100), cuenta.getSaldo());
	}
	
}
