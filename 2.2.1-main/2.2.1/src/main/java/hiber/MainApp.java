package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      CarService carService = context.getBean(CarService.class);

      carService.addCar(new Car("BMW-I8", 12345));
      carService.addCar(new Car("Mercedes e-class", 12345));
      carService.addCar(new Car("Mercedes s-class", 16665));
      carService.addCar(new Car("Mercedes Maybach", 77777));

      List<Car> cars = carService.listCars();

      userService.addUser(new User("User1", "Lastname1", "user1@mail.ru", cars.get(0)));
      userService.addUser(new User("User2", "Lastname2", "user2@mail.ru", cars.get(1)));
      userService.addUser(new User("User3", "Lastname3", "user3@mail.ru", cars.get(2)));
      userService.addUser(new User("User4", "Lastname4", "user4@mail.ru", cars.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("car = " + user.getCar().getModel());
      }
      User findedUser = userService.getUserCar("Mercedes e-class", 12345);
      System.out.println(findedUser.getFirstName());
      context.close();
   }
}
