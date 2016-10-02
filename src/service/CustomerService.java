package service;

import java.util.HashMap;
import java.util.Map;

import model.Customer;

//if를 이용해서 map을 조회해서 custormer을 리턴
/* Singleton */
public class CustomerService {
	private Map<String, Customer> customers;

	// class에 하나만 할당됨. 처음에만 초기화
	private static final CustomerService instance = new CustomerService();

	// 내부에서만 생성자 호출 가능
	private CustomerService() {
		customers = new HashMap<String, Customer>();
	}

	public static CustomerService getInstance() {
		return instance;
	}

	/** Map에 Customer를 추가 */
	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	/** id와 일치하는 Customer가 존재하는지 찾기(회원가입 시 중복 처리) */
	public Customer findCustomer(String id) {
		if (id != null) {
			return customers.get(id.toLowerCase());
		} else
			return null;
	}

	/** id와 password에 해당하는 Customer가 Map에 존재하는지, 성공적으로 login하는지 체크 */
	public Customer login(String id, String password) {
		if (id != null && password != null) {
			if (customers.get(id.toLowerCase()).getPassword().equals(password)) {
				return customers.get(id.toLowerCase());
			} 
			//일치하는 Customer가 없음
			else {
				return null;
			}
		} 
		// 모든 정보를 입력하지 않음
		else {
			return null;
		}
	}
}
