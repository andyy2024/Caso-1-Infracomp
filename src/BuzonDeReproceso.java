import java.util.ArrayList;

public class BuzonDeReproceso {
    
    private ArrayList<Producto> reprocesos;

    public BuzonDeReproceso() {
        reprocesos = new ArrayList<>();
    }

    public synchronized void reprocesarProducto(Producto producto){
        reprocesos.add(producto);
    }

    public synchronized Producto buscarProductoParaReprocesar(){
        if (reprocesos.size() == 0) {
            return null;
        }
        return reprocesos.removeLast();
    }
}
