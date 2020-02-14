import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Caballo {

    Scanner read = new Scanner(System.in);

    private String name, breed, step, diet, diseases, fatherName, country;
    private int age, awards;
    private double price, spermDoseCost;
    private float weight;

    public Caballo(){

    }
    public Caballo(String name, String breed, String step, String diet, String diseases, String fatherName,
                   String country, int age, int awards, double price, float weight) {

        this.name = name;
        this.breed = breed;
        this.step = step;
        this.diet = diet;
        this.diseases = diseases;
        this.fatherName = fatherName;
        this.country = country;
        this.age = age;
        this.awards = awards;
        this.price = price;
        this.weight = weight;

        this.spermDoseCost = estimateDoseCost(price, diseases, age, awards);
    }

    /**
     * Method to compute the cost of spermDose from different parameters
     * @param price is used to compute a base cost for the spermDose
     * @param diseases is used to modify the cost of the spermDose
     * @param age is used to modify the cost of the spermDose
     * @param awards is used to modify the cost of the spermDose
     * @return the cost of spermDose as a double.
     */
    private double estimateDoseCost(double price, String diseases, int age, int awards){
            //  Se establece el coste base de la dosis en una 250va parte del coste del caballo.
        double spermDoseCost = price / 250f;
        double costBonus = 0;
        if(age > 2 && age < 9){
            /*  Se calcula un aumento hasta del 20% del coste por dosis segun la edad del caballo,
                siendo 5 su edad mas fertil.*/
            costBonus += spermDoseCost * ((20 - abs(5 - age)*5 ))/100f;
        }
        if (awards > 0 & awards <= 10){
            /* Se calcula un aumento de hasta el 50% del precio de la dosis si acorde a un numero de
                premios ganados en competiciones, hasta un maximo de 10. */
            costBonus += spermDoseCost * (awards * 5)/100f;
        }
        if(diseases != "Ninguna"){
            costBonus -= spermDoseCost * (0.3f);
        }

        return spermDoseCost + costBonus;
    }

    public void enterDataHorse (){
        Scanner userIn = new Scanner(System.in);
        System.out.println("Ingrese la informacion del caballo:");

    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getStep() {
        return step;
    }

    public String getDiet() {
        return diet;
    }

    public String getDiseases() {
        return diseases;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }

    public int getAwards() {
        return awards;
    }

    public double getPrice() {
        return price;
    }

    public double getSpermDoseCost() {
        return spermDoseCost;
    }

    public float getWeight() {
        return weight;
    }
}
