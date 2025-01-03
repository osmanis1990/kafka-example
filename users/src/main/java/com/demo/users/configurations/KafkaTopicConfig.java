package com.demo.users.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateNewTopic() {
        return TopicBuilder.name(Topic.USER_CREATED.getValue())
                .partitions(2)
                .replicas(1)
                .configs(buildConfigurations())
                .build();
    }

    /**
     * Builds and returns a map of configuration properties for Kafka topic setup.
     * These configurations define properties such as retention policies,
     * log segment sizes, and message size limits for the topic.
     *
     * @return a map containing Kafka topic configuration properties as key-value pairs
     */
    private Map<String, String> buildConfigurations() {
        // Se crea un nuevo mapa para almacenar las configuraciones del tema.
        Map<String, String> configurations = new HashMap<>();

        // Configuración para la política de limpieza del tema. Los mensajes se eliminarán
        // cuando se cumpla la política de retención o cuando se alcance el tamaño máximo del segmento.
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);

        // Configuración del tiempo de retención de los mensajes en milisegundos. En este caso, se retendrán
        // los mensajes durante 24 horas (86400000 ms).
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");

        // Configuración del tamaño máximo de los segmentos de log en bytes. Si un segmento excede este tamaño,
        // se dividirá en un nuevo segmento. En este caso, se establece un límite de 1 GB (1073741824 bytes).
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");

        // Configuración del tamaño máximo de los mensajes permitidos en el tema. En este caso, se limita a 30 MB.
        // Si un mensaje supera este tamaño, será rechazado.
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "31457280");

        return configurations;
    }

}
