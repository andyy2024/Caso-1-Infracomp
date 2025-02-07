import java.util.ArrayList;

public class BuzonDeRevision {

    //buffer
    private int limiteDeAlmacenamiento;
    private ArrayList<Producto> productosAlmacenados;

    public BuzonDeRevision(int limiteDeAlmacenamiento) {
        this.limiteDeAlmacenamiento = limiteDeAlmacenamiento;
        productosAlmacenados = new ArrayList<>();
    }

    public synchronized void almacenarProducto(Producto producto) {
        while (productosAlmacenados.size() == limiteDeAlmacenamiento)
            try {
                //espera pasiva
                wait();
            } catch (InterruptedException e) {e.printStackTrace();}

        productosAlmacenados.add(producto);
        notify(); 
        // solo puede entrar uno al tiempo
        // no tiene sentido despertar a todos

    }

    public synchronized Producto buscarProductoParaInspeccionar(){

        if (productosAlmacenados.size() == 0){
            return null;
        }
        return productosAlmacenados.getLast();

    }
}