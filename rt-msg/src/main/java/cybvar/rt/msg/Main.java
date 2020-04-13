/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.rt.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


/**
 *
 * @author zeyarhtike
 */

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Main {
    
    
    @Autowired
    protected MongoTemplate template;
    
    
    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
    }


    
    @EventListener(ApplicationReadyEvent.class)
    protected void createCappedCollection(){
        String messageCol="message";
        if(!template.collectionExists(messageCol)){
            //template.dropCollection(messageCol);
            int numberOfDoc=1000;
            long sizeOfDoc=1024l*1024l;
            long totalSize=sizeOfDoc*numberOfDoc;
            template.createCollection(messageCol, CollectionOptions.empty().capped().size(totalSize).maxDocuments(numberOfDoc));
        }
    }
    
    @EventListener(ApplicationPreparedEvent.class)
    protected void startDb(){
        DbUtil.startDb();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                DbUtil.stopDb();
            }
        }));
    }
    
    
}
