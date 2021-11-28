package Application;

import Controller.CustomerController;
import Controller.GovernmentController;
import Controller.VendorController;
import Entity.City;
import Entity.CustomerType;
import Entity.State;
import Repository.VendorOxygenRepository;

public class ApplicationMain {

    public static void main(String... args){

        VendorOxygenRepository vendorOxygenRepository = new VendorOxygenRepository();
        CustomerController customerController = new CustomerController(vendorOxygenRepository);
        VendorController vendorController = new VendorController(vendorOxygenRepository);
        GovernmentController governmentController = new GovernmentController(vendorOxygenRepository, customerController.getCustomerService());

        customerController.registerCustomer(1, State.KARNATAKA, City.BANGALORE, 30, CustomerType.HOSPITAL);
        customerController.registerCustomer(2, State.KARNATAKA, City.BANGALORE, 25, CustomerType.HOSPITAL);
        customerController.registerCustomer(3, State.KARNATAKA, City.MYSORE, 50, CustomerType.INDUSTRY);

        vendorController.registerVendor(1, City.BANGALORE,State.KARNATAKA, 120, 200);
        vendorController.registerVendor(2, City.CHENNAI, State.TAMILNADU ,120, 120);
        //err check
        //vendorController.registerVendor(1, City.BANGALORE,State.KARNATAKA, 120, 120);

        System.out.println("response: "+ customerController.bookOxygen(1,240 ));

        System.out.println(governmentController.showHospitalsInCity(City.BANGALORE));
        System.out.println(governmentController.showIndustryInCity(City.BANGALORE));
        System.out.println(governmentController.showVendorsInState(State.KARNATAKA));
        System.out.println(governmentController.showVendorsInState(State.TAMILNADU));


        System.out.println("Industry: " + vendorOxygenRepository.getIndustrialAvailability());
        System.out.println("Medical: " +vendorOxygenRepository.getMedicalAvailability());


    }
}
