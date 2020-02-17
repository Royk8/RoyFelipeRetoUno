import java.io.*;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.*;

/** This class has the methods to interact to the user with the inventory and sells
  */

public class Menu {
    Scanner read = new Scanner(System.in);
    //Formato necesario para impresion de cantidades de dinero
    NumberFormat money = NumberFormat.getCurrencyInstance();
    ArrayList<Caballo> horsesList;
    ArrayList<String> breedList;
    ArrayList<String> stepList;
    ArrayList<String> diseaseList;
    ArrayList<Venta> sellsList;
    ArrayList<YearMonth> monthsList;

    /**
     * Menu constructor, its function is instantiate list and read the text file
     * @throws IOException
     */
    public Menu() throws IOException{
        horsesList = new ArrayList<Caballo>();
        breedList = new ArrayList<String>();
        stepList = new ArrayList<String>();
        diseaseList = new ArrayList<String>();
        diseaseList.add("Ninguna");
        sellsList = new ArrayList<Venta>();
        monthsList = new ArrayList<YearMonth>();
        readDB();

    }

    /** Welcome message & show the submenus menuInvenario()
     * and menuVentas()
     */

    public void menu()throws IOException{
        int option = 0;
        do{
            System.out.println("EL HATO COBRA\nBienvenido al administrador de inventario.");
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tAdministrar inventario");
            System.out.println("2)\tVentas");
            System.out.println("0)\tSALIR");
            option = this.readInt(0,2);
            switch (option) {
                case 1:
                    menuInventario();
                    break;
                case 2:
                    menuVentas();
                    break;
            }
        }while(option != 0);
        saveChanges();
        System.out.println("Hasta luego.");
    }

    /** Submenu to control the functions about the horse inventory
     */

    void menuInventario(){
        int option1 = 0;
        do{
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tVer Caballos");
            System.out.println("2)\tIngresa nuevo Caballo");
            System.out.println("3)\tModificar informacion de un Caballo");
            System.out.println("4)\tBorrar un Caballo");
            System.out.println("0)\tMenu principal");
            option1 = this.readInt(0,4);
            switch (option1) {
                case 1:
                    checkHorses();
                    break;
                case 2:
                    addHorse();
                    break;
                case 3:
                    editHorse();
                    break;
                case 4:
                    deleteHorse();
                    break;
            }
        }while(option1 != 0);
    }

    /**
     * Submenu to control the functions about the sells
     */
    void menuVentas(){
        int option2 = 0;
        do{
            System.out.println("Seleccione la tarea que desea realizar:");
            System.out.println("1)\tConsultar ventas");
            System.out.println("2)\tRegistrar venta");
            System.out.println("3)\tConsultar caballo mas preciado");
            System.out.println("0)\tMenu principal");

            option2 = this.readInt(0,3);
            switch (option2) {
                case 1:
                    checkSells();
                    break;
                case 2:
                    addSell();
                    break;
                case 3:
                    mostLovedHorse();
                    break;
            }
        }while(option2 != 0);

    }

    /** This method print the information about the selected horse
     */

    public void checkHorses(){
        if(horsesList.size() == 0){
            System.out.println("No se encuentra ni un caballo en el registro");
            return;
        }
        System.out.println("Seleccione el Caballo del que desea consultar su informacion: ");
        printHorses();
        int optionIn = readInt(1, horsesList.size())-1;
        System.out.println("La informacion del Caballo seleccionado es: ");
        System.out.println("Nombre: "+horsesList.get(optionIn).getName());
        System.out.println("Raza: "+horsesList.get(optionIn).getBreed());
        System.out.println("Paso: "+horsesList.get(optionIn).getStep());
        System.out.println("Dieta: "+horsesList.get(optionIn).getDiet());
        System.out.println("Enfermedad: "+horsesList.get(optionIn).getDiseases());
        System.out.println("Nombre del padre: "+horsesList.get(optionIn).getFatherName());
        System.out.println("Pais: "+horsesList.get(optionIn).getCountry());
        System.out.println("Edad: "+horsesList.get(optionIn).getAge());
        System.out.println("Numero de premios: "+horsesList.get(optionIn).getAwards());
        System.out.println("Peso: "+horsesList.get(optionIn).getWeight());
        System.out.println("Precio del Caballo: "+money.format(horsesList.get(optionIn).getPrice()));
        System.out.println("Precio de Pajilla: "+money.format(horsesList.get(optionIn).getSpermDoseCost()));
    }

    /** This method save step by step the information that the user
     * write about the horse and creates new one
     */

    public void addHorse(){
        //Ingreso de nombre.
        System.out.print("Ingrese el nombre del caballo: ");
        String name = read.nextLine();

        //Ingreso de la Raza
        int breedIndex = addBreed(name);

        //Ingreso de paso
        int stepIndex = addStep(name);

        //Ingreso de alimentacion
        System.out.print("Ingrese el tipo de alimento preferido de " + name +": ");
        String diet = read.nextLine();

        //Ingreso de enfermedades
        int diseaseIndex = addDisease(name);

        //Ingreso del nombre del padre
        System.out.print("Ingrese el nombre del padre de " + name +": ");
        String fatherName = read.nextLine();

        //Ingreso del pais
        System.out.print("Ingrese el pais de origen de " + name +": ");
        String country = read.nextLine();

        //Ingreso Edad
        System.out.print("Ingrese la edad de "+name +": ");
        int age = readInt(0);

        //Ingreso numero de Premios
        System.out.print("Ingrese el numero de premios de "+name +": ");
        int awards = readInt(0);

        //Ingreso el Precio
        System.out.print("Ingrese el precio de "+name +": ");
        double price = readDouble(0);

        //Ingreso el Peso
        System.out.print("Ingrese el peso de "+name +": ");
        float weight = readFloat(0);

        //Creacion de nueva instancia de caballo
        Caballo caballo = new Caballo(name, breedList.get(breedIndex), stepList.get(stepIndex), diet,
                diseaseList.get(diseaseIndex), fatherName, country, age, awards, price, weight,0);
        horsesList.add(caballo);
    }

    /** This method allows editing some horse data or all its data
     */

    public void editHorse(){
        System.out.println("Ingrese el indice del Caballo que desea modificar: ");
        printHorses();
        int optionIn = readInt(1, horsesList.size())-1;
        System.out.println("La informacion del Caballo seleccionado es: ");
        System.out.println("1. Nombre: "+horsesList.get(optionIn).getName());
        System.out.println("2. Raza: "+horsesList.get(optionIn).getBreed());
        System.out.println("3. Paso: "+horsesList.get(optionIn).getStep());
        System.out.println("4. Dieta: "+horsesList.get(optionIn).getDiet());
        System.out.println("5. Enfermedad: "+horsesList.get(optionIn).getDiseases());
        System.out.println("6. Nombre del padre: "+horsesList.get(optionIn).getFatherName());
        System.out.println("7. Pais: "+horsesList.get(optionIn).getCountry());
        System.out.println("8. Edad: "+horsesList.get(optionIn).getAge());
        System.out.println("9. Numero de premios: "+horsesList.get(optionIn).getAwards());
        System.out.println("10. Peso: "+horsesList.get(optionIn).getWeight());
        System.out.println("11. Precio del Caballo: "+money.format(horsesList.get(optionIn).getPrice()));
        System.out.println("12. Precio de Pajilla: "+money.format(horsesList.get(optionIn).getSpermDoseCost()));
        System.out.println("0. Modificar toda la informacion: ");
        int editOption = readInt(0,12);
        switch (editOption){
            case 0:
                horsesList.remove(optionIn);
                addHorse();
                break;
            case 1:
                System.out.println("Cual es el nuevo nombre de: "+ horsesList.get(optionIn).getName());
                String name = read.nextLine();
                horsesList.get(optionIn).setName(name);
                break;
            case 2:
                int breedIndex = addBreed(horsesList.get(optionIn).getName());
                horsesList.get(optionIn).setBreed(breedList.get(breedIndex));
                break;
            case 3:
                int stepIndex = addStep(horsesList.get(optionIn).getName());
                horsesList.get(optionIn).setStep(stepList.get(stepIndex));
                break;
            case 4:
                System.out.println("Cual es el nuevo alimento preferido de: "+ horsesList.get(optionIn).getName());
                String diet = read.nextLine();
                horsesList.get(optionIn).setDiet(diet);
                break;
            case 5:
                int diseaseIndex = addDisease(horsesList.get(optionIn).getName());
                horsesList.get(optionIn).setDiseases(diseaseList.get(diseaseIndex));
                break;
            case 6:
                System.out.println("Cual es el nuevo nombre del padre de: "+ horsesList.get(optionIn).getName());
                String fatherName = read.nextLine();
                horsesList.get(optionIn).setFatherName(fatherName);
                break;
            case 7:
                System.out.println("Cual es el nuevo pais de origen de: "+ horsesList.get(optionIn).getName());
                String country = read.nextLine();
                horsesList.get(optionIn).setCountry(country);
                break;
            case 8:
                System.out.println("Cual es la nueva edad de: "+ horsesList.get(optionIn).getName());
                int age = readInt(0);
                horsesList.get(optionIn).setAge(age);
                break;
            case 9:
                System.out.println("Cual es la nueva cantidad de premios de: "+ horsesList.get(optionIn).getName());
                int awards = readInt(0);
                horsesList.get(optionIn).setAwards(awards);
                break;
            case 10:
                System.out.println("Cual es el nuevo peso de: "+ horsesList.get(optionIn).getName());
                float weight = readFloat(0);
                horsesList.get(optionIn).setWeight(weight);
                break;
            case 11:
                System.out.println("Cual es el nuevo precio de: "+ horsesList.get(optionIn).getName());
                double price = readDouble(0);
                horsesList.get(optionIn).setPrice(price);
                break;
            case 12:
                System.out.println("El precio de la pajilla de "+ horsesList.get(optionIn).getName()+" no puede ser" +
                        "modificado.\nEste depende de sus otras caracteristicas, gracias por su comprension. ");
                break;
        }
    }

    /** This method asks which horse you want to remove
       so, the horse is deleted
     */
    public void deleteHorse(){
        if(horsesList.size() == 0){
            System.out.println("No se encuentra ningun caballo en el registro ");
            return;
        }
        System.out.println("Seleccione el indice del Caballo que desea borrar: ");
        printHorses();
        System.out.println("0. No borrar ninguno.");
        int delIndex = readInt(1,horsesList.size());

        if(delIndex != 0) {
            System.out.println("El Caballo: " + horsesList.get(delIndex - 1).getName() + " ha sido eliminado satisfactoriamente");
            horsesList.remove(delIndex - 1);
        }

    }

    /** This is a menu that show all options about the sales
     */

    public void checkSells(){
        System.out.println("Seleccione una opcion.");
        System.out.println("1. Ver todas las ventas");
        System.out.println("2. Ver ventas de un mes especifico");
        System.out.println("3. Ver promedio de ventas por mes");
        int option = readInt(1,3);
        switch (option){
            case 1:
                checkAllSells();
                break;
            case 2:
                checkMonthSells();
                break;
            case 3:
                checkAverageSells();
                break;
        }
    }

    /** This method is used to see the list of all sells
     */

    public void checkAllSells(){
        if(sellsList.isEmpty()){
            System.out.println("No hay ventas registradas.");
            return;
        }
        System.out.println("Las ventas son: ");
        for(int i = 0; i < sellsList.size(); i++){
            System.out.println((i+1)+ ". " + sellsList.get(i).getName() +  "\t" +
                    sellsList.get(i).printableDate() + "\t" + money.format(sellsList.get(i).getSellPrice()));
        }
    }

    /** This method is used to see the list of monthly sells
     */

    public void checkMonthSells(){
        System.out.println("Seleccione un mes ");
        for (int i = 0; i < monthsList.size(); i++){
            System.out.println((i+1) + ". " + monthsList.get(i));
        }
        int option = readInt(1,monthsList.size()) -1;
        double totalSells = 0;
        for(int i = 0; i < sellsList.size(); i++){
            if(sellsList.get(i).getMonth().equals(monthsList.get(option))){
                totalSells += sellsList.get(i).getSellPrice();
                System.out.println(sellsList.get(i).getName() + "\t" + sellsList.get(i).printableDate() +
                        "\t " + money.format(sellsList.get(i).getSellPrice()));
            }
        }
        System.out.println("Las ventas totales del mes son: " + money.format(totalSells)+ "\n");
    }

    /** This method is used to see the list of average sells
     */

    public void checkAverageSells(){
        double totalSells = 0;
        for(int i = 0; i < sellsList.size(); i++){
            totalSells += sellsList.get(i).getSellPrice();
        }
        double averageSells = totalSells / monthsList.size();
        System.out.println("El promedio de ventas por mes es : " + money.format(averageSells));
    }

    /** This method is used to add a new sell
     */

    public void addSell(){
        System.out.println("Seleccione el caballo del que fue vendida la pajilla");
        printHorses();
        int sellIndex = readInt(1,horsesList.size()) -1;
        System.out.println("Cuando fue la venta?");
        System.out.println("1. Hoy");
        System.out.println("2. Otra Fecha");
        GregorianCalendar sellDate;
        YearMonth monthSell = null;
        int when = readInt(1,2);
        if(when == 1){
            sellDate = new GregorianCalendar();
            monthSell = YearMonth.now();
        }else{
            int year, month, day;
            System.out.print("Ingrese el año: ");
            year = readInt(1900,2100);
            System.out.print("Ingrese el numero del mes: ");
            month = readInt(1,12);
            //Lectura de dia, evitando errores de ingreso de dias mayores al del mes.
            YearMonth yearMonth = YearMonth.of(year,month);
            int checkDays = yearMonth.lengthOfMonth();
            System.out.print("Ingrese el dia: ");
            day = readInt(1,checkDays);

            sellDate = new GregorianCalendar(year,month-1,day);
            monthSell = yearMonth.of(year,month);
        }

        Venta venta = new Venta(horsesList.get(sellIndex).getName(),sellDate,
                horsesList.get(sellIndex).getSpermDoseCost());
        sellsList.add(venta);
        horsesList.get(sellIndex).increaseSells();
        if(!monthsList.contains(monthSell)) monthsList.add(monthSell);
        sortSells();
    }

    /** This method returns which horse has more demand with respect to its straw
     */

    public void mostLovedHorse(){
        int mostLovedHorse = 0;
        int mostLovedHorseIndex = 0;
        for(int i = 0; i < horsesList.size(); i++){
            if(mostLovedHorse < horsesList.get(i).getSells()){
                mostLovedHorse = horsesList.get(i).getSells();
                mostLovedHorseIndex = i;
            }
        }
        System.out.println("El caballo mas apreciado es: " + horsesList.get(mostLovedHorseIndex).getName());
    }

    /**
     * Additional methods necessaries to run the program correctly
     */

    public void printHorses(){
        for(int i = 0; i < horsesList.size(); i++){
            System.out.println((i+1) + ". "+ horsesList.get(i).getName());
        }
    }

    public int addBreed(String name){
        System.out.println("Seleccione la raza de " + name +": ");
        for (int i = 0; i< breedList.size(); i++)
            System.out.println(i+1+ ". "+breedList.get(i));
        System.out.println("0. Ingresar una raza: ");
        int breedOption = readInt(0, breedList.size());
        if(breedOption == 0){
            System.out.print("Ingrese el nombre de la raza: ");
            String breed = read.nextLine();
            breedList.add(breed);
            breedOption = breedList.size();
        }
        return breedOption-1;
    }

    public int addStep(String name){
        System.out.println("Seleccione el tipo de paso  de " + name +": ");
        for (int i = 0; i< stepList.size(); i++)
            System.out.println(i+1+ ". "+stepList.get(i));
        System.out.println("0. Ingresar un nuevo tipo de paso: ");
        int stepOption = readInt(0, stepList.size());
        if(stepOption == 0){
            System.out.print("Ingrese el nombre del tipo de paso: ");
            String step = read.nextLine();
            stepList.add(step);
            stepOption = stepList.size();
        }
        return stepOption -1;
    }

    public int addDisease(String name){
        System.out.println("Seleccione la enfermedad " + name +": ");
        for (int i = 0; i< diseaseList.size(); i++)
            System.out.println(i+1+ ". "+diseaseList.get(i));
        System.out.println("0. Ingresar una nueva enfermedad: ");
        int diseaseOption = readInt(0, diseaseList.size());
        if(diseaseOption == 0){
            System.out.print("Ingrese el nombre de la nueva enfermedad: ");
            String disease = read.nextLine();
            diseaseList.add(disease);
            diseaseOption = diseaseList.size();
        }
        return diseaseOption-1;
    }

    /**
     * Metodo que organiza la lista de ventas de acuerdo a su fecha.
     */
    public void sortSells(){
        Collections.sort(sellsList, new Comparator<Venta>() {
            @Override
            public int compare(Venta o1, Venta o2) {
                return o1.getDate().getTime().compareTo(o2.getDate().getTime());
            }
        });
    }

    public int readInt(int min){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(read.nextLine());
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("El numero debe ser entero y ");
            }
            if(n>= min) break;
            System.out.println("Debe ser mayor o igual a  "+min);
        }
        return n;
    }

    public int readInt(int min, int max){
        int n = min-1;
        while(true){
            try{
                n = Integer.parseInt(read.nextLine());
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("El numero debe ser entero y ");
            }
            if(n<= max && n>= min) break;
            System.out.println("Debe estar entre "+min+" y "+max);
        }
        return n;
    }

    public double readDouble(double min){
        double n = min-1;
        while(true){
            try{
                n = Double.parseDouble(read.nextLine());
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("El numero debe ser real y ");
            }
            if(n> min) break;
            System.out.println("Debe ser mayor a  "+min);
        }
        return n;
    }

    public float readFloat(float min){
        float n = min-1;
        while(true){
            try{
                n = Float.parseFloat(read.nextLine());
            }catch (InputMismatchException | NumberFormatException ex){
                System.out.println("El numero debe ser real y ");
            }
            if(n> min) break;
            System.out.println("Debe ser mayor a  "+min);
        }
        return n;
    }

    public void readDB() throws IOException {
        BufferedReader readFile = null;
        File horseFile = new File("Horses.txt");
        if(!horseFile.exists()) return;
        try {
            readFile = new BufferedReader(new FileReader(horseFile));
            String line;
            while ((line = readFile.readLine()) != null) {
                String[] fields = line.split(";");
                String name = fields[0];
                String breed = fields[1];
                String step = fields[2];
                String diet = fields[3];
                String disease = fields[4];
                String fatherName = fields[5];
                String country = fields[6];
                int age = Integer.parseInt(fields[7]);
                int awards = Integer.parseInt(fields[8]);
                double price = Double.parseDouble(fields[9]);
                float weight = Float.parseFloat(fields[10]);
                int sells = Integer.parseInt(fields[11]);
                Caballo caballo = new Caballo(name,breed,step,diet,disease,fatherName,country,
                        age,awards,price,weight,sells);
                horsesList.add(caballo);
                if(!breedList.contains(breed)) breedList.add(breed);
                if(!stepList.contains(step)) stepList.add(step);
                if(!diseaseList.contains(disease)) diseaseList.add(disease);
            }
        }finally {
            if(readFile != null){
                readFile.close();
            }
        }

        File sellsFile = new File("Sells.txt");
        if(!sellsFile.exists()) return;
        try {
            readFile = new BufferedReader(new FileReader(sellsFile));
            String line;
            while ((line = readFile.readLine()) != null) {
                String[] fields = line.split(";");
                String name = fields[0];
                int year = Integer.parseInt(fields[1]);
                int month = Integer.parseInt(fields[2]);
                int day = Integer.parseInt(fields[3]);
                double sellPrice = Double.parseDouble(fields[4]);
                GregorianCalendar sellDate = new GregorianCalendar(year,month-1,day);
                YearMonth yearMonth = YearMonth.of(year,month);
                Venta venta = new Venta(name,sellDate,sellPrice);
                sellsList.add(venta);
                monthsList.add(yearMonth);
            }
        }finally {
            if(readFile != null){
                readFile.close();
            }
        }
    }

    public void saveChanges() throws IOException{
        File horseFile = new File("Horses.txt");
        if(!horseFile.exists()) horseFile.createNewFile();
        PrintWriter writeFile = null;
        try{
            writeFile = new PrintWriter(new FileWriter(horseFile));
            String line;
            for(int i = 0; i < horsesList.size(); i++){
                line = horsesList.get(i).toString();
                writeFile.println(line);
            }
        }finally {
            if (writeFile != null) {
                System.out.println("Horses saved");
                writeFile.close();
            }
        }

        File sellsFile = new File("Sells.txt");
        if(!sellsFile.exists()) sellsFile.createNewFile();
        try{
            writeFile = new PrintWriter(new FileWriter(sellsFile));
            String line;
            for(int i = 0; i < sellsList.size(); i++){
                line = sellsList.get(i).toString();
                writeFile.println(line);
            }
        }finally {
            if (writeFile != null) {
                System.out.println("Sells saved");
                writeFile.close();
            }
        }
    }
}
