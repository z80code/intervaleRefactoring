package com.intervale.models.modelsXML;

import com.intervale.models.Commission;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "commissions")
@XmlAccessorType(XmlAccessType.NONE)
public class Commissions {
    @XmlElement(name = "commission")
    private List<Commission> commissions;

    public Commissions() {
    }

    public Commissions(List<Commission> commissions) {
        this.commissions = commissions;
    }
    public void setCommissions(List<Commission> commissions) {
        this.commissions = commissions;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }
}
