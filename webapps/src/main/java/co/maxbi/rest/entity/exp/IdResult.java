package co.maxbi.rest.entity.exp;

import com.google.gson.annotations.SerializedName;


public class IdResult {
    @SerializedName("ID")
    private int idCategory;

    public IdResult(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "IdResult{" +
                "idCategory=" + idCategory +
                '}';
    }
}

