public class PoligonoRetangulo 
{
    private int xMax, yMax, xMin, yMin;

    public PoligonoRetangulo(int[] valores) {
        this.xMax = valores[0];
        this.yMax = valores[1];
        this.xMin = valores[2];
        this.yMin = valores[3];
    }
    public int getxMax() {
        return this.xMax;
    }
    public int getyMax() {
        return this.yMax;
    }
    public int getxMin() {
        return this.xMin;
    }
    public int getyMin() {
        return this.yMin;
    }

    public boolean intercept(PoligonoRetangulo pr1)
    {
        if((this.getxMax() == pr1.getxMax() && this.getxMin() == pr1.getxMin()) && ((this.getyMin() < pr1.getxMax() && pr1.getyMax() <= this.getyMax()) || (pr1.getyMin() <= this.getyMax() && this.getyMax() <= pr1.getyMax())))
        {
            return true;
        }
        else if ((this.getyMax() == pr1.getyMax() && this.getyMin() == pr1.getyMin()) && ((this.getxMin() < pr1.getxMax() && pr1.getxMax() < this.getxMax()) || (pr1.getxMin() < this.getxMax() && this.getxMax() < pr1.getxMax())))
        {
            return true;
        }
        else if(((this.getxMin() < pr1.getxMax() && pr1.getxMax() < this.getxMax()) || (pr1.getxMin() < this.getxMax() && this.getxMax() < pr1.getxMax())) && ((this.getyMin() < pr1.getyMax() && pr1.getyMax() < this.getyMax()) || (pr1.getyMin() < this.getyMax() && this.getyMax() < pr1.getyMax())))
        {
            return true;
        }
        return false;
    } 
}