package com.kerrishaus.ErosteyWorship.gods;

import com.kerrishaus.ErosteyWorship.gods.traits.Trait;

import java.util.List;

public class God
{
    public String name;
    public List<Trait> traits;

    public God(String name, List<Trait> traits)
    {
        this.name   = name;
        this.traits = traits;
    }
}
