package com.dkm.gdpartifact;

public class AeCountryNotFoundException extends RuntimeException
{
    public AeCountryNotFoundException(Long id)
    {
        super("Could not find country with id: " + id);
    }
}
