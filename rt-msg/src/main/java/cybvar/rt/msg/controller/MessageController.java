/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.rt.msg.controller;

import cybvar.rt.msg.bz.Message;
import cybvar.rt.msg.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 *
 * @author zeyarhtike
 */

@RestController
@CrossOrigin
public class MessageController {
    
    @Autowired
    protected MessageRepo repo;
    
    
    
    @GetMapping(value = "/messages/retrieve_by/{channel}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> findByChannel(@PathVariable(value = "channel", required = true)String channel){
        Message message = new Message();
        message.setAttrs(new TreeMap<>());
        message.setChannel(channel);
        message.getAttrs().put("rec", UUID.randomUUID().toString());
        message.getAttrs().put("ping", true);
        return repo.save(message).map(e -> {            
            message.setAttrs(new TreeMap<>());
            message.setChannel(UUID.randomUUID().toString());
            message.getAttrs().put("rec", UUID.randomUUID().toString());
            return message;
        }).concatWith(repo.findByChannel(channel)).filter( e -> e.getChannel().equals(channel))
                .map(e -> {
                    e.getAttrs().put("msgId",e.getId());
                    e.getAttrs().put("channel",e.getChannel());
                    return ServerSentEvent.builder().id(e.getId()).event(channel).data(e.getAttrs()).build();
                });

    }
    
    
    
    @PostMapping(value="/message/add/{channel}")
    public Mono<Message> addMessage(
            @PathVariable(value = "channel", required = true) String channel,
            @RequestBody(required = false) Map payload){
    
        Message message = new Message();
        if(payload!=null){
            message.setAttrs(payload);
        }else{
            message.setAttrs(new TreeMap<>());
        }
        message.setChannel(channel);
        message.getAttrs().put("rec", UUID.randomUUID().toString());
        return repo.save(message);
                
    }
    
    @GetMapping(value="/message/get/{id}")
    public Mono<Message> retrieveMessage(
            @PathVariable(value = "id", required = true) String id){
    
        
        return repo.findById(id);
                
    }
}
