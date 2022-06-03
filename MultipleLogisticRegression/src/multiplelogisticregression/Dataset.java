//https://data.princeton.edu/wws509/notes/c3.pdf
//https://kseow.com/logisticregression
package multiplelogisticregression;

public class Dataset {
    /*
    public double[][] x = {
            {1,1,1},
            {1,4,2},
            {1,2,4}       
        }; 
    public double[] y = {
            0,
            1,
            1
        };
     //alpha = 0.1, Iter = 100;
    */
    
    /*
    public double[][] x = {
            {53,6,59},
            {10,4,14},
            {212,52,264},  
            {50,10,60},
            {60,14,74},
            {19,10,29},
            {155,54,209},
            {65,27,92},
            {112,33,145},
            {77,80,157},
            {118,46,164},
            {68,78,146},
            {35,6,41},
            {46,48,94},
            {8,8,16},
            {12,31,43},
        }; 
    public double[] y = {
            1,
            0,
            1,
            0,
            1,
            0,
            1,
            0,
            1,
            0,
            1,
            0,
            1,
            0,
            1,
            0
        };
    */
    
    public double[][] x = {
            {1,780,4,3},
            {1,750,3.9,4},
            {1,690,3.3,3},
            {1,710,3.7,5},
            {1,680,3.9,4},
            {1,730,3.7,6},
            {1,690,2.3,1},
            {1,720,3.3,4},
            {1,740,3.3,5}
        };
    public double[] y = {
            1,
            1,
            0,
            1,
            0,
            1,
            0,
            1,
            1
        };  //Iter = 100000 alpha = 0.1;
    
    int n = y.length;

    public int getN() {
        return n;
    }

    public double[][] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }
    
}
