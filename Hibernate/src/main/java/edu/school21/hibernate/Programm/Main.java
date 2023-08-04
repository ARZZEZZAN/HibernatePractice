package edu.school21.hibernate.Programm;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.school21.hibernate.Entity.User;
import edu.school21.hibernate.Exceptions.UserParametersException;
import edu.school21.hibernate.Hibernate.UserHelper;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UserParametersException, IOException {
//        org.openjdk.jmh.Main.main(args);
          /* ---------------------------------------------- Doesn't Work ---------------------------------------------- */
        /* ----------------------- ALL USERS WITH THEIR ROOMS AND MESSAGES WITH JSON SERIALIZATION ----------------------- */
        String filePath = "user.ser";

        // Сериализация объекта User
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (User user : UserHelper.getUserList()) {
                oos.writeObject(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            User deserializedUser = (User) ois.readObject();
            System.out.println("Объект User успешно десериализован:");
            System.out.println("Имя: " + deserializedUser.getLogin());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Benchmark
    public void init() {
        List<User> users = UserHelper.getUserList();
        System.out.println(users);
    }
}