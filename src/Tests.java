
public class Tests {

    public static void main(String[] args) throws InterruptedException {
        Tests tests = new Tests();
        Print.setSilence(true);
        tests.testProductionProcess();
        tests.testDynamicParameters();
    }

    public void testProductionProcess() throws InterruptedException {
        int trabajadores = 10;
        int metaDeProductos = 20;
        int limiteBuzon = 5;

        BuzonDeReproceso buzonDeReproceso = new BuzonDeReproceso();
        BuzonDeRevision buzonDeRevision = new BuzonDeRevision(limiteBuzon);
        Deposito deposito = new Deposito();

        Productor[] productores = new Productor[trabajadores];
        Inspector[] equipoDeCalidad = new Inspector[trabajadores];

        for (int i = 0; i < trabajadores; i++) {
            productores[i] = new Productor(buzonDeReproceso, buzonDeRevision, i);
            equipoDeCalidad[i] = new Inspector(buzonDeReproceso, buzonDeRevision, deposito, metaDeProductos, i);
        }

        for (int i = 0; i < trabajadores; i++) {
            productores[i].start();
            equipoDeCalidad[i].start();
        }

        for (int i = 0; i < trabajadores; i++) {
            productores[i].join();
            equipoDeCalidad[i].join();
        }

        if (deposito.getInventarioActual() == metaDeProductos){
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }

    public void testDynamicParameters() throws InterruptedException {
        int[] trabajadoresArray = {5, 10, 15};
        int[] metaDeProductosArray = {10, 20, 30};
        int[] limiteBuzonArray = {3, 5, 7};

        test : for (int trabajadores : trabajadoresArray) {
            for (int metaDeProductos : metaDeProductosArray) {
                for (int limiteBuzon : limiteBuzonArray) {
                    BuzonDeReproceso buzonDeReproceso = new BuzonDeReproceso();
                    BuzonDeRevision buzonDeRevision = new BuzonDeRevision(limiteBuzon);
                    Deposito deposito = new Deposito();

                    Productor[] productores = new Productor[trabajadores];
                    Inspector[] equipoDeCalidad = new Inspector[trabajadores];

                    for (int i = 0; i < trabajadores; i++) {
                        productores[i] = new Productor(buzonDeReproceso, buzonDeRevision, i);
                        equipoDeCalidad[i] = new Inspector(buzonDeReproceso, buzonDeRevision, deposito, metaDeProductos, i);
                    }

                    for (int i = 0; i < trabajadores; i++) {
                        productores[i].start();
                        equipoDeCalidad[i].start();
                    }

                    for (int i = 0; i < trabajadores; i++) {
                        productores[i].join();
                        equipoDeCalidad[i].join();
                    }

                    if (deposito.getInventarioActual() != metaDeProductos) {
                        System.out.println("Test failed with parameters: trabajadores = " + trabajadores + ", metaDeProductos = " + metaDeProductos + ", limiteBuzon = " + limiteBuzon);
                        break test;
                    }
                }
            }
        }
    }
}