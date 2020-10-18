package com.example.todoapp.config.couchbase;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.json.JsonValueModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.Duration;

@EnableConfigurationProperties({
        CouchbaseResource.class
})
@Configuration
@RequiredArgsConstructor
public class CouchbaseConfiguration {

    private final CouchbaseResource couchbaseResource;

    @Bean()
    public Cluster couchbaseCluster() {
        var objectMapper = new Jackson2ObjectMapperBuilder()
                .modules(new JsonValueModule())
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        var clusterEnvironment = ClusterEnvironment.builder()
                .jsonSerializer(JacksonJsonSerializer.create(objectMapper))
                .timeoutConfig(TimeoutConfig
                        .connectTimeout(couchbaseResource.getTimeout().getConnect())
                        .kvTimeout(couchbaseResource.getTimeout().getKv())
                        .viewTimeout(couchbaseResource.getTimeout().getView())
                        .queryTimeout(couchbaseResource.getTimeout().getQuery()))
                .build();

        return Cluster.connect(couchbaseResource.getHosts(), ClusterOptions
                .clusterOptions(couchbaseResource.getUsername(), couchbaseResource.getPassword())
                .environment(clusterEnvironment));
    }

    @Bean
    public Collection userCollection(Cluster couchbaseCluster) {
        couchbaseCluster.bucket(couchbaseResource.getBucketName()).waitUntilReady(Duration.ofSeconds(60));
        return couchbaseCluster.bucket(couchbaseResource.getBucketName()).defaultCollection();
    }
}