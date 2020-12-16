package com.cybrilla.repositoryImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybrilla.model.Customer;
import com.cybrilla.model.PayeeDetails;
import com.cybrilla.model.Transactions;
import com.cybrilla.repository.CustomerRepository;
import com.cybrilla.request.AccountRequest;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Transactional
	public Customer getCustomer(String accountNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM customer c WHERE accountNumber = :accountNumber LIMIT 1";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("accountNumber", Long.parseLong(accountNumber));
		query.addEntity(Customer.class);
		List<Customer> results = query.list();
		if (results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

	public Long addCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		Long generatedId = (Long) session.save(customer);

		return generatedId;
	}

	public void updateCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer);
	}

	@Override
	public void addTransations(Transactions transactions) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(transactions);
	}

	@Override
	@Transactional
	public List<Transactions> getTransations(String accountNumber) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM transactions c WHERE accountNumber = :accountNumber order by createdTime DESC";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("accountNumber", Long.parseLong(accountNumber));
		query.addEntity(Transactions.class);
		List<Transactions> results = query.list();
		return results;
	}

	@Override
	@Transactional
	public void addPayee(PayeeDetails payeeDetails) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(payeeDetails);

	}

	@Override
	@Transactional
	public List<PayeeDetails> getPayees(AccountRequest accountRequest) {
		Session session = this.sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM payee c WHERE accountNumber=:accountNumber";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter("accountNumber", accountRequest.getAccountNumber());
		query.addEntity(PayeeDetails.class);
		List<PayeeDetails> results = query.list();
		return results;
	}
}
