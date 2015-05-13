package com.london.housing.model;

/**
 * @author smith
 */
public class Ward {

    private String wardName;

    private String code;

    private String transport;

    private String health;

    private String housePrice;

    private String crimeRate;


    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }

    public String getCrimeRate() {
        return crimeRate;
    }

    public void setCrimeRate(String crimeRate) {
        this.crimeRate = crimeRate;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }
}
