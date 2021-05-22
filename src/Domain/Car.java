package Domain;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Car {

    private String company, model, link;
    private int year;

    public Car(String company, String model, int year) {
        this.company = company;
        this.model = model;
        this.year = year;
        this.link = "----------";
    }

    public Car(String company, String model, int year, String link) {
        this(company, model, year);
        this.link = link;
    }

    public Car(Car car) {
        this.company = car.getCompany();
        this.model = car.getModel();
        this.year = car.getYear();
        this.link = car.getLink();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void openLink() throws URISyntaxException, IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        URI oURL = new URI(getLink());
        desktop.browse(oURL);
    }

    @Override
    public String toString() {
        return "Car{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return getYear() == car.getYear() && Objects.equals(getCompany(), car.getCompany()) && Objects.equals(getModel(), car.getModel());
    }
}
