package Repository;

import Domain.Vendor;
import Entity.CustomerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class VendorOxygenRepository {

    //store all current availability for each vendor
    Map<Vendor, Integer> medicalAvailability;
    Map<Vendor, Integer> industrialAvailability;
    Map<Vendor, Integer> totalAvailability;

    public Map<Vendor, Integer> getMedicalAvailability() {
        return medicalAvailability;
    }

    public Map<Vendor, Integer> getIndustrialAvailability() {
        return industrialAvailability;
    }

    public VendorOxygenRepository() {
        this.medicalAvailability = new HashMap<>();
        this.industrialAvailability = new HashMap<>();
        this.totalAvailability = new HashMap<>();
    }

    public Map<Vendor, Integer> getTotalAvailability() {
        return totalAvailability;
    }

    //stores vendors in decreasing order of availability
    PriorityQueue<Vendor> searchStoreMedical = new PriorityQueue<Vendor>(
            (a,b) -> medicalAvailability.get(b) - medicalAvailability.get(a));
    PriorityQueue<Vendor> searchStoreIndustry = new PriorityQueue<Vendor>(
            (a,b) -> medicalAvailability.get(b) - medicalAvailability.get(a));


    public void addVendorInitial(Vendor vendor){
        medicalAvailability.put(vendor, vendor.getMedicalOxygenCapacity());
        searchStoreMedical.add(vendor);
        industrialAvailability.put(vendor, vendor.getIndustrialOxygenCapacity());
        searchStoreIndustry.add(vendor);
        totalAvailability.put(vendor, vendor.getIndustrialOxygenCapacity() + vendor.getMedicalOxygenCapacity());
    }


    public void storeCurrentAvailability(CustomerType customerType, int oxygenToBeBooked){
        if (customerType == CustomerType.HOSPITAL){
            Vendor vendor = searchStoreMedical.poll();
            medicalAvailability.put(vendor, medicalAvailability.get(vendor)-oxygenToBeBooked);
            totalAvailability.put(vendor, totalAvailability.get(vendor)-oxygenToBeBooked);
            searchStoreMedical.add(vendor);
        }
        else if (customerType == CustomerType.INDUSTRY){
            Vendor vendor = searchStoreIndustry.poll();
            industrialAvailability.put(vendor, industrialAvailability.get(vendor)-oxygenToBeBooked);
            totalAvailability.put(vendor, totalAvailability.get(vendor)-oxygenToBeBooked);
            searchStoreIndustry.add(vendor);
        }
    }

    public void storeCurrentAvailability(Vendor v, int oxygenToBeBooked){

        List tmpList = new ArrayList<>();
        Vendor vendor = null;
        while (!searchStoreMedical.isEmpty()){
            vendor = searchStoreMedical.poll();
            if (vendor.getVendorId()!=v.getVendorId()){
                tmpList.add(vendor);
            }
            else break;
        }

        int medSupply = medicalAvailability.get(vendor);
        medicalAvailability.put(vendor, 0);
        totalAvailability.put(vendor, totalAvailability.get(vendor)-medSupply);
        searchStoreMedical.add(vendor);
        searchStoreMedical.addAll(tmpList);

        tmpList = new ArrayList<>();
        while (!searchStoreIndustry.isEmpty()){
            vendor = searchStoreIndustry.poll();
            if (vendor.getVendorId()!=v.getVendorId()){
                tmpList.add(vendor);
            }
            else break;
        }
        int left = oxygenToBeBooked - medSupply;
        industrialAvailability.put(vendor, industrialAvailability.get(vendor) - left);
        totalAvailability.put(vendor, totalAvailability.get(vendor)-left);
        searchStoreIndustry.add(vendor);
        searchStoreIndustry.addAll(tmpList);
    }


    public boolean getOxygen(CustomerType customerType, int oxygenToBeBooked){
        if (customerType == CustomerType.INDUSTRY){
            Vendor v = searchStoreIndustry.peek();
            if (industrialAvailability.get(v) >= oxygenToBeBooked){
                storeCurrentAvailability(customerType, oxygenToBeBooked);
                return true;
            }
            return false;
        }
        else if (customerType == CustomerType.HOSPITAL){
            Vendor v = searchStoreMedical.peek();
            if (medicalAvailability.get(v) >= oxygenToBeBooked){
                storeCurrentAvailability(customerType, oxygenToBeBooked);
                return true;
            }
            else {
                int max = 0;
                for (Vendor vendor: totalAvailability.keySet()){
                    if (totalAvailability.get(vendor) > max){
                        max = totalAvailability.get(vendor);
                        v = vendor;
                    }

                }
                if (max<oxygenToBeBooked) return false;
                storeCurrentAvailability(v,oxygenToBeBooked );
                return true;
            }
        }




        return false;

    }


}
