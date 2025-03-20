package com.example.sneaker.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FavoriteId implements Serializable {

    private UUID user;
    private UUID product;

    public FavoriteId() {
    }

    public FavoriteId(UUID user, UUID product) {
        this.user = user;
        this.product = product;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(user, that.user) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, product);
    }
}