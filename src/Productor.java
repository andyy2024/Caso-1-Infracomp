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
            Print.imprimir(new String[]{"Productor-" + id, " buscando productos para ", "reprocesar..."},
               new String[]{Print.FONDO_MAGENTA_CLARO, Print.BLANCO, Print.MORADO_LINDO}); 
            producto = buzonDeReproceso.buscarProductoParaReprocesar();

            //SI hubo un reproceso
            if(producto != null){

            if (producto.getid().equals("FIN")){
            Print.imprimir(new String[]{"Productor-" + id, " ha", " finalizado ","su trabajo"},
               new String[]{Print.FONDO_MAGENTA_CLARO,Print.BLANCO, Print.ROJO_CLARO, Print.BLANCO});
            break;
            }

            // vuelve a mandar el producto
            buzonDeRevision.almacenarProducto(producto);
            Print.imprimir(new String[]{"Productor-" + id, " ha", " reenviado ","producto ", producto.getid()},
               new String[]{Print.FONDO_MAGENTA_CLARO, Print.BLANCO, Print.MAGENTA_CLARO,Print.BLANCO, Print.AMARILLO_CLARO});
            continue;
            } 
            // genera un producto nuevo y lo almacena 
            producto = new Producto("p" + id + "-" + i);
            i++;
            Print.imprimir(new String[]{"Productor-" + id, " ha"," creado"," el producto ", producto.getid()},
               new String[]{Print.FONDO_MAGENTA_CLARO, Print.BLANCO, Print.VERDE_CLARO, Print.BLANCO,Print.AMARILLO_CLARO});
            buzonDeRevision.almacenarProducto(producto);
            Print.imprimir(new String[]{"Productor-" + id," ha", " enviado"," producto ", producto.getid()},
               new String[]{Print.FONDO_MAGENTA_CLARO, Print.BLANCO, Print.MAGENTA_CLARO, Print.BLANCO, Print.AMARILLO_CLARO});
        }
        
    }

}