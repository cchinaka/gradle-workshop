package ng.com.workshop.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


// @Configuration
// @EnableMongoRepositories(basePackages = { "ng.com.workshop.business.data.mongo" })
// @PropertySource("classpath:app.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.database.name:MongoWorkshopDB}")
    private String databaseName;

    @Value("${mongo.database.username:mongoAdmin}")
    private String username;

    @Value("${mongo.database.password:mongoAdminPassword}")
    private String password;

    @Value("${mongo.server.name:localhost}")
    private String serverName;

    @Value("${mongo.server.port:80}")
    private Integer port;


    @Override
    protected String getDatabaseName() {
        return databaseName;
    }


    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credential = MongoCredential.createMongoCRCredential(username, databaseName, password.toCharArray());
        return new MongoClient(new ServerAddress(serverName, port), Collections.singletonList(credential));
    }
}
