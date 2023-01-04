
import java.util.Scanner;

public class UF2_Practica8Pablo_JimenezRuben_Serrano {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        //Demanem la mida del tauler en files i columnes, el mínim del tauler serà de 4x4 obviament 
        int fila = escanearEntero("Quantes files vols: ");
        boolean n_files_valid = false;
        while (!n_files_valid) {
            if (fila >= 4) {
                n_files_valid = true;
            } else {
                fila = escanearEntero("El tamany mínim del tauler ha de ser de 4x4, torna a introduir les files: ");
            }
        }
        int columna = escanearEntero("Quantes columnes vols: ");
        boolean n_columnes_valid = false;
        while (!n_columnes_valid) {
            if (columna >= 4) {
                n_columnes_valid = true;
            } else {
                columna = escanearEntero("El tamany mínim del tauler ha de ser de 4x4, torna a introduir les columnes: ");
            }
        }
        int[][] tauler = new int[fila][columna];

        //Inicialització del tauler
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                tauler[i][j] = 0;
            }
        }
        int jugador = 1;
        boolean ratlla = false;
        
        //Mantindrem un registre dels moviments que hem fet per saber si hem omplert la matriu
        int num_moviments = 0;
        //Els moviments màxims seràn la quantitat d'espais que té el tauler
        final int NUM_MOVIMENTS_MAX = fila * columna;

        //El programa s'executa fins que hi ha un 4 en ratlla o la matriu s'omple
        while (ratlla == false && num_moviments < NUM_MOVIMENTS_MAX) {

            //Comprovem si la columna on volem coloar fitxa és vàlida
            boolean columna_valida = false;
            System.out.println("Torn del jugador " + jugador);
            int moviment = escanearEntero("Selecciona una columna per colocar fitxa: ");
            while (!columna_valida) {
                if (moviment >= 0 && moviment < tauler[0].length) {
                    columna_valida = true;
                } else {
                    moviment = escanearEntero("Columna no vàlida, torna a introduir-la: ");
                }
            }

            //Realitzem la jugada
            int fila_colocada = Jugada(moviment, jugador, tauler);

            //Comprovem si la columna és plena i per tant no podem colocar fitxa (es manté el torn del jugador)
            if(fila_colocada == -1){
                System.out.println("La columna ja és plena, introdueix un altra columna");
            }
            else{
                System.out.println("Fitxa colocada a la fila " + fila_colocada);
                //Comprovem si hi ha guanyador
                ratlla = EnRatlla(fila_colocada, moviment, tauler);
                if (ratlla) {
                    if (jugador == 1) {
                        System.out.println("GUANYA EL JUGADOR 1!!");
                    } else {
                        System.out.println("GUANYA EL JUGADOR 2 !!");
                    }
                } else {
                    if (jugador == 1) {
                        jugador = 2;
                    } else {
                        jugador = 1;
                    }
                    ++num_moviments;
                }
            }

            Dibuixa(tauler);
        }

    }

    //Funció per llegir enters
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

    //Funció que retorna a quina fila de la columna hem colocat la fitxa, a més
    //coloca la propia fitxa a la matriu
    static int Jugada(int columna, int jugador, int tauler[][]) {
        int fila_fitxa = -1;

        boolean trobat = false;

        for (int i = 0; i < tauler.length && !trobat; ++i) {
            /*
            En cas que la columna estigui plena mai entrarem en aquest if
            i el valor de fila_fitxa és mantindra en -1 que significa 
            que no es pot colocar
             */
            if (tauler[i][columna] == 0) {
                trobat = true;
                fila_fitxa = i;
            }
        }
        if(fila_fitxa != -1){
            if (jugador == 1) {
                tauler[fila_fitxa][columna] = 1;
            } else {
                tauler[fila_fitxa][columna] = 2;
            }
        }

        return fila_fitxa;
    }

    //Funció que retorna true si ha trobat un 4 en ratlla i false si no ho ha fet
    static boolean EnRatlla(int fila, int columna, int tauler[][]) {
        boolean ratlla = false;

        //Comprovem en horitzontal
        int valor_casella = tauler[fila][columna];
        for (int i = columna - 3; i < columna + 3; ++i) {
            //Comprovem els fora de límit
            if (i < tauler.length - 3 && i >= 0) {
                if (tauler[fila][i] == valor_casella
                        && tauler[fila][i + 1] == valor_casella
                        && tauler[fila][i + 2] == valor_casella
                        && tauler[fila][i + 3] == valor_casella) {
                    ratlla = true;
                }
            }
        }

        //Comprovem en vertical
        for (int i = fila - 3; i < fila + 3; ++i) {
            //Comprovem els fora de límit
            if (i < tauler[0].length - 3 && i >= 0) {
                if (tauler[i][columna] == valor_casella
                        && tauler[i + 1][columna] == valor_casella
                        && tauler[i + 2][columna] == valor_casella
                        && tauler[i + 3][columna] == valor_casella) {
                    ratlla = true;
                }
            }

        }

        return ratlla;
    }

    //Procediment per mostrar la matriu
    static void Dibuixa(int tauler[][]) {

        for (int i = tauler.length - 1; i >= 0; --i) {
            for (int j = 0; j < tauler[0].length; ++j) {
                System.out.print(tauler[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

}
