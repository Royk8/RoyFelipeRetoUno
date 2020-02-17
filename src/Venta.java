import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Math.abs;

/** This class has the getters and setter about the sells and date methods
 */

public class Venta {

    private String name;
    private GregorianCalendar date;
    private double sellPrice;

    public Venta(String name, GregorianCalendar date, double sellPrice){
        this.name = name;
        this.date = date;
        this.sellPrice = sellPrice;
    }

    /** Getters and setters
     */

    public String getName() {
        return name;
    }

    public double getSellPrice(){
        return sellPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    /** Get the month about sales
     */

    public YearMonth getMonth(){
        return YearMonth.parse(printableDate(), DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
    }

    /** Print the date
     */

    public String printableDate(){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String date = simpleFormat.format(this.date.getTime());
        return date;
    }

    /** Convert the information to string
     */

    public String toString(){
        SimpleDateFormat dateFileFormat = new SimpleDateFormat("yyyy;MM;dd");
        String date = dateFileFormat.format(this.date.getTime());
        return name + ";" + date + ";" +sellPrice;
    }


}