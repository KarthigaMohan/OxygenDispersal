package Controller;

import Domain.Vendor;
import Entity.City;
import Entity.State;
import Repository.VendorOxygenRepository;
import Service.VendorService;

public class VendorController {

    private VendorOxygenRepository vendorOxygenRepository;
    private VendorService vendorService;

    public VendorController(VendorOxygenRepository vendorOxygenRepository) {
        this.vendorService = new VendorService(vendorOxygenRepository);
    }

    public Vendor registerVendor(int vendorId, City city, State state, int industrialOxygenCapacity, int medicalOxygenCapacity){
        return vendorService.allowVendorToBeRegisteredBasedOnState(vendorId, city, state, industrialOxygenCapacity, medicalOxygenCapacity);
    }

    public VendorService getVendorService() {
        return vendorService;
    }
}
