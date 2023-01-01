
import java.util.Scanner;

public class UF2_Practica8Pablo_JimenezRuben_Serrano {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        
        //Demanem la mida del tauler en files i columnes       
        int fila = escanearEntero("Quantes files vols: ");
        int columna = escanearEntero("Quantes columnes vols: ");
        int [][] tauler = new int [fila][columna];

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

    static int Jugada(int [][] tauler){
    
    }
    
    static int EnRatlla(int [][] tauler){
        
    }
    
    static void Dibuixa(int fila, int columna, int [][] tauler){
      
    }
    
    
    

}
