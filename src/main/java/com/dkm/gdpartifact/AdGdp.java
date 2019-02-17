package com.dkm.gdpartifact;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class AdGdp
{
    private @Id @GeneratedValue Long id;
    private String country;
    private Long gdp;

    public AdGdp()
    {
    }

    public AdGdp(String country, Long gdp)
    {
        this.country = country;
        gdp = gdp;
    }

}
