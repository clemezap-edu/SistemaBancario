import java.util.Scanner;
import java.time.LocalDate;

class Banco {

    Scanner scanner = new Scanner(System.in);

    //int numCuenta;
    //int saldo;

    String[] nomCuentas = new String[3];
    long[] numTarjetas = new long[3];
    int[] numCuentas = new int[3];
    long [] interBancs = new long[3];
    double[] saldoBancs = new double[3];
    int[] pinBancs = new int[3];
    long[][] inversiones = new long[50][3];
    String[][] movimientos = new String[100][4];
}

class Cliente extends Banco {

    //String cliente = "Clemente";
    //int pin = 123;

    void depositar(int numCliente){



        int cantidad;

        System.out.println("¿Qué cantidad quieres depositar?");
        cantidad = scanner.nextInt();

        saldoBancs[numCliente] += cantidad;
        System.out.println("Deposito realizado");

        for (int i = 0; i < 100; i++) {
            if (movimientos[i][0].equals("Nada")){
                movimientos[i][0] = "Deposito";
                movimientos[i][1] = "Fecha: " + LocalDate.now();
                movimientos[i][2] = "Cantidad: "+cantidad;
                movimientos[i][3] = Long.toString(numTarjetas[numCliente]);
                break;
            }
        }

    }

    void retirar(int numCliente) {
        int cantidad;


        do {
            System.out.println("¿Qué cantidad quieres retirar?");
            cantidad = scanner.nextInt();

            if (cantidad > saldoBancs[numCliente]) {
                System.out.println("Insuficiente para retirar");
            }
        } while (cantidad > saldoBancs[numCliente]);

        saldoBancs[numCliente] -= cantidad;
        System.out.println("Retiro realizado");

        for (int i = 0; i < 100; i++) {
            if (movimientos[i][0].equals("Nada")){
                movimientos[i][0] = "Retiro";
                movimientos[i][1] = "Fecha: " + LocalDate.now();
                movimientos[i][2] = "Cantidad: "+cantidad;
                movimientos[i][3] = Long.toString(numTarjetas[numCliente]);
                break;
            }
        }

    }

    void transferencia(int numCuenta ){

        long cantidad = 0;
        long clabe = 0;
        long numTarjeta;
        int cuentaDestino = 0;
        int cuentaDepo = 0;
        int opcion = 0;

        int paso = 0;

        System.out.println("¿Qué tipo de transferencia quiere realizar?\n1)Mismo Banco\n2)Interbancaria\n3)Por tarjeta");
        opcion  = scanner.nextInt();

        switch (opcion){
            case 1:
                do {

                    System.out.println("Ingrese el Número de cuenta:");
                    cuentaDestino = scanner.nextInt();

                    for (int i = 0; i < 3; i++) {
                        if (cuentaDestino == numCuentas[i]) {
                            cuentaDepo = i;
                            paso = 1;
                        }
                    }

                    if (paso == 0){
                        System.out.println("No existe esa cuenta");
                    }

                } while (paso == 0);


                if (paso == 1){
                    System.out.println("Ingresa la cantidad que quieres transferir");
                    cantidad = scanner.nextInt();

                    if (cantidad > saldoBancs[numCuenta]) {
                        System.out.println("Saldo insuficiente");
                    } else {
                        saldoBancs[cuentaDepo] += cantidad;
                        saldoBancs[numCuenta] -= cantidad;
                    }


                }

                break;
            case 2:
                do {

                    System.out.println("Ingrese la clabe a la que quiere depositar");
                    clabe = scanner.nextLong();

                    for (int i = 0; i < 3; i++) {
                        if (clabe == interBancs[i]) {
                            cuentaDepo = i;
                            paso = 1;
                        }
                    }

                    if (paso == 0){
                        System.out.println("No existe esa cuenta");
                    }

                } while (paso == 0);


                if (paso == 1){
                    System.out.println("Ingresa la cantidad que quieres transferir");
                    cantidad = scanner.nextInt();

                    if (cantidad > saldoBancs[numCuenta]) {
                        System.out.println("Saldo insuficiente");
                    } else {
                        saldoBancs[cuentaDepo] += cantidad;
                        saldoBancs[numCuenta] -= cantidad;
                    }


                }

                break;
            case 3:
                do {

                    System.out.println("Ingrese el número de tarjeta a la que quiere transferir");
                    numTarjeta = scanner.nextLong();

                    for (int i = 0; i < 3; i++) {
                        if (numTarjeta == numTarjetas[i]) {
                            cuentaDepo = i;
                            paso = 1;
                        }
                    }

                    if (paso == 0){
                        System.out.println("No existe esa cuenta");
                    }

                } while (paso == 0);


                if (paso == 1){
                    System.out.println("Ingresa la cantidad que quieres transferir");
                    cantidad = scanner.nextInt();

                    if (cantidad > saldoBancs[numCuenta]) {
                        System.out.println("Saldo insuficiente");
                    } else {
                        saldoBancs[cuentaDepo] += cantidad;
                        saldoBancs[numCuenta] -= cantidad;
                    }


                }

                break;

        }

        for (int i = 0; i < 100; i++) {
            if (movimientos[i][0].equals("Nada")){
                movimientos[i][0] = "Transferencia";
                movimientos[i][1] = "Fecha: " + LocalDate.now();
                movimientos[i][2] = "Cantidad: "+cantidad;
                movimientos[i][3] = Long.toString(numTarjetas[numCuenta]);
                break;
            }
        }



    }

    void inversiones(long numTarjeta, int cliente){

        int bandera = 0;
        int paso = 0;
        int opcion = 0;
        int cantidad = 0;


        for (int i = 0; i < 50; i++) {
            if (numTarjeta == inversiones[i][2]){
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Inversión "+ inversiones[i][1]);
                System.out.println("Cantidad: "+ inversiones[i][0]);
                System.out.println("Retorno: "+ ( inversiones[i][0] + (inversiones[i][0] *0.02) * 0.15 ) );
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
                bandera = 1;
            }
        }

        if (bandera == 0){
            System.out.println("No haz hecho ninguna inversión");
        }

        System.out.println("¿Deseas realizar una inversión?\n1)Sí\n2)No");
        opcion = scanner.nextInt();

        if (opcion == 1){
            do{
                System.out.println("Ingresa la cantidad que quieres invertir");
                cantidad = scanner.nextInt();

                if (cantidad >  saldoBancs[cliente]){
                    System.out.println("Saldo insuficiente");
                } else{
                    paso = 1;
                }

            } while (paso == 0);

            for (int i = 0; i < 50; i++) {
                if (inversiones[i][0] == 0 ){
                    inversiones[i][0] = cantidad;
                    inversiones[i][1] = 123 + i + cantidad;
                    inversiones[i][2] = numTarjeta;

                    saldoBancs[cliente] += (cantidad * 0.02) * 0.15  ;

                    System.out.println("Inversión realizada");

                    break;

                }
            }

            for (int i = 0; i < 100; i++) {
                if (movimientos[i][0].equals("Nada")){
                    movimientos[i][0] = "Inversión";
                    movimientos[i][1] = "Fecha: " + LocalDate.now();
                    movimientos[i][2] = "Cantidad: "+cantidad;
                    movimientos[i][3] = Long.toString(numTarjetas[cliente]);
                    break;
                }
            }
        }

    }

    void movimientos(long numTarjeta, int cliente){

        int bandera = 0;

        for (int i = 0; i < 100; i++) {

            if (movimientos[i][3].equals(Long.toString(numTarjeta))){

                System.out.println("- - - - - - - - - - - - - - - - - ");
                System.out.println(movimientos[i][0]);
                System.out.println(movimientos[i][1]);
                System.out.println(movimientos[i][2]);
                System.out.println(movimientos[i][3]);
                System.out.println("- - - - - - - - - - - - - - - - - ");
                bandera = 1;
            }
        }

        if (bandera == 0){
            System.out.println("- - - - - - - - - - - - - - -");
            System.out.println("No has realizado movimientos");
            System.out.println("- - - - - - - - - - - - - - -");
        }
    }

}

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        Cliente cl = new Cliente();

        cl.nomCuentas[0] = "Pedro";
        cl.nomCuentas[1] = "Pablo";
        cl.nomCuentas[2] = "Juan";

        cl.numCuentas[0] = 12345678;
        cl.numCuentas[1] = 23456789;
        cl.numCuentas[2] = 34567890;

        cl.interBancs[0] = 6543212345678L;
        cl.interBancs[1] = 7654321234567L;
        cl.interBancs[2] = 8765432123456L;

        cl.numTarjetas[0] = 1234567890987654L;
        cl.numTarjetas[1] = 2345678909876543L;
        cl.numTarjetas[2] = 3456789098765432L;

        cl.saldoBancs[0] = 500;
        cl.saldoBancs[1] = 1000;
        cl.saldoBancs[2] = 1500;

        cl.pinBancs[0] = 1234;
        cl.pinBancs[1] = 2345;
        cl.pinBancs[2] = 3456;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 50; j++) {
                cl.inversiones[j][i] = 0;
            }
        }

        for (int i = 0; i < 100; i++) {
            cl.movimientos[i][0] = "Nada";
            cl.movimientos[i][3] = "0";
        }


        int cliente = 0;
        int paso = 0;

        int pinIntento;
        int intentos = 0;
        int opcion = 0;
        long numCuenta;

        do {
            System.out.println("- - - - - B A N C O    P O L I T E C N I C O - - - - - -");
            intentos = 0;

            do{

                System.out.println("Ingresa tu número de tarjeta");
                numCuenta = sc.nextLong();

                for (int i = 0; i < 3; i++) {
                    if (numCuenta == cl.numTarjetas[i]){
                        paso = 1;
                        cliente = i;
                    }
                }

            } while(paso == 0);

            paso = 0;

            do {

                System.out.println("Número de cuenta: " + cl.numCuentas[cliente]);

                System.out.println("Ingresa tu pin");
                pinIntento = sc.nextInt();

                if (pinIntento == cl.pinBancs[cliente]){
                    paso = 1;
                    break;
                }

                intentos++;


                if (intentos >= 3) {
                    System.out.println("Número de intentos excedidos, bloqueando tarjeta. . .");
                    System.exit(0);
                }
            } while (intentos <= 3);

            do {
                System.out.println("- - - - - - - - - - - - - - - - - - - ");
                System.out.println(LocalDate.now());
                System.out.println("Bienvenido " + cl.nomCuentas[cliente]);
                System.out.println("Número de cuenta: "+cl.numCuentas[cliente]);
                System.out.println("Saldo: "+cl.saldoBancs[cliente]);

                System.out.println("¿Qué deseas realizar? \n1)Depositar\n2)Retirar\n3)Transferencia\n4)Inversiones\n5)Movimientos\n6)Salir");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        cl.depositar(cliente);
                        break;
                    case 2:
                        cl.retirar(cliente);
                        break;
                    case 3:
                        cl.transferencia(cliente);
                        break;
                    case 4:
                        cl.inversiones(cl.numTarjetas[cliente], cliente);
                        break;
                    case 5:
                        cl.movimientos(cl.numTarjetas[cliente], cliente);
                        break;
                    case 6:
                        System.out.println("Saliendo del programa . . .");
                        break;

                }
            } while (opcion !=6);
        } while (true);




    }
}