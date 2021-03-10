package com.aecl.demo.entity;

public class FavPost {
    private Integer favouriteId;

    private Integer fpostId;

    private Integer fpersonId;

    public Integer getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Integer favouriteId) {
        this.favouriteId = favouriteId;
    }

    public Integer getFpostId() {
        return fpostId;
    }

    public void setFpostId(Integer fpostId) {
        this.fpostId = fpostId;
    }

    public Integer getFpersonId() {
        return fpersonId;
    }

    public void setFpersonId(Integer fpersonId) {
        this.fpersonId = fpersonId;
    }
}