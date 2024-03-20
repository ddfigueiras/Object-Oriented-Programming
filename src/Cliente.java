import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    public static void main(String[] args) {
        boolean intercepts = false;
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        Map<List<Ponto>, PoligonoRetangulo> listaPoligonos = new HashMap<>();


        for (int i = 0; i < 2; i++) {
            // Lê o número de vértices do polígono atual
            int numVerticesPoligono = sc.nextInt();
            // Lê os pontos do polígono atual
            listaPoligonos.putAll(ReadFromInputPointsN(sc, numVerticesPoligono));
        }
        sc.close();

        if (!listaPoligonos.isEmpty()) {
            List<List<Ponto>> chaves = new ArrayList<>(listaPoligonos.keySet());

            if (!listaPoligonos.get(chaves.get(0)).intercept(listaPoligonos.get(chaves.get(1)))) {
                printError("false");
            }

            List<Poligono> pList = new ArrayList<>(2);
            for (Map.Entry<List<Ponto>, PoligonoRetangulo> entry : listaPoligonos.entrySet()) {
                if (entry.getKey().equals(chaves.get(0))) {
                    pList.add(new Poligono(entry.getKey()));
                } else if (entry.getKey().equals(chaves.get(1))) {
                    pList.add(new Poligono(entry.getKey()));
                }
            }

            if (pList.size() >= 2 && pList.get(0).IntercetaPoligono(pList.get(1).getPontos(), false)) {
                intercepts = true;
            }
        }

        String result = intercepts ? "true" : "false";
        System.out.println(result);
    }

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