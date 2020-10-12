package com.example.todoapp.config.couchbase;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "couchbase")
public class CouchbaseResource {

    private String hosts;
    private String username;
    private String password;
    private String bucketName;
    private CouchbaseConnectionTimeout timeout = new CouchbaseConnectionTimeout();

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public CouchbaseConnectionTimeout getTimeout() {
        return timeout;
    }

    public void setTimeout(CouchbaseConnectionTimeout timeout) {
        this.timeout = timeout;
    }

    static class CouchbaseConnectionTimeout {
        private Duration connect = Duration.ofSeconds(15);
        private Duration kv = Duration.ofSeconds(15);
        private Duration view = Duration.ofSeconds(15);
        private Duration query = Duration.ofSeconds(15);

        public Duration getConnect() {
            return connect;
        }

        public void setConnect(Duration connect) {
            this.connect = connect;
        }

        public Duration getKv() {
            return kv;
        }

        public void setKv(Duration kv) {
            this.kv = kv;
        }

        public Duration getView() {
            return view;
        }

        public void setView(Duration view) {
            this.view = view;
        }

        public Duration getQuery() {
            return query;
        }

        public void setQuery(Duration query) {
            this.query = query;
        }
    }
}
