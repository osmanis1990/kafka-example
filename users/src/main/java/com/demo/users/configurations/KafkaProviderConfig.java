package com.demo.users.configurations;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProviderConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    /**
     * Constructs and returns a map containing the configuration properties for a Kafka producer.
     * These configurations include details about the Kafka broker servers and serializers for
     * message keys and values.
     *
     * @return a map of producer configuration properties (key: value)
     */
    private Map<String, Object> producerConfig() {
        // Se crea un nuevo mapa para almacenar las configuraciones del productor.
        Map<String, Object> properties = new HashMap<>();

        // Configuración de la dirección de los servidores Kafka. Esta propiedad especifica
        // los brokers de Kafka a los que se conectará el productor. Se utiliza el valor de
        // la variable 'bootstrapServers', que debe contener las direcciones de los servidores.
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Configuración de la clase serializadora para las claves. En este caso, se está utilizando
        // 'StringSerializer' para serializar las claves de los mensajes como cadenas de texto (String).
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Configuración de la clase serializadora para los valores. Similar a las claves, se utiliza
        // 'JsonSerializer' para serializar los valores de los mensajes como cadenas de texto (String).
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        return properties;
    }

}
