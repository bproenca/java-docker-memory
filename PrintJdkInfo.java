import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class PrintJdkInfo {

    public static void main(String[] args) throws IOException {
        PrintJdkInfo app = new PrintJdkInfo();
        app.memory();
    }

    private void memory() {
        int mb = 1024 * 1024;
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
        long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
        System.out.println("Initial Memory (xms) : " + xms + "mb");
        System.out.println("Max Memory (xmx) : " + xmx + "mb");
    }
}