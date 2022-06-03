
package multiplelogisticregression;

public class Model {
    Dataset ds = new Dataset();
    public Model() {
    }
    int n = ds.getY().length;
    double[]w = new double[n]; //weights
    double[]fx = new double[n]; //f(xi)-yi

    public double[] getW() {
        return w;
    }

    public void setW(double[] w) {
        this.w = w;
    }

    public double[] getFx() {
        return fx;
    }

    public void setFx(double[] fx) {
        this.fx = fx;
    }
    
    
    
}
