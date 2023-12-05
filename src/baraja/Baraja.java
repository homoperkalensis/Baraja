/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baraja;
import java.util.*;
/**
 *
 * @author javie
 */
public class Baraja {
    
    
    private static Scanner sn = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    
     public static void main(String[] args) {
         
        Baraja.crearBaraja();
        Baraja.swap();
        Baraja.eligeUnaOpción();
        
     }
    
    private static ArrayList <Carta> baraja = new ArrayList<Carta>();
    private static ArrayList <Carta> sacadas = new ArrayList<Carta>();
    
    private static int numCartas = 48, pos = 0;
    
    private static boolean noQuedanCartas = false;
    
    public static void crearBaraja()
    {
        numCartas = 48;
        for(int i = 0; i<4; i++)//Asigna el palo
        {
            for(int j = 1; j<13 ; j++)//Asigna el numero de carta
            {
                baraja.add(new Carta(j, i));//Crea las cartas
            }
        }
    }
    
    public static void swap()
    {
         
        int y;//numero random

        for(int i = 0; i<200; i++)//Cambia cartas de posición i veces
        {  
            for(int j = 0; j<48; j++){//Cambia TODAS las cartas a y posición
                y = Utils.getRandomInt(0, 48);
                Carta temp = baraja.get(j);
                baraja.set(j,baraja.get(y));
                baraja.set(y,temp);
            }
        }
    }
    
    public static void mostrarBaraja()
    {
        int i = 1;
        if( noQuedanCartas )
        {
            System.out.println("No quedan cartas en la baraja");
        }
        else
        {    
            for( Carta c: baraja)
            {
                System.out.println(i+": "+c.dameNumero()+" "+c.damePalo());
                i++;
            }
        }
    }
    
    public static void eligeUnaOpción()//Switch case para elegir que hacer
    {
            int opcion;
            do{
                Baraja.noQuedanCartas();//Comprueba si quedan cartas
                System.out.println("\n Seleccione una opción: \n 1.Mostrar baraja \n 2.Obtener carta en una poscición concreta \n 3.Extraer carta en posición \n 4.Extraer una carta concreta \n 5.Extraer un numero de cartas \n 10.Salir");
                opcion = sn.nextInt();
                switch(opcion)
                {
                    case 1 ->{
                        Baraja.noQuedanCartas();
                         if(Baraja.noQuedanCartas)
                        {System.out.println("No quedan cartas");}
                         else{Baraja.mostrarBaraja();}
                    }
                    case 2 ->{
                        Baraja.noQuedanCartas();
                        if(Baraja.noQuedanCartas)
                        {System.out.println("No quedan cartas");}
                        else{
                            System.out.println("¿Qué posición quieres mirar? (quedan " + numCartas + " cartas)");
                            int posi = sn.nextInt();
                              while(numCartas<posi)//Comprueba que en la posición introducida aun queden cartas
                            {
                                System.out.println("Te he dicho que quedan "+ numCartas +" cartas");
                                posi = sn.nextInt();
                            }
                            System.out.println("Posición "+ posi + ": " + Baraja.ObtenerCartaEnPosicion(posi).dameNumero() + " de " + Baraja.ObtenerCartaEnPosicion(posi).damePalo());
                        }
                    }
                    case 3 ->{
                        Baraja.noQuedanCartas();
                        if(Baraja.noQuedanCartas)
                        {System.out.println("No quedan cartas");}
                        else{
                        System.out.println("¿En que posición está la carta que quieres extraer? (quedan " + numCartas + " cartas)");
                        int posi = sn.nextInt();
                        while(numCartas<posi)//Comprueba que en la posición introducida aun queden cartas
                        {
                            System.out.println("Te he dicho que quedan "+ numCartas +" cartas");
                            posi = sn.nextInt();
                        }
                        System.out.println("La carta extraida es el "+Baraja.ExtraerCartaEnPosicion(posi).dameNumero()+ " de " + Baraja.ExtraerCartaEnPosicion(posi).damePalo());
                        baraja.remove(posi-1);
                        numCartas--;
                        }
                    }
                    case 4 ->{
                        Baraja.noQuedanCartas();
                        if(Baraja.noQuedanCartas)
                        {System.out.println("No quedan cartas");}
                        else
                        {
                            System.out.println("¿Qué carta deseas extraer? (primero elige el palo y luego el número)");
                            String palo = sn.next();
                            int num = sn.nextInt();
                            boolean esta = false;
                            for (Carta c: baraja)//primero comprueba que esa carta siga en la baraja
                            {
                                if(c.dameNumero() == num && palo.equals(c.damePalo()))
                                { esta = true ;}
                            }
                            if(esta)
                            {
                                Baraja.ExtraerCarta(palo, num);
                                System.out.println("Se a extraido la carta " + num + " de " + palo + " que estaba en la posición " + (pos+1));//lo hago de esta manera para asegurarnos de que se hace bien
                                numCartas--;
                                baraja.remove(pos);
                            }
                            else
                            {
                                System.out.println("Esa carta ya nos está en la baraja o no existe");
                            }
                        }
                    }
                    case 5 ->{
                        Baraja.noQuedanCartas();
                        if(Baraja.noQuedanCartas)
                        {System.out.println("No quedan cartas");}                        
                        else
                        {
                            System.out.println("¿Cuantas cartas quieres sacar?");
                            int num = sn.nextInt();
                            while (num>numCartas)//Comprueba si se puede sacar ese numero de cartas
                            {System.out.println("No puedes sacar tantas cartas, solo quedan: " + numCartas);}
                            
                            Baraja.ExtraerPrimeras(num);
                            System.out.println("Has sacado el siguiente listado de cartas: ");
                            numCartas -= num;
                            
                            for (Carta c: sacadas)//muestra la lista de cartas sacadas
                            {System.out.println(c.dameNumero()+ " de " + c.damePalo());}

                            for ( int i = (baraja.size()-1); num != 0; num --  )//elimina las cartas sacadas de la baraja
                            {
                                baraja.remove(i);
                                i--;
                            }
                        }
                    }
                    /*case 6 ->{}
                    case 7 ->{}
                    case 8 ->{}
                    case 9 ->{}*/
                    
                }
            }
            while( opcion != 10);
    }
    
     public static void noQuedanCartas()
    {
        if( numCartas == 0 )
        { noQuedanCartas = true; }
    }
    
    public static Carta ObtenerCartaEnPosicion( int posi )
    {
        int posiR = posi-1;//La posición que se introduce es del 0 al numero de cartas que queden en la baraja, por lo que la posición real no es la que introduce
        return baraja.get(posiR);
    }
    
    public static Carta ExtraerCartaEnPosicion( int posi)
    {
        int posiR = posi-1;//La posición que se introduce es del 0 al numero de cartas que queden en la baraja, por lo que la posición real no es la que introduce
        
        return baraja.get(posiR);
    }
    
    public static Carta ExtraerCarta(String palo, int num)
    {
       pos = 0;
       Carta cartax = new Carta();
       boolean encontrada = false;
       for (Carta c: baraja)
       {
           if(c.dameNumero() == num && palo.equals(c.damePalo()))
           {
               cartax = c;
               encontrada = true;
           }
           if( !encontrada )
           {
           pos++;
           }
       }
       
       return cartax;
    }
    
    public static ArrayList ExtraerPrimeras( int nCartas )
    {
        int num = nCartas;
         
        for( int i = baraja.size()-1 ; i > baraja.size()-num-1 ; i--)
        {   
            sacadas.add(baraja.get(i));
        }
        return sacadas;
    }
    /*public static void main(String[] args) {
    
        Baraja.crearBaraja();//creo la baraja
        Baraja.swap();//la barajo
        Baraja.eligeUnaOpción();
        /*Carta jose = new Carta();
        System.out.println(jose.dameNumero() +  " de " +jose.damePalo());
    }
    
    private static int numCartas = 48;
    
    private static int cartasFuera = 0;
    
    private static boolean noQuedanCartas = false;
    
    private static Carta [] baraja = new Carta[48];//Creamos la baraja
    
        public static void eligeUnaOpción()//Switch case para elegir que hacer
    {
            int opcion = 0;
            do{
                Baraja.noQuedanCartas();
                System.out.println("\n Seleccione una opción: \n 1.Obtener carta en una poscición concreta \n 2.Mostrar baraja \n 3.Extraer carta en posición");
                opcion = sn.nextInt();
                switch(opcion)
                {
                    case 1 ->{
                        System.out.println("¿Qué posición de la baraja quieres mirar?");
                        int posicion = sn.nextInt();
                        Baraja.obtenerCartaEnPosicion(posicion);
                    }
                    case 2 ->{
                        Baraja.mostrarBaraja();
                    }
                    case 3 ->{
                        System.out.println("En que posición está la carta que quieres sacar?");
                        int posicion = sn.nextInt();
                        Baraja.extraerCartaEnPosicion(posicion);
                    }
                    /*case 4 ->{}
                    case 5 ->{}
                    case 6 ->{}
                    case 7 ->{}
                    case 8 ->{}
                    case 9 ->{}
                    
                }
            }
            while( opcion != 10);
    }
    
    public static void barajaPrueba()
    {
        
        Carta [] cartas  = new Carta [48];
        for(int i = 0; i<48; i++)
        {
            cartas[i] = new Carta();
        }
        
        for(int i = 0; i<48; i++){
        System.out.println(cartas[i].dameNumero() + " de " + cartas[i].damePalo());
        }
        
    }
    
    public static void crearBaraja()
    {
        
        
        int cont = 0;//asigna posición de carta
        
        for(int i = 0; i<4; i++)//Asigna el palo
        {
            for(int j = 1; j<13 ; j++)//Asigna el numero de carta
            {
                baraja [cont] = new Carta(j, i);//Crea las cartas
                        
                cont++;
            }
        }
    }
    
    public static void swap()
    {
         
        int y;//numero random

        for(int i = 0; i<200; i++)//Cambia cartas de posición i veces
        {  
            for(int j = 0; j<48; j++){//Cambia TODAS las cartas a y posición
                y = Utils.getRandomInt(0, 48);
                Carta temp = baraja[j];
                baraja[j]=baraja[y];
                baraja[y]=temp;
            }
        }
    }
    
    public static void mostrarBaraja()
    {        for(int i = 0; i<48; i++)
        {
            if( noQuedanCartas)//comprobamos si quedan cartas
            {System.out.println("No quedan cartas en la baraja, no hay baraja pues");}
            
            else if(baraja[i].dameNumero() == 0)//comprueba si se han extraido cartas y en ese caso donde haya una carta vacia enseña nada
            {System.out.print(" ");}
            
            else //muestra las cartas que hayan
            {System.out.println("Carta "+ (i+1) +":  "+baraja[i].dameNumero() +" de "+ baraja[i].damePalo());}//Muestra la baraja
        }
    }
    
    public static void obtenerCartaEnPosicion( int posicion )
    {
        if( baraja[posicion-1].dameNumero() == 0 ) //si el numero de la carta es 0 es que está fuera de la baraja, por lo que se entiende que no quedan tantas cartas para llegar a esa posición
        {
            
            System.out.println("No quedan tantas cartas en la baraja, pon un numero más bajo sabiendo que quedan " + numCartas);
        }
        else {System.out.println("La carta en la posicion " + posicion + " es un " + baraja[posicion-1].dameNumero() + " de " + baraja[posicion-1].damePalo());}
    } 
      
    public static void extraerCartaEnPosicion( int posicion )
    {
        if( baraja[posicion-1].dameNumero() == 0 ) //si el numero de la carta es 0 es que está fuera de la baraja, por lo que se entiende que no quedan tantas cartas para llegar a esa posición
        {
            
            System.out.println("No quedan tantas cartas en la baraja, pon un numero más bajo sabiendo que quedan " + numCartas);
        }
        else {
        System.out.println("Has extraido la carta en la posición " + posicion + " y es un " + baraja[posicion-1].dameNumero() + " de " + baraja[posicion-1].damePalo());
        
        baraja[posicion-1] = new Carta(0,0);
        
        Carta temp = baraja[posicion-1];
        
        for( int i = 47; baraja[i].dameNumero() == 0; i--)//recorro el array de arriba a abajo para ver cuantas cartas faltan
        {
            numCartas--;
            cartasFuera++;
        }
        
        for(int j = 0; j<((numCartas-1)-(posicion-1)); j++)//cuento el numero de cartas desde la posicion hasta el numero de cartas que hay
        {
            baraja[(posicion-1)+j] = baraja[(posicion-1)+(j+1)];
        }
        baraja[numCartas-1] = temp;}
    }
    
    public static void extraerCarta( int numero, int palo)
    {
        
    }
    
    public static void noQuedanCartas()
    {
        if( cartasFuera == 48 )
        { noQuedanCartas = true; }
    }
    
    */
        /*public static void crearBaraja() //inicializar array
    {
            for(int i=0; i<48; i++) //rellena el array
            {
                int numero = (int) (Math.floor(Math.random()*(N-M+1)+M));//número aleatorio de carta
                int num = (int) (Math.floor(Math.random()*(NS-MAX+1)+MAX));//posición aleatoria de la baraja
                for(int j=0; j<48; j++) //recorre el array
                {
                    
                    cartas[j] = new Carta(numero, "oros");
                    if(cartas[i].equals(cartas[j])==false)
                    {
                    cartas[num]= cartas[j];
                    }
                }
            }
    }
    
    private static final int M = 1;
    
    private static final int N = 12;
    
    private static final int MAX = 1;
    
    private static final int NS = 48;*/
    
    /*public static void crearBaraja()//Crear una baraja ordenada
    {
        boolean todas = false ; 
        int numeroCarta;
        int contador = 1;
        while ( todas == false )
        {
            for( int j = 0; j<4; j++)
            {
                for(int i = 0; i<12; i++)//asignamos numero a la carta
                {
                    numeroCarta = i+1;
                    cartas [contador] = new Carta(i, Palo.values(j));
                    contador++;
                }
                
            }
        }
    }*/
}
