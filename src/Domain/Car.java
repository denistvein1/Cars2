package Domain;

import java.awt.*;
import java.io.FileWriter;
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

    public String toStringNoLink() {
        return "Car{" +
                "company='" + company + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public String toString() {
        return company + ',' + model + ',' + year + ',' + link + "\n";
    }

    public static Car readFromFile(String line) throws NumberFormatException {
        String[] result = line.split(",");
        String company, model, link;
        int year;
        company = result[0];
        model = result[1];
        year = Integer.parseInt(result[2]);
        link = result[3];
        return new Car(company, model, year, link);
    }

    public static void writeToCsvFile(FileWriter file, Car car) throws IOException {
        file.write(car.toString());
    }

    public static Car readFromHtmlFile(String htmlObject) throws NumberFormatException {
        String[] result = htmlObject.split("\n");
        String company = result[2].split(">")[1].split("<")[0];
        String[] modelAndYear = result[3].split(">")[1].split("<")[0].split(", ");
        String model = modelAndYear[0];
        int year = Integer.parseInt(modelAndYear[1]);
        String link = result[4].split("=")[1].split(">")[0];
        return new Car(company, model, year, link);
    }

    public static void writeToHtmlFile(FileWriter file, Car car) throws IOException {
        file.write("<html>\n");
        file.write("<body>\n");
        file.write("<h2>" + car.getCompany() + "</h2>\n");
        file.write("<p>" + car.getModel() + ", " + car.getYear() + "</p>\n");
        file.write("<a href=" + car.getLink() + ">Learn more</a>\n");
        file.write("</body>\n");
        file.write("</html>\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return getYear() == car.getYear() && Objects.equals(getCompany(), car.getCompany()) && Objects.equals(getModel(), car.getModel());
    }
}
