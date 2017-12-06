package com.example.tomasaluch.entregable2java.Model;

/**
 * Created by tomasaluch on 30/11/17.
 */

public class Paint {
    private String image;
    private String name;
    private String artistId;

    public String getArtistId() {
        return artistId;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
