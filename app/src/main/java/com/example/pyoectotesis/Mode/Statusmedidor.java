package com.example.pyoectotesis.Mode;

import android.renderscript.Sampler;

public class Statusmedidor {
    private String Value;
    public Statusmedidor(){

    }
    public Statusmedidor(String Value) {
        this.Value = Value;

    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
