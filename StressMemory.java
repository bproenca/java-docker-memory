import java.util.ArrayList;
import java.util.List;

public class StressMemory {

    public static void main(String[] args) {
        StressMemory app = new StressMemory();
        app.consume();
    }

    private void consume() {
        List<Object> list = new ArrayList<Object>();
        int cnt = 0;
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            byte b[] = new byte[10485760]; //10mb
            list.add(b);
            printMemoryStatus(++cnt);
        }
    }

    private void printMemoryStatus(int cnt) {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        StringBuilder builder = new StringBuilder();
        builder.append("## ").append(cnt);
        builder.append("\tUsed Memory   : " + (runtime.totalMemory() - runtime.freeMemory()) / mb + " mb");
        builder.append("\tFree Memory   : " + runtime.freeMemory() / mb + " mb");
        builder.append("\tTotal Memory  : " + runtime.totalMemory() / mb + " mb");
        builder.append("\tMax Memory    : " + runtime.maxMemory() / mb + " mb");
        System.out.println(builder);
    }
}