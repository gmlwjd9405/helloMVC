package service;

import java.util.HashMap;
import java.util.Map;

import model.Customer;

//if�� �̿��ؼ� map�� ��ȸ�ؼ� custormer�� ����
/* Singleton */
public class CustomerService {
	private Map<String, Customer> customers;

	// class�� �ϳ��� �Ҵ��. ó������ �ʱ�ȭ
	private static final CustomerService instance = new CustomerService();

	// ���ο����� ������ ȣ�� ����
	private CustomerService() {
		customers = new HashMap<String, Customer>();
	}

	public static CustomerService getInstance() {
		return instance;
	}

	/** Map�� Customer�� �߰� */
	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	/** id�� ��ġ�ϴ� Customer�� �����ϴ��� ã��(ȸ������ �� �ߺ� ó��) */
	public Customer findCustomer(String id) {
		if (id != null) {
			return customers.get(id.toLowerCase());
		} else
			return null;
	}

	/** id�� password�� �ش��ϴ� Customer�� Map�� �����ϴ���, ���������� login�ϴ��� üũ */
	public Customer login(String id, String password) {
		if (id != null && password != null) {
			if (customers.get(id.toLowerCase()).getPassword().equals(password)) {
				return customers.get(id.toLowerCase());
			} 
			//��ġ�ϴ� Customer�� ����
			else {
				return null;
			}
		} 
		// ��� ������ �Է����� ����
		else {
			return null;
		}
	}
}
