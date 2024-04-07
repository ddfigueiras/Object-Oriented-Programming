import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Classe que representa o terminal/utilização de tudo (main).
 */
public class Cliente {
    /**
     * Método principal.
     *
     * @author Diogo Silva (a79764)
     * @version 1.0.0 - 19/02/24
     * @param args
     * @return A list of Pontos representing the points read from the input.
     */

    public static String capital(String s) 
    {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        List<Poligono> poligonos = new ArrayList<>();
        while (sc.hasNextLine()) 
        {
            String linhaPoligono = sc.nextLine();
            if (linhaPoligono.isEmpty()) break;
            
            String linhaRotacao = sc.nextLine();
            if (linhaRotacao.isEmpty()) break;
            
            String[] partesPoligono = linhaPoligono.split(" ", 2);
            String[] partesRotacao = linhaRotacao.split(" ");
            

            //Ponto pontoRotacao = null;

            int xRotacao = Integer.parseInt(partesRotacao[0]);
            int yRotacao = Integer.parseInt(partesRotacao[1]);
            //pontoRotacao = new Ponto(xRotacao, yRotacao);

            try 
            {
                Class<?> cl = Class.forName(capital(partesPoligono[0]));
                Constructor<?> constructor = cl.getConstructor(String.class);
                Poligono poligono = (Poligono) constructor.newInstance(partesPoligono[1]);
                for (Poligono poligonotest : poligonos) 
                {
                    if(poligonotest.equals(poligono))
                        printError("Duplicado");
                }
                poligono.moverParaNovoCentro(xRotacao, yRotacao);
                //System.out.println(poligono);
                poligonos.add(poligono);
            } catch (ClassNotFoundException e) 
            {
                System.out.println("Classe não encontrada: " + e.getMessage());
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        sc.close();
        for (Poligono poligonoPrint : poligonos) {
            System.out.println(poligonoPrint.toString());
        }
    }
/*
Poligono 4 5 5 8 6 8 7 5 7
Triangulo 7 1 9 1 9 3
Quadrado 3 0 5 2 3 4 1 2
Retangulo 0 1 1 0 3 2 2 3

Retangulo 1 1 3 1 3 5 1 5
90
Retangulo 1 1 3 1 3 5 1 5
-90 3 1
Triangulo 2 1 4 1 3 4
180

Poligono 4 1 2 5 6 8 7 12 14
-1 3
Poligono 4 1 2 5 6 8 7 12 14
-5 1

Retangulo 1 1 5 1 5 3 1 3
3 3
Triangulo 1 0 3 0 2 3
2 3
*/
    /**
     * Reads from the input the number of points, reads them, and returns them in a
     * list of Ponto.
     * @author Diogo Silva (a79764).
     * @version 1.0.0 - 2024/02/19
     * @inv n >= 0
     * @param sc a scanner
     * @param n the number of points to read
     * @return A list of Pontos representing the points read from the input.
     */
    static Map<List<Ponto>, PoligonoRetangulo> ReadFromInputPointsN(Scanner sc, int n) {
        List<Ponto> listPontos = new ArrayList<>();
        int[] valores1 = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i = 0; i < n; i++) {
            Ponto ponto = new Ponto(sc.nextInt(), sc.nextInt());
            listPontos.add(ponto);
            valores1[0] = Math.max(valores1[0], ponto.getX());
            valores1[1] = Math.max(valores1[1], ponto.getY());
            valores1[2] = Math.min(valores1[2], ponto.getX());
            valores1[3] = Math.min(valores1[3], ponto.getY());
            //System.out.println("====================================");
            //System.out.println(ponto.getX()+ ", " + ponto.getY());
        }

        PoligonoRetangulo poligonoRetangulo = new PoligonoRetangulo(valores1);
        Map<List<Ponto>, PoligonoRetangulo> poligonos = new HashMap<>();
        List<Ponto> poligono = new ArrayList<>(listPontos);

        poligonos.put(poligono, poligonoRetangulo);

        return poligonos;
    }

        /**
     * Used to print a string to the output
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param erro string with the name of the error
     * @return A list of Pontos representing the points read from the input.
     */
    static void printError(String erro)
    {
        System.out.println(erro);
        System.exit(0);
    }
}