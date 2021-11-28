package Domain;

import Entity.City;
import Entity.State;

/*
 Each vendor is assumed to be supplying both medical & industrial grade oxygen

 */
public class Vendor {

    private int vendorId;
    private City city;
    private State state;
    private int industrialOxygenCapacity;
    private int medicalOxygenCapacity;


    public Vendor(int vendorId, City city, State state, int industrialOxygenCapacity, int medicalOxygenCapacity) {
        this.vendorId = vendorId;
        this.city = city;
        this.state = state;
        this.industrialOxygenCapacity = industrialOxygenCapacity;
        this.medicalOxygenCapacity = medicalOxygenCapacity;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getIndustrialOxygenCapacity() {
        return industrialOxygenCapacity;
    }

    public void setIndustrialOxygenCapacity(int industrialOxygenCapacity) {
        this.industrialOxygenCapacity = industrialOxygenCapacity;
    }

    public int getMedicalOxygenCapacity() {
        return medicalOxygenCapacity;
    }

    public void setMedicalOxygenCapacity(int medicalOxygenCapacity) {
        this.medicalOxygenCapacity = medicalOxygenCapacity;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorId=" + vendorId +
                ", city=" + city +
                ", state=" + state +
                ", industrialOxygenCapacity=" + industrialOxygenCapacity +
                ", medicalOxygenCapacity=" + medicalOxygenCapacity +
                '}';
    }
}
