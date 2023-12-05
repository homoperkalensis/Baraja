/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baraja;

/**
 *
 * @author javie
 */
public class Carta {
    
    private int number;
    
    private String palo;
    
    //private enum Palo{oros, copas, espadas, bastos;}
    
    
    private final String palos[]={"oros","copas","espadas","bastos"};
    
    
    public int dameNumero()
    {
        return number;
    }
    
    public String damePalo()
    {
        return palo;
    }
    
    /*public String damePalo()
    {
        return Palo.values()[];
    }*/

    Carta()//constructor defecto
    {
        number = 1;
        //Palo palo = Palo.oros;
        palo = palos[0];
    }
    
    Carta(int number, int palo)
    {
        this.number = number;
        //Palo Elpalo = Enum.valueOf(Palo.class, palo);
        this.palo = palos[palo];
    }
    
}
