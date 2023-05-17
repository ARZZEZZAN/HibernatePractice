package edu.school21.hibernate.Programm;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.school21.hibernate.Entity.User;
import edu.school21.hibernate.Hibernate.UserHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.json"))) {
            List<User> users = UserHelper.getUserList();
            writer.write("[");
                for(User user :  users) {
                    String json = objectMapper.writeValueAsString(user);
                    if(user == users.get(users.size()-1)) {
                        writer.write(json + "\n");
                    } else {
                        writer.write(json + ",\n");
                    }
                }
            writer.write("]");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}