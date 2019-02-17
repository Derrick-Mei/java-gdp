package com.dkm.gdpartifact;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AdGdp
{
    private @Id @GeneratedValue Long id;
    private String country;
    private Long Gdp;

    public AdGdp()
    {
    }

    public AdGdp(String country, Long gdp)
    {
        this.country = country;
        Gdp = gdp;
    }

}
