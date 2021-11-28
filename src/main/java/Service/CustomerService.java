package Service;

import Domain.Customer;
import Entity.City;
import Entity.CustomerType;
import Entity.State;
import Repository.VendorOxygenRepository;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    Map<Integer, Customer> customerRepository;
    VendorOxygenRepository vendorOxygenRepository;


    public Map<Integer, Customer> getCustomerRepository() {
        return customerRepository;
    }

    public CustomerService(VendorOxygenRepository vendorOxygenRepository){
        this.vendorOxygenRepository = vendorOxygenRepository;
        this.customerRepository = new HashMap<>();
    }

    public Customer registerCustomer(int customerId, State state, City city, int maxRequirement, CustomerType customerType){
        Customer c =  new Customer(customerId, customerType, state, city, maxRequirement);
        customerRepository.put(c.getCustomerId(), c);
        return c;
    }

    public boolean bookOxygen(int customerId, int quantity){
        if (!customerRepository.containsKey(customerId)) {
            throw new RuntimeException("Customer not registered");
        }
        return this.vendorOxygenRepository.getOxygen(customerRepository.get(customerId).getCustomerType(), quantity);
    }



}
