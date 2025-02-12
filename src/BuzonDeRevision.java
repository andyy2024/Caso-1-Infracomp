import java.util.ArrayList;

public class BuzonDeRevision {

    //buffer
    private int limiteDeAlmacenamiento;
    private ArrayList<Producto> productosAlmacenados;
    private boolean disponibilidad = true;

    public BuzonDeRevision(int limiteDeAlmacenamiento) {
        this.limiteDeAlmacenamiento = limiteDeAlmacenamiento;
        productosAlmacenados = new ArrayList<>();
    }


    public synchronized Producto buscarProductoParaInspeccionar(){

        if (productosAlmacenados.size() == 0){
            return null;
        }
        Producto producto = productosAlmacenados.removeLast();
        notify();
        return producto;
    }

    public synchronized boolean vacio(){
        return productosAlmacenados.size() == 0;
    }

    public synchronized void almacenarProducto(Producto producto) {
        while (productosAlmacenados.size() == limiteDeAlmacenamiento){
            try {
                //espera pasiva
                wait();
            } catch (InterruptedException e) {e.printStackTrace();}
        }

        productosAlmacenados.add(producto);
    }
}