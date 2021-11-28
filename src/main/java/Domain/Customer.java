package Domain;

import Entity.City;
import Entity.CustomerType;
import Entity.State;

public class Customer {

    public Customer(int customerId, CustomerType customerType, State state, City city, int maxRequirement) {
        this.customerId = customerId;
        this.customerType = customerType;
        this.state = state;
        this.city = city;
        this.maxRequirement = maxRequirement;
    }

    private int customerId;
    private CustomerType customerType;
    private State state;
    private City city;
    private int maxRequirement;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getMaxRequirement() {
        return maxRequirement;
    }

    public void setMaxRequirement(int maxRequirement) {
        this.maxRequirement = maxRequirement;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerType=" + customerType +
                ", state=" + state +
                ", city=" + city +
                ", maxRequirement=" + maxRequirement +
                '}';
    }
}
