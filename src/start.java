import Domain.Car;
import Repository.RepositoryFile;
import Service.Service;
import Service.ServiceCart;
import UI.UIAdmin;
import UI.UIMain;
import UI.UIUser;

import java.io.IOException;
import java.util.Scanner;

public class start {
    public static void main(String[] args) {
        create();
    }

    public static String readFileType() {
        System.out.println("Before starting the program, choose the file type you want to use: ");
        System.out.println("1. csv");
        System.out.println("2. html");
        String sChoice;
        String[] type = {"csv", "html"};
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice: ");
            sChoice = scanner.nextLine();
            try {
                choice = Integer.parseInt(sChoice);
            } catch (NumberFormatException e) {
                System.out.println(sChoice + " is not a number!");
                continue;
            }
            if(choice != 1 && choice != 2){
                System.out.println("Invalid input!");
                continue;
            }
            return type[choice - 1];
        }
    }

    public static void create() {
        String fileType = readFileType();
        String fileNameEntities = "D:\\JavaProjects\\A4\\src\\entities.csv";
        String fileNameCart = "D:\\JavaProjects\\A4\\src\\shoppingCart.";
        fileNameCart += fileType;
        RepositoryFile<Car> repositoryFileEntities;
        RepositoryFile<Car> repositoryFileCart;
        try {
            repositoryFileEntities = new RepositoryFile<Car>(fileNameEntities);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Something went wrong with the file " + fileNameEntities);
            e.printStackTrace();
            return;
        }
        try {
            repositoryFileCart = new RepositoryFile<Car>(fileNameCart);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Something went wrong with the file" + fileNameCart);
            e.printStackTrace();
            return;
        }
        Service<Car> service = new Service<>(repositoryFileEntities);
        ServiceCart<Car> serviceCart = new ServiceCart<>(repositoryFileEntities, repositoryFileCart);
        UIAdmin uiAdmin = new UIAdmin(service);
        UIUser uiUser = new UIUser(service, serviceCart);
        UIMain uiMain = new UIMain(uiAdmin, uiUser);
        uiMain.menu();
    }
}
