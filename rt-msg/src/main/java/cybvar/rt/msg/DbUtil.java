/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cybvar.rt.msg;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.config.Storage;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

/**
 *
 * @author zeyarhtike
 */
public class DbUtil {
    
    
    private static MongodExecutable mongodExe;
    private static MongodProcess mongod;


    private static String database = "database";
    
    public static void startDb(){
        try {
            
            MongodStarter starter = MongodStarter.getDefaultInstance();
            Storage replication = new Storage(database, null, 0);
            mongodExe = starter.prepare(new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION).replication(replication)
                    .net(new Net("localhost", 27000, Network.localhostIsIPv6()))
                    .build());
            mongod = mongodExe.start();

        } catch (Exception ex) {
            try {
                if (mongod != null) {
                    mongod.stop();
                }
            } catch (Exception e) {
                //ingore
            }
            try {
                if (mongodExe != null) {
                    mongodExe.stop();
                }
            } catch (Exception e) {
                //ignore
            }

        }
        
        
    }
    
    public static void stopDb(){
        try {
            if (mongod != null) {
                mongod.stop();
            }
        } catch (Exception e) {
            //ingore
        }
        try {
            if (mongodExe != null) {
                mongodExe.stop();
            }
        } catch (Exception e) {
            //ignore
        }        
    }
}
