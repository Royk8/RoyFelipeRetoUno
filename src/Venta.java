import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.Math.abs;

public class Venta {

    private String name;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}