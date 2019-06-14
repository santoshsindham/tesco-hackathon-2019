package com.tesco.transport.deliverynotificaiton.deliverynotification.infrastructure.messaging;

import com.couchbase.client.core.env.QueryServiceConfig;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractReactiveCouchbaseConfiguration;
import java.util.List;

@Configuration
public class CouchbaseConfig extends AbstractReactiveCouchbaseConfiguration {

  @Value("${spring.couchbase.bucket.name}")
  private String bucketName;

  @Value("${spring.couchbase.bucket.password}")
  private String bucketPassword;

  @Value("#{'${spring.couchbase.bootstrap-hosts}'.split(',')}")
  private List<String> bootstrapHosts;


  @Value("${spring.couchbase.env.autorelease-after}")
  private long autoreleaseAfter;

  @Override
  public CouchbaseEnvironment getEnvironment() {

    return DefaultCouchbaseEnvironment.builder()
        .autoreleaseAfter(autoreleaseAfter)
        .build();
  }

  @Override
  protected List<String> getBootstrapHosts() {
    return bootstrapHosts;
  }

  @Override
  protected String getBucketName() {
    return bucketName;
  }

  @Override
  protected String getBucketPassword() {
    return bucketPassword;
  }
}
