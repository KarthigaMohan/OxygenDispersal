package Controller;

import Domain.Customer;
import Domain.Vendor;
import Entity.City;
import Entity.CustomerType;
import Entity.State;
import Repository.VendorOxygenRepository;
import Service.CustomerService;

import java.util.*;
import java.util.stream.Collectors;

public class GovernmentController {
    private VendorOxygenRepository vendorOxygenRepository;
    private CustomerService customerService;

    public GovernmentController(VendorOxygenRepository vendorOxygenRepository, CustomerService customerService) {
        this.vendorOxygenRepository = vendorOxygenRepository;
        this.customerService = customerService;

    }

    public Queue<Vendor> viewVendors(){
        Map<Vendor, Integer> total = vendorOxygenRepository.getTotalAvailability();
        PriorityQueue<Vendor> viewTotal =  new PriorityQueue<>(
                (a,b) -> total.get(b)-total.get(a)
        );
        viewTotal.addAll(total.keySet());
        return viewTotal;
    }

    public List<Customer> showHospitalsInCity(City city){
        Map<Integer, Customer> repo = customerService.getCustomerRepository();
        return repo.values().stream().filter(i->i.getCustomerType()== CustomerType.HOSPITAL).filter(i->i.getCity() == city).collect(Collectors.toList());
    }

    public Map<Vendor, Integer> showVendorsInState(State state){
        Map<Vendor, Integer> repo = vendorOxygenRepository.getTotalAvailability();
        Map<Vendor, Integer> availability = new HashMap<>();
        List<Vendor> list = repo.keySet().stream().filter(i->i.getState() == state).collect(Collectors.toList());
        for (Vendor v: list){
            availability.put(v, repo.get(v));
        }
        return availability;
    }

    public List<Customer> showIndustryInCity(City city){
        Map<Integer, Customer> repo = customerService.getCustomerRepository();
        return repo.values().stream().filter(i->i.getCustomerType()== CustomerType.INDUSTRY).filter(i->i.getCity() == city).collect(Collectors.toList());
    }
}
