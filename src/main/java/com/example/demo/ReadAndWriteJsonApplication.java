package com.example.demo;

import com.example.demo.Repository.MasterRepository;
import com.example.demo.Services.MasterService;
import com.example.demo.model.Master;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
class MasterList {
    List<Master> masterList;
}


@SpringBootApplication

@EnableAsync
@EnableScheduling
@EnableTransactionManagement
public class ReadAndWriteJsonApplication extends SpringBootServletInitializer {



    @Qualifier("customUserRepository")
    private static MasterRepository masterRepository;


    public static void main(String[] args) {
        SpringApplication.run(ReadAndWriteJsonApplication.class, args);
        TimerTaskUtil util = new TimerTaskUtil();
        util.callTimerTask();


    }


/*
    @Transactional(propagation= Propagation.REQUIRES_NEW)
     public void readFile() {
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<Master>> typeReference = new TypeReference<List<Master>>() {
        };

        InputStream inputStream = MasterList.class.getResourceAsStream("/json/Noticias.json");


        try {


            List<Master> users = mapper.readValue(inputStream, typeReference);
           // System.out.println("users " + users);

            for (Master m : users) {


                    masterService.saveOrUpdate(m);

            }




            System.out.println("Users Saved 3!");


        } catch (IOException e) {
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }
*/



    @Bean
    CommandLineRunner runner(MasterService masterService) {



        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<Master>> typeReference = new TypeReference<List<Master>>(){};

            InputStream inputStream = MasterList.class.getResourceAsStream("/json/Noticias.json");


            try {
                List<Master> users = mapper.readValue(inputStream, typeReference);
                // System.out.println("users " + users);

                for (Master m : users) {


                    masterService.saveOrUpdate(m);

                }
                System.out.println("user saved!");


            } catch (IOException e){
                System.out.println("Unable to save users: " + e.getMessage());
            }

        };
    }



}











