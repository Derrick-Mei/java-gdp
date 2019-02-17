package com.dkm.gdpartifact;

public class AeCountryNotFoundException extends RuntimeException
{
    public AeCountryNotFoundException(String country)
    {
        super("Could not find country: " + country);
    }
}
