package br.com.fiap.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {
	
	@Produces
	@RequestScoped
	public EntityManager get() {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("global-persistence-unit");
		EntityManager manager = factory.createEntityManager();
		return manager;
	}

}
