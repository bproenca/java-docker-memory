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
        long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
        long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
        System.out.println("Initial Memory (Xms) : " + xms + "mb");
        System.out.println("Max Memory (Xmx) : " + xmx + "mb");
    }
}