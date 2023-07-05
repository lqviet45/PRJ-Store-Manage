package sample.shopping;


public class TeaError {
    private String idError;
    private String nameError;
    private String priceError;
    private String quantityError;
    private String imgError;

    public TeaError() {
    }

    public TeaError(String idError, String nameError, String priceError, String quantityError, String imgError) {
        this.idError = idError;
        this.nameError = nameError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.imgError = imgError;
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getImgError() {
        return imgError;
    }

    public void setImgError(String imgError) {
        this.imgError = imgError;
    }
}