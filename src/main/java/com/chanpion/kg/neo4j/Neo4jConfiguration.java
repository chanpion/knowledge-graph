package com.chanpion.kg.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.net.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author April Chen
 * @date 2020/1/13 15:08
 */
@Configuration
public class Neo4jConfiguration {

    @Value("${neo4j.uri}")
    private String uri;
    @Value("${neo4j.username}")
    private String username;
    @Value("${neo4j.password}")
    private String password;

    @Bean
    public Driver neo4jDriver() {
        String[] hosts = uri.split(",");

        Driver driver;
        if (hosts.length > 1) {
            Set<ServerAddress> serverAddresses = new HashSet<>(3);
            for (String host : hosts) {
                serverAddresses.add(ServerAddress.of(host, 7687));
            }
            Config config = Config.builder()
                    .withMaxConnectionPoolSize(50)
                    .withMaxConnectionLifetime(30, TimeUnit.MINUTES)
                    .withConnectionAcquisitionTimeout(2, TimeUnit.MINUTES)
                    .withoutEncryption()
                    .withResolver(address -> serverAddresses)
                    .withTrustStrategy(Config.TrustStrategy.trustAllCertificates())
                    .toConfig();
            driver = GraphDatabase.driver("bolt+routing://kg.graph.com", AuthTokens.basic(username, password), config);
        } else {
            driver = GraphDatabase.driver("bolt://" + uri + ":7687", AuthTokens.basic(username, password));
        }
        return driver;
    }
}
