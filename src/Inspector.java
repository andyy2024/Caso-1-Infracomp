import java.util.Random;

public class Inspector extends Thread {
    
    private BuzonDeReproceso buzonDeReproceso;
    private BuzonDeRevision buzonDeRevision;
    private Deposito deposito;
    private int metaDeProductos;
    private double limiteDeFallos;
    private int fallos;
    private int id;

    public Inspector(BuzonDeReproceso buzonDeReproceso, 
                     BuzonDeRevision buzonDeRevision,
                     Deposito deposito,
                     int metaDeProductos,
                     int id) {
        this.buzonDeReproceso = buzonDeReproceso;
        this.buzonDeRevision = buzonDeRevision;
        this.deposito = deposito;
        this.metaDeProductos = metaDeProductos;
        this.limiteDeFallos = metaDeProductos*0.1;
        this.id = id;
    }

    @Override
    public void run() {
        Producto producto;
        Random rand = new Random();
        while(true){
            System.out.println("entre");
            producto = buzonDeRevision.buscarProductoParaInspeccionar();
            
            while (producto == null){
                // si el buzon no quiso darle un producto
                // lo vuelve a intentar despues de yield()
                // para simular una espera semi-activa
                Thread.yield();
                producto = buzonDeRevision.buscarProductoParaInspeccionar();
            }

            // if (producto.getid() == "FIN"){
            //     break;
            // }

            System.out.println("Inspector " + id + " a recibido producto " + producto.getid());

            int ruleta = rand.nextInt(100) + 1;
            Boolean rechazar = ruleta % 7 == 0;
            if (rechazar && fallos <= limiteDeFallos){
                buzonDeReproceso.reprocesarProducto(producto);
                fallos++;
                System.out.println("Inspector " + id + " a rechazado producto " + producto.getid());
            } else {
                deposito.almacenanar(producto);
                System.out.println("Inspector " + id + " a aprobado producto " + producto.getid());
                if (deposito.getInventarioActual() >= metaDeProductos ){
                    producto = new Producto("FIN");
                    buzonDeReproceso.reprocesarProducto(producto);
                    System.out.println("Inspector " + id + " a enviado producto " + producto.getid());
                    
                    break;
                }
            }
        }
    System.out.println("Inspector " + id + " a finalizado su trabajo");
    
    }
}