import java.util.Random;

public class Inspector extends Thread {
    
    private BuzonDeReproceso buzonDeReproceso;
    private BuzonDeRevision buzonDeRevision;
    private Deposito deposito;
    private int metaDeProductos;
    private double limiteDeFallos;
    private int fallos;
    private int id;
    private boolean FIN = false;

    public Inspector(BuzonDeReproceso buzonDeReproceso, 
                     BuzonDeRevision buzonDeRevision,
                     Deposito deposito,
                     int metaDeProductos,
                     int id) {
        this.buzonDeReproceso = buzonDeReproceso;
        this.buzonDeRevision = buzonDeRevision;
        this.deposito = deposito;
        this.metaDeProductos = metaDeProductos;
        this.limiteDeFallos = metaDeProductos * 0.1;
        this.id = id;
    }

    @Override
    public void run() {

        Producto producto;
        Random rand = new Random();
        fallos = 0;

        terminar : while (true) {

            Print.imprimir(new String[]{"Inspector-" + id, " buscando productos para"," inspeccionar..."},
                           new String[]{Print.FONDO_ROSADO,Print.BLANCO, Print.ROSADO});
            producto = buzonDeRevision.buscarProductoParaInspeccionar();

            if (producto == null) {
                

            } else {
            }

            while (producto == null) {
                Print.imprimir(new String[]{"Inspector-" + id, " no recibio"," producto"},
                               new String[]{Print.FONDO_ROSADO, Print.ROJO_CLARO, Print.BLANCO});
                if (buzonDeRevision.vacio() && FIN) {break terminar;}

                Thread.yield();
                producto = buzonDeRevision.buscarProductoParaInspeccionar();
            }

            Print.imprimir(new String[]{"Inspector-" + id, " ah"," recibido", " el producto ", producto.getid()},
                               new String[]{Print.FONDO_ROSADO, Print.BLANCO, Print.VERDE_CLARO, Print.BLANCO, Print.AMARILLO_CLARO});

            int ruleta = rand.nextInt(100) + 1;
            Boolean rechazar = ruleta % 7 == 0;
            if (rechazar && fallos <= limiteDeFallos) {
                buzonDeReproceso.reprocesarProducto(producto);
                fallos++;
                Print.imprimir(new String[]{"Inspector-" + id, " ha ", "rechazado", " producto ", producto.getid()},
                               new String[]{Print.FONDO_ROSADO, Print.BLANCO, Print.ROJO_CLARO, Print.BLANCO, Print.AMARILLO_CLARO});

            } else {
                deposito.almacenanar(producto);
                Print.imprimir(new String[]{"Inspector-" + id, " ha"," aprobado"," producto ", producto.getid()},
                               new String[]{Print.FONDO_ROSADO, Print.BLANCO, Print.VERDE_CLARO, Print.BLANCO, Print.AMARILLO_CLARO});
                if (deposito.getInventarioActual() >= metaDeProductos) {
                    FIN = true;
                    producto = new Producto("FIN");
                    buzonDeReproceso.reprocesarProducto(producto);
                    Print.imprimir(new String[]{"Inspector-" + id, " ha"," enviado"," producto ", producto.getid()},
                                   new String[]{Print.FONDO_ROSADO, Print.BLANCO,Print.VERDE_CLARO,Print.BLANCO, Print.AMARILLO_CLARO});

                    if (buzonDeRevision.vacio()) {
                        break;
                    }
                }
            }
        }
        Print.imprimir(new String[]{"Inspector-" + id, " ha", " finalizado", " su trabajo"},
                       new String[]{Print.FONDO_ROSADO, Print.BLANCO, Print.ROJO_CLARO, Print.BLANCO});
    }
}