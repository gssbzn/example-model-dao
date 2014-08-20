package com.example.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.model.Cliente;

/**
 * 
 * @author Gustavo Bazan
 *
 */
public class ClienteDAOMemoryImplTest {
	
	private static ClienteDAOMemoryImpl dao;
	private Cliente firstCliente; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = (ClienteDAOMemoryImpl) DAOFactory.getClienteDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		firstCliente = new Cliente(1, "Prueba 1", "V-1");
		dao.create(firstCliente);
	}

	@After
	public void tearDown() throws Exception {
		dao.empty();
	}

	@Test
	public void testCreate() {
		Cliente cli = new Cliente();
		cli.setCedula("V-2");
		cli.setNombre("Prueba 2");
		Cliente cli2 = dao.create(cli);
		assertNotNull(cli2.getId());
		assertEquals(cli.getNombre(), cli2.getNombre());
		assertEquals(cli.getCedula(), cli2.getCedula());		
	}
	
	@Test
	public void testUpdate(){
		Cliente cliente = dao.first();
		cliente.setNombre("Prueba");
		assertTrue(dao.update(cliente));
		Cliente cli = dao.first();
		assertEquals(cliente, cli);
		assertEquals(cliente.getNombre(), cli.getNombre());
	}
	
	@Test
	public void testFind() {
		Cliente cli = new Cliente(1, "Prueba 1", "V-1");
		Cliente cliente = dao.find(1);
		assertEquals(cli, cliente);
	}

}
