public class Productor extends Thread {

    private BuzonDeReproceso buzonDeReproceso;
    private BuzonDeRevision buzonDeRevision;
    private int id;
    
    public Productor(BuzonDeReproceso buzonDeReproceso, 
                    BuzonDeRevision buzonDeRevision,
                    int id) {
        this.buzonDeReproceso = buzonDeReproceso;
        this.buzonDeRevision = buzonDeRevision;
        this.id = id;
    }

    @Override
    public void run() {
    // Resumen:
    // Verifica si hay productos en el buzon de reprocesos
    // Si no, vuelve a revizar hasta que este vacio.
    // Una vez este vacio, almacena el producto en el 
    // buzon de revision 
        int i = 0;
        Producto producto;
        while(true){
            
            //chekar si hay reprocesos
            System.out.println("Productor-" + id + " buscando productos para reprocesar...");
            producto = buzonDeReproceso.buscarProductoParaReprocesar();
            
            //SI hubo un reproceso
            if(producto != null){
                
                if (producto.getid() == "FIN"){
                    System.out.println("Productor-" + id + " a finalizado su trabajo");
                    break;
                }

                // vuelve a mandar el producto
                buzonDeRevision.almacenarProducto(producto);
                System.out.println("Productor-" + id + " a reenviado producto " + producto.getid());
                continue;
            } 
            // genera un producto nuevo y lo almacena 
            producto = new Producto("p" + id + "-" + i);
            i++;
            buzonDeRevision.almacenarProducto(producto);
            System.out.println("Productor-" + id + " a enviado producto " + producto.getid());
        }
        
    }

}