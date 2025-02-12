public class Print {

        private static boolean silence = false;

        // Códigos ANSI para colores
        public static final String RESET = "\u001B[0m";    // Restablecer color
        public static final String ROJO = "\u001B[31m";
        public static final String VERDE = "\u001B[32m";
        public static final String AMARILLO = "\u001B[33m";
        public static final String AZUL = "\u001B[34m";
        public static final String MAGENTA = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String BLANCO = "\u001B[37m";
        public static final String ROJO_CLARO = "\u001B[38;5;202m";
        public static final String MAGENTA_CLARO = "\u001B[35;1m";
        public static final String VERDE_CLARO = "\u001B[38;5;156m";
        public static final String CYAN_CLARO = "\u001B[36;1m";
        public static final String AMARILLO_CLARO = "\u001B[33;1m";
        public static final String AZUL_CLARO = "\u001B[34;1m";
        public static final String NARANJA = "\u001B[38;5;208m";
        public static final String ROSADO = "\033[38;5;212m";  
        public static final String MORADO_LINDO = "\033[38;5;177m";  

        // Estilos
        public static final String NEGRITA = "\u001B[1m";
        public static final String SUBRAYADO = "\u001B[4m";
        public static final String ITALICA = "\u001B[3m";
        public static final String PARPADEO = "\u001B[5m";
        public static final String TACHADO = "\u001B[9m";

        // fondos
        public static final String FONDO_VERDE_CLARO = "\u001B[42;1m";
        public static final String FONDO_NEGRO_CLARO = "\u001B[40;1m";
        public static final String FONDO_ROJO_CLARO = "\u001B[41;1m";
        public static final String FONDO_AMARILLO_CLARO = "\u001B[43;1m";
        public static final String FONDO_AZUL_CLARO = "\u001B[44;1m";
        public static final String FONDO_MAGENTA_CLARO = "\u001B[45;1m";
        public static final String FONDO_CYAN_CLARO = "\u001B[46;1m";
        public static final String FONDO_BLANCO_CLARO = "\u001B[47;1m";
        public static final String FONDO_ROSADO = "\u001B[48;5;198m";

        // To use these codes in Java, you'll need to use the escape sequence
        // \u001b[ followed by the color code and then m.
        // General format:
        // System.out.print("\u001b[" + COLOR_CODE + "m" + "Your Text" + "\u001b[0m");

        // 256-Color Codes: 
        // The codes beyond the basic 16 colors (like the ones in the larger
        // sections of your image, 16-231) are for more fine-grained color control. 
        // These use a slightly different format:
        // Format: 
        //     \u001b[38;5;COLOR_CODEm for foreground, 
        //     \u001b[48;5;COLOR_CODEm for background.

        public static void main(String[] args) {
            // EJEMPLO
            System.out.println(ROJO + NEGRITA + "Este es un texto en rojo" + RESET);
            System.out.println(VERDE + ITALICA + "Este es un texto en verde" + RESET);
            System.out.println(AMARILLO + TACHADO + "Este es un texto en amarillo" + RESET);
            System.out.println(AZUL + SUBRAYADO + "Este es un texto en azul" + RESET);
            System.out.println(MAGENTA + "Este es un texto en magenta" + RESET);
            System.out.println(CYAN + "Este es un texto en cyan" + RESET);
            System.out.println(NARANJA + "Este es un texto en naranja" + RESET);
            System.out.println(ROSADO + "Este es un texto en rosado" + RESET);


        }

        public static void imprimir(String[] textos, String[] formatos) {
            String texto = "";
            for (int i = 0; i < textos.length; i++) {
                texto += formatos[i] + textos[i] + RESET;
            }

            if (!silence)
            System.out.println(texto);
        }

        public static void setSilence(boolean silence) {
            Print.silence = silence;
        }
}
