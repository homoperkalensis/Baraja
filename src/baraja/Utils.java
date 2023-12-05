/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baraja;

import java.util.Random;

/**
 *
 * @author javie
 */
public class Utils {
    
    private static Random r = new Random();
    
            public static double getRandom(double min, double max)
        {
            if (min > max)
            {
                return getRandom(max, min);
            }
            return r.nextDouble() * (max - min) + min;
        }
        public static int getRandomInt(int min, int max)
        {
            if (min > max)
            {
                return getRandomInt(max, min);
            }
            return r.nextInt(min, max);
        }
}
