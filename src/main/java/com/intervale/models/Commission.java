package com.intervale.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Commission {
    @XmlAttribute
    private int id;
    @XmlElement
    private Brand brand;
    @XmlElement
    private Currency currency;
    @XmlElement
    private double value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commission that = (Commission) o;

        if (Double.compare(that.getValue(), getValue()) != 0) return false;
        if (getBrand() != that.getBrand()) return false;
        return getCurrency() == that.getCurrency();

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getBrand().hashCode();
        result = 31 * result + getCurrency().hashCode();
        temp = Double.doubleToLongBits(getValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Commission() {
    }

    public Commission(int id, Brand brand, Currency currency, double value) {
        this.id = id;
        this.brand = brand;
        this.currency = currency;
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }
}
