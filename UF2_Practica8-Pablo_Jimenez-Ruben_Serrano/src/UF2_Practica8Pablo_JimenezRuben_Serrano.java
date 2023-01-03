
import java.util.Scanner;

public class UF2_Practica8Pablo_JimenezRuben_Serrano {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        //Demanem la mida del tauler en files i columnes       
        int fila = escanearEntero("Quantes files vols: ");
        int columna = escanearEntero("Quantes columnes vols: ");
        int[][] tauler = new int[fila][columna];

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                tauler[i][j] = 0;
            }
        }
        int jugador = 1;
        boolean ratlla = false;

        while (ratlla == false) {
            boolean jugada;
            int moviment = 0;
            do {
                Dibuixa(tauler);
                moviment = escanearEntero("Selecciona una fila: ");

                //Comprobem la jugada
                jugada = Jugada(moviment, tauler);
            } while (jugada == false);
            //Coloquem la fitxa
            for (int f = tauler.length - 1; f >= 0; f--) {
                if (tauler[f][moviment] == 0) {
                    tauler[f][moviment] = jugador;                  
                }
            }
            //Comprobem si hi ha guanyador
            ratlla = EnRatlla(jugador, tauler);

            //Si no intercambiem els jugadors
            if (jugador == 1) {
                jugador = 2;
            } else {
                jugador = 1;
            }
            Dibuixa(tauler);
        }
        if (ratlla) {
            if (jugador == 1) {
                System.out.println("GUANYA EL JUGADOR 1!!");
            } else {
                System.out.println("GUANYA EL JUGADOR 2 !!");
            }
        }

    }

    static int escanearEntero(String msj) {
        int num;
        System.out.print(msj);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Ha de ser enter.");
            System.out.print(msj);
        }
        num = scan.nextInt();
        return num;
    }

    static boolean Jugada(int columna, int tauler[][]) {
        boolean jugada = true;
        //Primer comprobem si la jugada es valida
        if (columna < 0 || columna > tauler.length) {
            jugada = false;
        }
        //Comprobem si està plena 
        if (tauler[0][columna] != 0) {
            jugada = false;
        }

        return jugada;
    }

    static boolean EnRatlla(int jugador, int tauler[][]) {
        boolean ratlla = false;

        //Comprobem en hortizontal
        for (int fila = 0; fila < tauler.length; fila++) {
            for (int col = 0; col < tauler[0].length - 3; col++) {
                if (tauler[fila][col] == jugador
                        && tauler[fila][col + 1] == jugador
                        && tauler[fila][col + 2] == jugador
                        && tauler[fila][col + 3] == jugador) {
                    ratlla = true;
                }
            }
        }

        //Comprobem en vertical
        for (int fila = 0; fila < tauler.length - 3; fila++) {
            for (int col = 0; col < tauler[0].length; col++) {
                if (tauler[fila][col] == jugador
                        && tauler[fila + 1][col] == jugador
                        && tauler[fila + 2][col] == jugador
                        && tauler[fila + 3][col] == jugador) {
                    ratlla = true;
                }
            }
        }

        return ratlla;
    }

    static void Dibuixa(int tauler[][]) {

        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler.length; j++) {
                System.out.print(tauler[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

}
