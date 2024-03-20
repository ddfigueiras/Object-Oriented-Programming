import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ClienteTest {

        @Test
        public void testNegative() 
        {
                assertEquals("Ponto:vi", new Ponto(-3, 0));
                assertEquals("Ponto:vi", new Ponto(-3, -4));
                assertEquals("Ponto:vi", new Ponto(3, -5));
                assertEquals("Ponto:vi", new Ponto(3, 4));
        }

        @Test
        public void testPerimetro() 
        {
                List<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(1, 1));
                pontos.add(new Ponto(2, 2));
                pontos.add(new Ponto(3, 3));
                Poligono poligono = new Poligono(pontos);
                
                assertEquals(4.24, poligono.perimetro(), 0.01);
        }

        @Test
        public void testPerimetro_Quadrado() 
        {
                List<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(0, 0));
                pontos.add(new Ponto(0, 3));
                pontos.add(new Ponto(3, 3));
                pontos.add(new Ponto(3, 0));
                Poligono poligono = new Poligono(pontos);

                assertEquals(12.0, poligono.perimetro(), 0.01);
        }

        @Test
        public void testPerimetro_PentagonoRegular() {
                List<Ponto> pontos = new ArrayList<>();
                pontos.add(new Ponto(0, 0));
                pontos.add(new Ponto(4, 0));
                pontos.add(new Ponto(5, 3));
                pontos.add(new Ponto(2, 5));
                pontos.add(new Ponto(-1, 3));
                Poligono poligono = new Poligono(pontos);

                assertEquals(15.63, poligono.perimetro(), 0.01);
        }
        @Test
        public void test1N2N1() 
        {
                Ponto ponto1 = new Ponto(0, 0);
                Ponto ponto2 = new Ponto(3, 0);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                assertEquals("Poligono:vi", new Poligono(pontosPoligonoTeste).toString());
        }
        public void test2N2N1() 
        {
                Ponto ponto1 = new Ponto(4, 0);
                Ponto ponto2 = new Ponto(3, 2);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                assertEquals("Poligono:vi", new Poligono(pontosPoligonoTeste).toString());
        }
        public void test3N2N1() 
        {
                Ponto ponto1 = new Ponto(03, 17);
                Ponto ponto2 = new Ponto(26, 05);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                assertEquals("Poligono:vi", new Poligono(pontosPoligonoTeste).toString());
        }
        public void test4N2N1() 
        {
                Ponto ponto1 = new Ponto(2513, 21351);
                Ponto ponto2 = new Ponto(32135, 52213);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                assertEquals("Poligono:vi", new Poligono(pontosPoligonoTeste).toString());
        }
        @Test
        public void testSegmento() 
        {
                Ponto ponto1 = new Ponto(10, 10);
                Ponto ponto2 = new Ponto(100, 100);
                Ponto ponto3 = new Ponto(200, 200);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                pontosPoligonoTeste.add(ponto3);
                assertEquals("Segmento:vi", new Poligono(pontosPoligonoTeste).toString());
        }
        @Test
        public void testSegmento1() 
        {
                Ponto ponto1 = new Ponto(0, 0);
                Ponto ponto2 = new Ponto(1, 1);
                Ponto ponto3 = new Ponto(2, 2);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                pontosPoligonoTeste.add(ponto3);
                assertEquals("Segmento:vi", new Poligono(pontosPoligonoTeste).toString());
        }
        @Test
        public void testSegmento2() 
        {
                Ponto ponto1 = new Ponto(10, 5);
                Ponto ponto2 = new Ponto(20, 5);
                Ponto ponto3 = new Ponto(3, 5);
                
                List<Ponto> pontosPoligonoTeste = new ArrayList<>();
                pontosPoligonoTeste.add(ponto1);
                pontosPoligonoTeste.add(ponto2);
                pontosPoligonoTeste.add(ponto3);
                assertEquals("Segmento:vi", new Poligono(pontosPoligonoTeste).toString());
        }
}