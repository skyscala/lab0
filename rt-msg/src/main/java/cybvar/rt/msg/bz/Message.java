/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.rt.msg.bz;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author zeyarhtike
 */


@Setter @Getter
@Document(collection = "message")
public class Message {
    
    @Id
    private String id;
    
    private Map<String,Object> attrs;

    private String channel;
    
    
}
