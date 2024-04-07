import java.util.List;

public class Quadrado extends Retangulo
{
    public Quadrado(List<Ponto> listapontos) 
    {
        super(listapontos);
        if(!eQuadrado(listapontos))
        {
            Cliente.printError("Quadrado:vi");
        }
    }
    public void rotacionar(double anguloGraus, Ponto pontoRotacao) 
    {
        super.rotacionar(anguloGraus, pontoRotacao);
    }
    public Quadrado(String listapontos) 
    {
        super(listapontos);

        if(this.getPontos().size() != 4)
        {
            Cliente.printError("Quadrado:vi");
        }
        if(eQuadrado(this.getPontos()) || this.getPontos().get(0).dist(this.getPontos().get(1)) != this.getPontos().get(1).dist(this.getPontos().get(2)) || this.getPontos().get(1).dist(this.getPontos().get(2)) != this.getPontos().get(2).dist(this.getPontos().get(3))|| this.getPontos().get(2).dist(this.getPontos().get(3)) != this.getPontos().get(3).dist(this.getPontos().get(0)))
        {
            Cliente.printError("Quadrado:vi");
        }
    }

    static boolean eQuadrado(List<Ponto> pontos)
    {
        for (int i = 0; i < 4; i++) 
        {
            Ponto p1 = pontos.get(i);
            Ponto p2 = pontos.get((i + 1) % 4);
            Ponto p3 = pontos.get((i + 2) % 4);

            Ponto Lado1Valor = new Ponto(Math.abs(p2.getX() - p1.getX()), Math.abs(p2.getY() - p1.getY()));
            Ponto Lado2Valor = new Ponto(Math.abs(p3.getX() - p2.getX()), Math.abs(p3.getY() - p2.getY()));

            int produtoEscalar = Lado1Valor.getX() * Lado2Valor.getX() + Lado1Valor.getY() * Lado2Valor.getY();

            if (produtoEscalar != 0) 
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        String polygonType = "Quadrado: ";
        sb.append(polygonType);
        sb.append("[");
        for(int i = 0; i < this.getPontos().size(); i++) {
            sb.append(this.getPontos().get(i).toString());
            if (i < this.getPontos().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
