package com.bernardo.ai_gateway;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(GlobalExceptionHandler.class)
public class AiGatewayApplication {

	public static void main(String[] args) {
		Map<String, Object> defaultProps = new HashMap<>();
		defaultProps.put("ai.engine.base-url", "http://localhost:11434");
		defaultProps.put("ai.engine.model", "llama3");
		defaultProps.put("ai.engine.timeout-seconds", "30");

		new SpringApplicationBuilder(AiGatewayApplication.class)
				.properties(defaultProps)
				.build()
				.run(args);
	}
}
