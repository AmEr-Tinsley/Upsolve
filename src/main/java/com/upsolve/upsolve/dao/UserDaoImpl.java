package com.upsolve.upsolve.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upsolve.upsolve.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(theUser);
		
	}
	@Override
	public void update(User theUser) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println(theUser);
		currentSession.getTransaction().begin();
		currentSession.merge(theUser);
		currentSession.getTransaction().commit();
	}
	@Override
	
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		//query
		Query<User> theQuery = currentSession.createQuery("from User",User.class);
		
		//exc query
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	@Transactional
	public void deleteByUserName(String Name) {
			Session currentSession = entityManager.unwrap(Session.class);

			Query theQuery = currentSession.createQuery("delete from User where userName=:name");
			
			theQuery.setParameter("name",Name);
			
			theQuery.executeUpdate();
	}
	
	
	
	

}
