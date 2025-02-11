public class Main {
    
    public static void main(String[] args) {
        int trabajadores = 1;
        int metaDeProductos = 1;
        int limiteBuzon = 3;
        
        BuzonDeReproceso buzonDeReproceso = new BuzonDeReproceso();
        BuzonDeRevision buzonDeRevision = new BuzonDeRevision(
            limiteBuzon
        );
        Deposito deposito = new Deposito();

        Productor[] productores = new Productor[trabajadores];
        Inspector[] equipoDeCalidad = new Inspector[trabajadores];

        for (int i = 0; i < trabajadores; i++) {
            productores[i] = new Productor(buzonDeReproceso, buzonDeRevision, i);
            equipoDeCalidad[i] = new Inspector(buzonDeReproceso,
                             buzonDeRevision, deposito,
                             metaDeProductos, i);
        }

        for ( int i = 0; i < trabajadores; i++){
            productores[i].start();
        }

        for ( int i = 0; i < trabajadores; i++){
            equipoDeCalidad[i].start();
        }

        // for ( int i = 0; i < trabajadores; i++){
        //     try {
        //         productores[i].join();
        //         equipoDeCalidad[i].join();
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }
        
    }
    
}
