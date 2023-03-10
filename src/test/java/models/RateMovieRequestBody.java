package models;

public class RateMovieRequestBody {
    private double value;

    public RateMovieRequestBody(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
