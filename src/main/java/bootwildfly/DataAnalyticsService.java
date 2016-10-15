package bootwildfly;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Service;

import kafka.Application;

@Service
public class DataAnalyticsService {
	
	
	/**
	 * Receive a Messag from Kafka
	 * @param solution
	 */
	public void receiveFromKafka() {
		ConfigurableApplicationContext context
		= new SpringApplicationBuilder(Application.class)
		.web(false)
		.run();
		
		PollableChannel fromKafka = context.getBean("received", PollableChannel.class);
		Message<?> received = fromKafka.receive(10000);
		while (received != null) {
			System.out.println(received);
			received = fromKafka.receive(10000);
			//Do Something
		}

		context.close();
	}
}
