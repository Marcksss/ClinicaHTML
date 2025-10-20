package HerramientasDesarrollo.Herramientas;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        long freeMemory = Runtime.getRuntime().freeMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long usedMemory = totalMemory - freeMemory;

        // Si el uso de memoria supera el 90%, consideramos que está en estado DOWN
        boolean isHealthy = ((double) usedMemory / maxMemory) < 0.9;

        if (isHealthy) {
            return Health.up()
                    .withDetail("usedMemory", usedMemory)
                    .withDetail("freeMemory", freeMemory)
                    .withDetail("maxMemory", maxMemory)
                    .build();
        } else {
            return Health.down()
                    .withDetail("usedMemory", usedMemory)
                    .withDetail("freeMemory", freeMemory)
                    .withDetail("maxMemory", maxMemory)
                    .build();
        }
    }
}
