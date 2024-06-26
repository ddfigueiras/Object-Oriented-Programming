import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Poligono 
{
    private List<Ponto> pontos;

    /**
     * Construct a poligono (all the vertices)
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param pontos a list of pontos
     * @return A list of Pontos representing the points read from the input.
     */
    public Poligono(List<Ponto> pontos) 
    {
        this.pontos = pontos;
        if(!this.isValid(false))
        {
            // deixei isto para se no futuro quiser não printar anteriormente logo ou fazer uma alteração na forma como funciona
            System.out.println("Erro não printado. Saida forçada...");
            System.exit(0);
        }
    }

    public Poligono(String pontos) 
    {
        List<Ponto> temp = new ArrayList<>();
        String[] numbersStr = pontos.split(" ");
        int startIndex = numbersStr.length % 2 == 0 ? 0 : 1;
        
        for (int i = startIndex; i < numbersStr.length; i += 2) 
        {
            Ponto p = new Ponto(Integer.parseInt(numbersStr[i]), Integer.parseInt(numbersStr[i + 1]));
            //System.out.println(p.toString());
            temp.add(p);
        }
        /*
        System.out.println("==================");
        System.out.println(temp.size());
        System.out.println("===============");
        */
        this.pontos = temp;
        if (!this.isValid(false)) 
        {
            System.out.println("Erro não printado. Saída forçada...");
            System.exit(0); 
        }
        
    }

    /**
     * Add a new point to the poliogno (not used yet because I changed a function and may be usefull)
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param Ponto
     */
    void addPonto(Ponto ponto) 
    {
        pontos.add(ponto);
    }

    /**
     * sum of all segments (perimetro)
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @return perimetro (double).
     */
    int perimetro() 
    {
        int perimetro = 0;
        for (int i = 0; i < pontos.size(); i++) 
        {
            Ponto pontoAtual = pontos.get(i);
            Ponto proximoPonto = pontos.get((i + 1) % pontos.size()); // Usei o % pq quando chegar ao ultimo elemento vai fazer com o elemento da posição 0
            perimetro += pontoAtual.dist(proximoPonto);
        }
        return perimetro;
    }

    /**
     * Check if the the poligono is in the first squere
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @return If it's in the first squere or not
     */
    boolean isFirstSquare() 
    {
        if (pontos.isEmpty()) 
        {
            System.out.println("Poligono empty");
            return false;
        }

        for (Ponto ponto : pontos) 
        {
            if (!ponto.isFirstQuadrant()) 
            {
                return false;
            }
        }
        return true;
    }

    /**
     * We check if it's actually an poligono, if not return the name of the error (string)
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @return 1 if the poligono is valid else a string with the error name.
     */
    boolean isValid(boolean poligonoSimples) 
    {
        boolean colinear = false;
        int n = pontos.size();
        
        if (n < 3) {
            Cliente.printError("Poligono:vi"); // Se tem menos de 3 pontos, não é um polígono
        }
        if (!this.IsAllPointsSegment()) {
            Cliente.printError("Segmento:vi");
        }
        // Verificar se todos os pontos são colineares
        
        for (int i = 0; i < n; i++) 
        {
            Ponto p1 = pontos.get(i);
            Ponto p2 = pontos.get((i + 1) % n);
            Ponto p3 = pontos.get((i + 2) % n);


            if (i > 0 && (this.pontos.get(i - 1).getX() == p1.getX() && this.pontos.get(i - 1).getY() == p1.getY())) {
                //System.out.println(String.format("%d, %d | %d, %d", listPontos.get(i).x, listPontos.get(i).y, listPontos.get(0).x, listPontos.get(0).y));
                //System.out.println("pontos iguais 1");
                Cliente.printError("Reta:vi");
            } else if (i == n - 1 && (this.pontos.get(i).getX() == this.pontos.get(0).getX() && this.pontos.get(i).getY() == this.pontos.get(0).getY())) 
            {
                //System.out.println(String.format("%d, %d | %d, %d", listPontos.get(i).x, listPontos.get(i).y, listPontos.get(0).x, listPontos.get(0).y));
                //System.out.println("pontos iguais 2");
                Cliente.printError("Reta:vi");
            }
            if (Ponto.isColinear(p1, p2, p3))
            {
                colinear = true;
            }
        }
        if(colinear)
            Cliente.printError("Poligono:vi");
        // Verificar interseções entre segmentos consecutivos
        for (int i = 0; i < n; i++) 
        {
            Ponto p3 = pontos.get(i);
            Ponto p4 = pontos.get((i + 1) % n);
            for (int j = i + 2; j < n; j++) 
            {
                Ponto p5 = pontos.get(j);
                Ponto p6 = pontos.get((j + 1) % n);
                if (intersecaoSegmentos(p3, p4, p5, p6, poligonoSimples)) 
                {
                    Cliente.printError("Poligono:vi"); // Se houver interseção, o polígono não é válido
                }
            }
        }
        
        return true; // Polígono válido 
    }
    
    /**
     * In this fuction we want to check if there are any interception beetwin
     * segments.
     * So we check if they are in any point is in the same bounds and w
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param p1 ponto 1 com x e y
     * @param p2 ponto 2 com x e y
     * @param p3 ponto 3 com x e y
     * @param p4 ponto 4 com x e y
     * @param poligonoSimples se é simples para usar o metodo colinear
     * @return if the both segments are intercepted.
     */
    boolean intersecaoSegmentos(Ponto p1, Ponto p2, Ponto p3, Ponto p4, boolean poligonoSimples) 
    {
        int d1 = Ponto.orient(p1, p2, p3);
        int d2 = Ponto.orient(p1, p2, p4);

        int d3 = Ponto.orient(p3, p4, p1);
        int d4 = Ponto.orient(p3, p4, p2);
        if(poligonoSimples)
        {
            if (d1 == 0 && Ponto.sameSegment(p1, p2, p3)) return true;
            if (d2 == 0 && Ponto.sameSegment(p1, p2, p4)) return true;
            
            if (d3 == 0 && Ponto.sameSegment(p3, p4, p1)) return true;
            if (d4 == 0 && Ponto.sameSegment(p3, p4, p2)) return true;
        }

        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * Seguem-se n linhas cada uma das quais com um ponto da trajetória.
     * Segue-se outro natural, m, indicando o número de obstáculos (polígonos)
     * Seguem-se m conjuntos de linhas com o seguinte significado: A primeira linha
     * de cada conjunto é um natural, digamos k, indicado o número de vértices de um
     * polígono cujos vértices são dados pelas k linhas seguintes.
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @return If all points are segment or not (same point twice)
     */
    boolean IsAllPointsSegment() 
    {
        for (int i = 0; i < pontos.size(); i++) 
        {
            for (int j = i + 1; j < pontos.size(); j++) 
            {
                if (pontos.get(i).equals(pontos.get(j))) 
                {
                    return false;
                }
            }
        }
        return true; 
    }
    
    /**
     * Check if the list of points that make a trajectory are intercepted by a poligno
     * 
     * @author (Diogo Silva a79764)
     * @version (1.0.0 - 19/02/24)
     * @param listaPontos Trajétória (lista de pontos)
     * @return if it intercept = true or not = false.
     */
    boolean IntercetaPoligono(List<Ponto> listaPontos, boolean poligonoSimples) 
    {
        int n = pontos.size();
        for (int i = 0; i < n; i++) {
            Ponto p1 = pontos.get(i);
            Ponto p2 = pontos.get((i + 1) % n);

            for (int j = 0; j < listaPontos.size() - 1; j++) 
            {
                Ponto p3 = listaPontos.get(j);
                Ponto p4 = listaPontos.get(j + 1);
                
                if (intersecaoSegmentos(p1, p2, p3, p4, poligonoSimples)) 
                {
                    /*System.out.println("==================");
                    System.out.println(i);
                    System.out.println(p1.getX() + "," + p1.getY() + " "  + p2.getX() + "," + p2.getY() + " " + p3.getX() + "," + p3.getY() + " " + p4.getX() + "," + p4.getY() + " ");
                    System.out.println("==================");*/
                    return true;
                }
            }
        }
        return false;
    }

    public List<Ponto> getPontos() 
    {
        return pontos;
    }

    public void setPontos(List<Ponto> pontos) 
    {
        this.pontos = pontos;
    }
    public boolean samePoints(Poligono other) 
    {
        if (other == null) {
            return false;
        }
        List<Ponto> otherPontos = other.getPontos();

        if (pontos.size() != otherPontos.size()) 
        {
            return false;
        }
        int mesmosPontos = 0;
        for (int i = 0; i < pontos.size(); i++) 
        {
            for(int j = 0; j < otherPontos.size(); j++)
            {
                if (pontos.get(i).getX() == otherPontos.get(j).getX() && pontos.get(i).getY() == otherPontos.get(j).getY()) 
                {
                    mesmosPontos++;
                }
            }
        }
        if(mesmosPontos == pontos.size()) return true;
        return false;
    }

    public void rotacionar(double anguloGraus, Ponto pontoRotacao) 
    {
        double anguloRadianos = Math.toRadians(anguloGraus);
        double xc, yc;

        if (pontoRotacao == null) {
            // Se nenhum ponto de rotação for especificado, usar o centroide do polígono
            double somaX = 0, somaY = 0;
            for (Ponto p : pontos) {
                somaX += p.getX();
                somaY += p.getY();
            }
            xc = somaX / pontos.size(); // Calcula a coordenada x do centroide
            yc = somaY / pontos.size(); // Calcula a coordenada y do centroide
        } else {
            // Se um ponto de rotação for especificado, usar suas coordenadas
            xc = pontoRotacao.getX();
            yc = pontoRotacao.getY();
        }
        for (Ponto p : this.pontos) 
        {
            double xOriginal = p.getX() - xc;
            double yOriginal = p.getY() - yc;

            double xNovo = xc + xOriginal * Math.cos(anguloRadianos) - yOriginal * Math.sin(anguloRadianos);
            double yNovo = yc + xOriginal * Math.sin(anguloRadianos) + yOriginal * Math.cos(anguloRadianos);

            int xFinal = (int) Math.round(xNovo);
            int yFinal = (int) Math.round(yNovo);
            p.setX(xFinal);
            p.setY(yFinal);
        }
    }

    public void translacao(int dx, int dy) 
    {
        for (Ponto ponto : this.pontos) 
        {
            ponto.setX(ponto.getX() + dx);
            ponto.setY(ponto.getY() + dy);
        }
    }

    public void moverParaNovoCentro(int novoX, int novoY) 
    {
        int cx = 0, cy = 0;
        for (Ponto ponto : pontos) 
        {
            cx += ponto.getX();
            cy += ponto.getY();
        }
        cx /= pontos.size();
        cy /= pontos.size();
    
        int dx = novoX - cx;
        int dy = novoY - cy;
    
        translacao(dx, dy);
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        String polygonType = "Poligono";
        sb.append(polygonType);
        sb.append(" de ").append(pontos.size()).append(" vertices: ");
        sb.append("[");
        for(int i = 0; i < pontos.size(); i++) {
            sb.append(pontos.get(i).toString());
            if (i < pontos.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() 
    {
        return Objects.hash(pontos);
    }

    @Override
    public boolean equals(Object obj) 
    {
        //if (this == obj) return true;
        // if (obj == null || getClass() != obj.getClass()) return false; 
        Poligono other = (Poligono) obj;
        return this.samePoints(other);
    }
}
/*
5
0 0
2 2
4 4
6 6
8 8
1
4
10 10
12 12
14 14
16 16
*/