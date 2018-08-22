package com.hazelcast.hazelcastcloudsample;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.spi.impl.discovery.HazelcastCloudDiscovery;
import com.hazelcast.client.spi.properties.ClientProperty;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.SSLConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.util.Properties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Properties props = new Properties();
		ClientConfig config = new ClientConfig();
		config.getNetworkConfig().setConnectionAttemptLimit(1000);
		config.getNetworkConfig().setSSLConfig(new SSLConfig().setEnabled(true).setProperties(props));
		config.setGroupConfig(new GroupConfig("<cluster_name>", "<group_password>"));
		config.setProperty(ClientProperty.HAZELCAST_CLOUD_DISCOVERY_TOKEN.getName(), "<token>");
		HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
		IMap map = client.getMap("map");
		for (int i = 0; i < 100; i++) {
			map.put(i, i);
			System.out.println(map.size());
		}
	}
}
