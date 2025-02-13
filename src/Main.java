public class Main {
    
    public static void main(String[] args) {
        int trabajadores =10;
        int metaDeProductos = 100;
        int limiteBuzon = 5;
        
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
            equipoDeCalidad[i].start();
        }
    }
}
