package edu.school21.hibernate.Programm;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.school21.hibernate.Entity.User;
import edu.school21.hibernate.Exceptions.UserParametersException;
import edu.school21.hibernate.Hibernate.UserHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws UserParametersException {
          /* ---------------------------------------------- Doesn't Work ---------------------------------------------- */
        /* ----------------------- ALL USERS WITH THEIR ROOMS AND MESSAGES WITH JSON SERIALIZATION ----------------------- */
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.json"))) {
            List<User> users = UserHelper.getUserList();
            writer.write("[");
                for(User user :  users) {
                    String json = objectMapper.writeValueAsString(user);
                    if(user == users.get(users.size()-1)) {
                        writer.write(json + "]");
                    } else {
                        writer.write(json + ",\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}