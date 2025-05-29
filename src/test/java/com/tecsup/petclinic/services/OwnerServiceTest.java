package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testFindOwnerById() {
		Integer ID = 1;
		String FIRST_NAME_EXPECTED = "George";

		Owner owner = null;
		try {
			owner = ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		assertEquals(FIRST_NAME_EXPECTED, owner.getFirstName());
	}

	@Test
	public void testFindOwnerByFirstName() {
		String FIND_FIRST_NAME = "George";
		int EXPECTED_SIZE = 1;

		List<Owner> owners = ownerService.findByFirstName(FIND_FIRST_NAME);

		assertEquals(EXPECTED_SIZE, owners.size());
	}

	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "Alice";
		String LAST_NAME = "Smith";
		String ADDRESS = "123 Main St";
		String CITY = "Springfield";
		String TELEPHONE = "555-1234";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		log.info("OWNER CREATED: " + createdOwner);

		assertNotNull(createdOwner.getId());
		assertEquals(FIRST_NAME, createdOwner.getFirstName());
		assertEquals(LAST_NAME, createdOwner.getLastName());
		assertEquals(ADDRESS, createdOwner.getAddress());
		assertEquals(CITY, createdOwner.getCity());
		assertEquals(TELEPHONE, createdOwner.getTelephone());
	}

	@Test
	public void testUpdateOwner() {
		String FIRST_NAME = "Bob";
		String LAST_NAME = "Brown";
		String ADDRESS = "Old Address";
		String CITY = "Old City";
		String TELEPHONE = "555-0000";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		Owner createdOwner = ownerService.create(owner);

		String UPDATED_FIRST_NAME = "Bob Updated";
		String UPDATED_LAST_NAME = "Brown Updated";
		String UPDATED_ADDRESS = "New Address";
		String UPDATED_CITY = "New City";
		String UPDATED_TELEPHONE = "555-9999";

		createdOwner.setFirstName(UPDATED_FIRST_NAME);
		createdOwner.setLastName(UPDATED_LAST_NAME);
		createdOwner.setAddress(UPDATED_ADDRESS);
		createdOwner.setCity(UPDATED_CITY);
		createdOwner.setTelephone(UPDATED_TELEPHONE);

		Owner updatedOwner = null;
		try {
			updatedOwner = ownerService.update(createdOwner);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(UPDATED_FIRST_NAME, updatedOwner.getFirstName());
		assertEquals(UPDATED_LAST_NAME, updatedOwner.getLastName());
		assertEquals(UPDATED_ADDRESS, updatedOwner.getAddress());
		assertEquals(UPDATED_CITY, updatedOwner.getCity());
		assertEquals(UPDATED_TELEPHONE, updatedOwner.getTelephone());
	}

	@Test
	public void testDeleteOwner() {
		String FIRST_NAME = "Charlie";
		String LAST_NAME = "Green";
		String ADDRESS = "Some Address";
		String CITY = "Some City";
		String TELEPHONE = "555-2222";

		Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
		owner = ownerService.create(owner);

		try {
			ownerService.delete(owner.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			ownerService.findById(owner.getId());
			fail("Expected OwnerNotFoundException");
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}
	}
}
