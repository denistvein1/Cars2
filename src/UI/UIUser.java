package UI;

import Domain.Car;
import Service.Service;
import Service.ServiceCart;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
/*        try {
            currentEntity.openLink();
        } catch (URISyntaxException e) {
            System.out.println("Invalid URL link: " + currentEntity.getLink() + "!");
        } catch (IOException e) {
            System.out.println("Failed to launch browser!");
        }*/
    }

    private void printNext() throws UnsupportedOperationException {
        if (!service.areElements()) {
            throw new UnsupportedOperationException();
        }
        try {
            currentEntity = iterator.next();
        } catch (NoSuchElementException | ConcurrentModificationException e) {
            iterator = service.getAll().iterator();
            currentEntity = iterator.next();
        }
        printEntity();
    }

    private void buyEntity() throws IOException {
        serviceCart.add(currentEntity);
        System.out.println(currentEntity.toStringNoLink() + " bought\n");
    }

    private void iterateEntities() {
        try {
            printNext();
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
                printNext();
                iterateEntitiesOptions();
            } else if (choice == 2) {
                try {
                    buyEntity();//Always in this order
                } catch (IOException e) {
                    System.out.println("Something went wrong with the file");
                    e.printStackTrace();
                } catch (UnsupportedOperationException e) {
                    System.out.println(currentEntity.toStringNoLink() + " already bought\n");
                    return;
                }
                try {
                    printNext();
                } catch (UnsupportedOperationException e) {
                    System.out.println("No cars available!");
                    return;
                }
                iterateEntitiesOptions();
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    private void printShoppingCart() {
        try {
            serviceCart.open();
        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private void clearShoppingCart() {
        if (!serviceCart.areElements()) {
            System.out.println("Shopping cart is already empty");
            return;
        }
        try {
            serviceCart.clear();
        } catch (IOException e) {
            System.out.println("Something went wrong with the file");
            e.printStackTrace();
        }
        System.out.println("Shopping cart cleared");
    }

    @Override
    public void introduction() {
        System.out.println("Here are your options for the user mode: ");
    }

    private void iterateEntitiesOptions() {
        System.out.println("0. Back");
        System.out.println("1. Next");
        System.out.println("2. Buy");
    }

    @Override
    public void options() {
        System.out.println("0. Back");
        System.out.println("1. Iterate through all the cars");
        System.out.println("2. Display shopping cart");
        System.out.println("3. Clear shopping cart");
        System.out.println("4. Print options again");
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
            } else if (choice == 3) {
                clearShoppingCart();
            } else if (choice == 4) {
                options();
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
