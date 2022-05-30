package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Visitante;


public class VisitanteDao {

	EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("global-persistence-unit");
	EntityManager manager = factory.createEntityManager();
	//private EntityManager manager;
	
	public void create(Visitante visitante) {
		manager.getTransaction().begin();
		manager.persist(visitante);
		manager.getTransaction().commit();
		
		manager.clear();
		
	}
	
	public List<Visitante> listAll() {
		TypedQuery<Visitante> query = 
				manager.createQuery("SELECT v FROM Visitante v", Visitante.class);
		return query.getResultList();
	}

	public void remove(Visitante visitante) {
		manager.getTransaction().begin();
		manager.remove(visitante);
		manager.getTransaction().commit();
	}

	public void update(Visitante visitante) {
		manager.getTransaction().begin();
		manager.merge(visitante);
		manager.getTransaction().commit();
	}

}
