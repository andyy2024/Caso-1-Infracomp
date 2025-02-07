import java.util.ArrayList;

public class Deposito {
    
    private ArrayList<Producto> productosAprobados;

    public Deposito() {
        productosAprobados = new ArrayList<>();
    }

    public synchronized void almacenanar(Producto producto){
        productosAprobados.add(producto);
    }

    public synchronized int getInventarioActual(){
        return productosAprobados.size();
    }

}
