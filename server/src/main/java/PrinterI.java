import com.zeroc.Ice.Current;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PrinterI implements Demo.Printer
{
    public void printString(String s, Current current)
    {

        String[] splitS = s.split(":");
        String username = splitS[0];
        s = splitS[1];

        if(containsNumber(s)){
            System.out.println(username + ":" + primeNumbers(s));
        } else if (s.equals("listifs")){
            System.out.println(username + ":" );
            consoleaSearch(s);
        } else if (s.equals("listports")){
            System.out.println(username + ":" );
            consoleaSearch(s);
        } else if (s.startsWith("!")) {
            System.out.println(username + ":" );
            consoleaSearch(s);
        } else {
            System.out.println(username + ":" + s);
        }

    }

    public void consoleaSearch(String s){

        String linea = "";
        s = s.replace("!", "");

        try {

            Runtime tiempoEjecucion = Runtime.getRuntime();
            Process proceso = tiempoEjecucion.exec("cmd.exe /C " + s);

            InputStream is = proceso.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();

        } catch (IOException ioe) {
        }
    }

    public boolean containsNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public ArrayList<Integer> primeNumbers(String s){

        int n = Integer.parseInt(s);
        ArrayList<Integer> primes = new ArrayList<>();
        boolean flag = true;

        if(n>1){
            for (int i = 1; i < n; i++) {
                for (int j = 2; j <= i / 2 && flag; ++j) {
                    if (i % j == 0) {
                        flag = false;
                    }
                }
                if(flag){
                    primes.add(i);
                }
                flag = true;
            }
        }

        return primes;

    }

}