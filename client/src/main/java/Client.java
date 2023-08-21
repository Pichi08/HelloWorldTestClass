import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws UnknownHostException {
        java.util.List<String> extraArgs = new java.util.ArrayList<>();

        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args,"config.client",extraArgs))
        {
            //com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");
            Demo.PrinterPrx twoway = Demo.PrinterPrx.checkedCast(
                communicator.propertyToProxy("Printer.Proxy")).ice_twoway().ice_secure(false);
            //Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
            Demo.PrinterPrx printer = twoway.ice_oneway();

            if(printer == null) {
                throw new Error("Invalid proxy");
            }

            Scanner sc = new Scanner(System.in);
            String message = "";
            boolean flag = true;

            while (flag) {
                String info = getInfo();
                System.out.print(info + ": ");
                message = sc.next();
                if (message.equals("exit")) {
                    flag = false;
                } else {
                    printer.printString(info + ":" + message);
                }
            }
        }
    }

    public static String getInfo() throws UnknownHostException {
        String username = System.getProperty("user.name");
        String hostname = java.net.InetAddress.getLocalHost().getHostName();
        return username + "@" + hostname;
    }
}