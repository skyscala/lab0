/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.rt.msg.repo;

import cybvar.rt.msg.bz.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 *
 * @author zeyarhtike
 */
@Repository
public interface MessageRepo extends ReactiveMongoRepository<Message,String>{

    @Tailable    
    public Flux<Message> findByChannel(String channel);
        

    @Tailable
    public Flux<Message> findWithTailableCursorBy();
    
    
}
