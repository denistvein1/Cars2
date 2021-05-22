import Domain.Car;
import Repository.Repository;
import Service.Service;
import Service.ServiceCart;
import UI.UIAdmin;
import UI.UIMain;
import UI.UIUser;

public class start {
    public static void main(String[] args) {
        Repository<Car> repository = new Repository<Car>();
        Service<Car> service = new Service<Car>(repository);
        initialize(service);
        ServiceCart<Car> serviceCart = new ServiceCart<Car>(repository);
        UIAdmin uiAdmin = new UIAdmin(service);
        UIUser uiUser = new UIUser(service, serviceCart);
        UIMain uiMain = new UIMain(uiAdmin, uiUser);
        uiMain.menu();
    }

    public static void initialize(Service<Car> service) {
        service.add(new Car("Porsche", "911 Turbo S", 2021, "https://www.youtube.com/watch?v=NYifTXGwCOM"));
        service.add(new Car("Mercedes-Benz", "A 250", 2020, "https://www.youtube.com/watch?v=PWspwo-ZW1k"));
        service.add(new Car("BMW", "M135i", 2021, "https://www.youtube.com/watch?v=qxrhqfDPdXM"));
        service.add(new Car("Dacia", "Duster", 2020, "https://www.youtube.com/watch?v=mp-5-_kRw0w"));
        service.add(new Car("Volkswagen", "Golf R", 2021, "https://www.youtube.com/watch?v=j9LEro19Ues"));
        service.add(new Car("Audi", "S3", 2019, "https://www.youtube.com/watch?v=ct8dAKKfqW0"));
        service.add(new Car("Renault", "Megane", 2021, "https://www.youtube.com/watch?v=lyFKOKvIye4"));
        service.add(new Car("Porsche", "993 GT2", 1996, "https://www.youtube.com/watch?v=adLhfYFYYlU"));
        service.add(new Car("Mercedes-Benz", "AMG GTR", 2020, "https://www.youtube.com/watch?v=ObE8KFDFmt8"));
        service.add(new Car("Škoda", "Octavia", 2021, "https://www.youtube.com/watch?v=SErOkO4UIXE"));
    }
}
