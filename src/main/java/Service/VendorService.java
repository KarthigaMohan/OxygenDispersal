package Service;

import Domain.Vendor;
import Entity.City;
import Entity.State;
import Repository.VendorOxygenRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VendorService {

    public VendorService(VendorOxygenRepository vendorOxygenRepository) {
        this.citiesWithVendorsPresent = new HashSet<>();
        this.vendorOxygenRepository = vendorOxygenRepository;
    }

    private Set<City> citiesWithVendorsPresent;
    private VendorOxygenRepository vendorOxygenRepository;

    public Vendor allowVendorToBeRegisteredBasedOnState(int vendorId, City city, State state, int industrialOxygenCapacity, int medicalOxygenCapacity){
        if (citiesWithVendorsPresent.contains(city)) {
            throw new RuntimeException("Vendors are already present for this city.");
        }
        citiesWithVendorsPresent.add(city);
        Vendor vendor = new Vendor(vendorId, city, state, industrialOxygenCapacity, medicalOxygenCapacity);
        vendorOxygenRepository.addVendorInitial(vendor);
        return vendor;
    }


}
