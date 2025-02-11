import java.util.ArrayList;

public class BuzonDeReproceso {
    
    private ArrayList<Producto> reprocesos;

    public BuzonDeReproceso() {
        reprocesos = new ArrayList<>();
    }

    public synchronized void reprocesarProducto(Producto producto){
        reprocesos.add(producto);
        for (Producto productso : reprocesos) {
            System.out.println("a");
            System.out.println(productso.getid());
        }
        
    }

    public synchronized Producto buscarProductoParaReprocesar(){
        for (Producto producto : reprocesos) {
            System.out.println("a");
            System.out.println(producto.getid());
        }
        if (reprocesos.size() == 0) {
            return null;
        }
        return reprocesos.removeLast();
    }
}
