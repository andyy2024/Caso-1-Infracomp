public class Main {
    
    public static void main(String[] args) {

        boolean enterValuesByConsole = true;

        int trabajadores = 2;
        int metaDeProductos = 20;
        int limiteBuzon = 2;

        if (enterValuesByConsole) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);

            System.out.print("Ingrese el numero de trabajadores: ");
            trabajadores = scanner.nextInt();

            System.out.print("Ingrese la meta de productos: ");
            metaDeProductos = scanner.nextInt();

            System.out.print("Ingrese el limite del buzon: ");
            limiteBuzon = scanner.nextInt();

            scanner.close();
        }
        
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

        for (int i = 0; i < trabajadores; i++) {
            try {
                productores[i].join();
                equipoDeCalidad[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // imprimir el inventario actual vs meta de productos
        System.out.println();
        Print.imprimir(new String[] {"Inventario en deposito: ", deposito.getInventarioActual() + "   ", "Meta de productos: ", metaDeProductos + ""},
                       new String[] {Print.FONDO_VERDE_CLARO, Print.AMARILLO_CLARO, Print.FONDO_VERDE_CLARO, Print.AMARILLO_CLARO});
    }
}
