package Controller;

import Domain.Customer;
import Entity.City;
import Entity.CustomerType;
import Entity.State;
import Repository.VendorOxygenRepository;
import Service.CustomerService;

public class CustomerController {

    VendorOxygenRepository vendorOxygenRepository;
    CustomerService customerService;

    public CustomerController(VendorOxygenRepository vendorOxygenRepository){
        customerService = new CustomerService(vendorOxygenRepository);
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Customer registerCustomer(int customerId, State state, City city, int maxRequirement, CustomerType customerType){
        return customerService.registerCustomer(customerId, state, city, maxRequirement, customerType);
    }

    public boolean bookOxygen(int customerId, int quantity){
        return customerService.bookOxygen(customerId, quantity);
    }


}
