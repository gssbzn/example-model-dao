package com.example.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.dao.memory.ClienteDAOMemoryImpl;
import com.example.dao.memory.CuentaDAOMemoryImpl;
import com.example.dao.memory.MovimientoDAOMemoryImpl;
import com.example.factory.DAOFactory;
import com.example.factory.DAOFactory.DAOTYPE;
import com.example.model.Cliente;
import com.example.model.Cuenta;
import com.example.model.Movimiento;
import com.example.model.TipoCuenta;
import com.example.model.TipoMovimiento;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class MovimientoDAOMemoryImplTest {

	private static DAOFactory daoFactory;
	private static MovimientoDAOMemoryImpl movimientoDao;
	private static CuentaDAOMemoryImpl cuentaDao;
	private static ClienteDAOMemoryImpl clienteDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		daoFactory = DAOFactory.getDAOFactory(DAOTYPE.MEMORYFACTORY);
		clienteDao = (ClienteDAOMemoryImpl) daoFactory.getClienteDAO();
		cuentaDao = (CuentaDAOMemoryImpl) daoFactory.getCuentaDAO();
		movimientoDao = (MovimientoDAOMemoryImpl) daoFactory.getMovimientoDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		clienteDao = null;
		cuentaDao = null;
		movimientoDao = null;
	}

	@Before
	public void setUp() throws Exception {
		Cliente cliente = clienteDao.create(new Cliente(1, "Prueba 1", "V-1"));
		Cuenta cuenta = new Cuenta(1,BigDecimal.ZERO,TipoCuenta.ACREENCIA.getValue());
		cuenta.setCliente(cliente);
		cuentaDao.create(cuenta);
		Movimiento movimiento = new Movimiento(1,TipoMovimiento.DEPOSITO.getValue(),BigDecimal.ONE);
		movimiento.setCuenta(cuenta);
		movimientoDao.create(movimiento);
	}

	@After
	public void tearDown() throws Exception {
		clienteDao.empty();
		cuentaDao.empty();
		movimientoDao.empty();
		
		
	}
	
	@Test
	public void testCreate() {
		Movimiento movimiento = new Movimiento();
		movimiento.setTipo(TipoMovimiento.DEPOSITO.getValue());
		movimiento.setMonto(new BigDecimal(100));
		Movimiento movimiento2 = movimientoDao.create(movimiento);
		assertNotNull(movimiento.getId());
		assertEquals(movimiento.getTipo(), movimiento2.getTipo());
		assertEquals(movimiento.getMonto(), movimiento2.getMonto());
	}
	
	@Test
	public void testUpate(){
		Movimiento movimiento = movimientoDao.first();
		movimiento.setMonto(BigDecimal.TEN);
		assertTrue(movimientoDao.update(movimiento));
		Movimiento movimiento2 = movimientoDao.first();
		assertEquals(movimiento, movimiento2);
		assertEquals(movimiento.getMonto(), movimiento2.getMonto());
	}

	@Test
	public void testRemove() {
		Movimiento movimiento = movimientoDao.first();
		assertTrue(movimientoDao.remove(movimiento));
		movimiento = movimientoDao.first();
		assertNull(movimiento);
	}

	@Test
	public void testFind() {
		Movimiento movimiento = new Movimiento(1,TipoMovimiento.DEPOSITO.getValue(),BigDecimal.ONE);
		Movimiento movimiento2 = movimientoDao.find(1);
		assertEquals(movimiento, movimiento2);
	}
}

