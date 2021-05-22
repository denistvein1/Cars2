package UI;

import Domain.Car;
import Service.Service;
import Service.ServiceCart;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Scanner;

public class UIUser implements UII {

    private final Service<Car> service;
    private final ServiceCart<Car> serviceCart;
    private Iterator<Car> iterator = null;
    private final Scanner scanner;
    private Car currentEntity;

    public UIUser(Service<Car> service, ServiceCart<Car> serviceCart) {
        this.service = service;
        this.serviceCart = serviceCart;
        scanner = new Scanner(System.in);
        currentEntity = null;
    }

    private void printEntity() {
        System.out.println(currentEntity);
        try {
            currentEntity.openLink();
        } catch (URISyntaxException e) {
            System.out.println("Invalid URL link: " + currentEntity.getLink() + "!");
        } catch (IOException e) {
            System.out.println("Failed to launch browser!");
        }
    }

    private void buyEntity() {
        serviceCart.add(currentEntity);
        System.out.println(currentEntity + " bought");
    }

    private void iterateEntities() {
        try {
            currentEntity = service.getNext(iterator);
            printEntity();
        } catch (UnsupportedOperationException e) {
            System.out.println("No cars available!");
            return;
        }
        iterateEntitiesOptions();
        while (true) {
            if (!service.areElements()) {
                System.out.println("No cars available!");
                return;
            }
            int choice;
            String sChoice;
            System.out.println("Enter your choice: ");
            sChoice = scanner.nextLine();
            try {
                choice = Integer.parseInt(sChoice);
            } catch (NumberFormatException e) {
                System.out.println(sChoice + " is not a number!");
                continue;
            }
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                currentEntity = service.getNext(iterator);
                printEntity();
            } else if (choice == 2) {
                buyEntity();
            } else if (choice == 3) {
                iterateEntitiesOptions();
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    private void printShoppingCart() {
        for (Car entity : serviceCart.getAll()) {
            System.out.println(entity);
        }
    }

    @Override
    public void introduction() {
        System.out.println("Here are your options for the user mode: ");
    }

    private void iterateEntitiesOptions() {
        System.out.println("0. Back");
        System.out.println("1. Next");
        System.out.println("2. Buy");
        System.out.println("3. Print options again");
    }

    @Override
    public void options() {
        System.out.println("0. Back");
        System.out.println("1. Iterate through all the cars");
        System.out.println("2. Display shopping cart");
        System.out.println("3. Print options again");
    }

    @Override
    public void menu() {
        options();
        while (true) {
            int choice;
            String sChoice;
            System.out.println("Enter your choice: ");
            sChoice = scanner.nextLine();
            try {
                choice = Integer.parseInt(sChoice);
            } catch (NumberFormatException e) {
                System.out.println(sChoice + " is not a number!");
                continue;
            }
            if (choice == 0) {
                break;
            } else if (choice == 1) {
                iterator = service.getAll().iterator();
                iterateEntities();
                options();
            } else if (choice == 2) {
                printShoppingCart();
                options();
            } else if (choice == 3) {
                options();
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
