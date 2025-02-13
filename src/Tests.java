import java.util.Random;


public class Tests {

    public static void main(String[] args) throws InterruptedException {
        Tests tests = new Tests();
        Print.setSilence(true);
        tests.testProductionProcess();
        // tests.testDynamicParameters();
    }

    public Deposito run(int trabajadores, int metaDeProductos, int limiteBuzon) throws InterruptedException{
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

        return deposito;
    }

    public void testProductionProcess() throws InterruptedException {

        int trabajadores = 100;
        int metaDeProductos = 10000;
        int limiteBuzon = 10;


        Deposito deposito = run(trabajadores, metaDeProductos, limiteBuzon);

        String result = "Inventario actual: " + deposito.getInventarioActual() + ", Meta de productos: " + metaDeProductos;
        if (deposito.getInventarioActual() >= metaDeProductos){
            System.out.println("Test passed     " + result);
        } else {
            System.out.println("Test failed     " + result);
        }
    }

    public void testDynamicParameters() throws InterruptedException {
        Random random = new Random();
        int size = 5;
        int[] trabajadoresArray = random.ints(size, 5, 16).toArray();
        int[] metaDeProductosArray = random.ints(size, 10, 31).toArray();
        int[] limiteBuzonArray = random.ints(size, 3, 8).toArray();

        test : for (int trabajadores : trabajadoresArray) {
            for (int metaDeProductos : metaDeProductosArray) {
                for (int limiteBuzon : limiteBuzonArray) {

                    Deposito deposito = run(trabajadores, metaDeProductos, limiteBuzon);

                    String result = "Inventario actual: " + deposito.getInventarioActual() + ", Meta de productos: " + metaDeProductos;
                    if (deposito.getInventarioActual() >= metaDeProductos){
                        System.out.println("Test passed     " + result);
                    } else {
                        System.out.println("Test failed     " + result);
                        break test;
                    }
                }
            }
        }
    }


}