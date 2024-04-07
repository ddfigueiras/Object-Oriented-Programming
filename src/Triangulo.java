import java.util.List;

public class Triangulo extends Poligono 
{

    public Triangulo(List<Ponto> pontosRecebidos) 
    {
        super(pontosRecebidos);
        if(this.getPontos().size() != 3)
            Cliente.printError("Triangulo: VI");
    }
    public void rotacionar(double anguloGraus, Ponto pontoRotacao) 
    {
        super.rotacionar(anguloGraus, pontoRotacao);
    }
    public Triangulo(String pontosRecebidos) 
    {
        super(pontosRecebidos);

        if (this.getPontos().size() != 3) {
            Cliente.printError("Retângulo:vi");
        }
    }
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        String polygonType = "Triangulo: ";
        sb.append(polygonType);
        sb.append("[");
        for (int i = 0; i < getPontos().size(); i++) {
            sb.append(getPontos().get(i).toString());
            if (i < getPontos().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
