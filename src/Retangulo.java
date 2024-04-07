import java.util.List;

public class Retangulo extends Poligono
{
    public Retangulo(List<Ponto> pontos) 
    {
        super(pontos);
        if (pontos.size() != 4) {
            Cliente.printError("Retângulo:vi");
        }
    }

    public Retangulo(String pontosStr) 
    {
        super(pontosStr);
        if (this.getPontos().size() != 4) {
            Cliente.printError("Retângulo:vi");
        }
    }
    public void rotacionar(double anguloGraus, Ponto pontoRotacao) 
    {
        super.rotacionar(anguloGraus, pontoRotacao);
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        String polygonType = "Retangulo: ";
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
