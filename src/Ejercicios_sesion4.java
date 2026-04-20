import java.util.Scanner; //Importo el Scanner, para leer lo que el usuario escriba por teclado.

public class Ejercicios_sesion4 {
    //Zona de declaración de variables
    static String nombreUsuario; //Pido un string, porque el nombre tiene que ser de este tipo
    static int edad;
    static double precioAdeudado;
    static double precioBase = 200;
    static double precioFinal;
    static String respuestaEstudiante;
    static String respuestaMaterial;
    static double descuentoTotal;
    //Zona de declaración de constantes. Como son constantes y no van a cambiar a lo largo del código, se les pone un final delante.
    static final double DESCUENTO_ESTUDIANTE = 0.10;
    static final double DESCUENTO_MENORES = 0.05;
    static final double RECARGO_MATERIAL = 20;
    static final double IVA = 0.10;
    static Scanner scanner = new Scanner(System.in); //"Activo" el scanner, previamente lo habré importado arriba de todo. Cuando quiera que deje de leer le meto el close.
    //Scanner me dio error por no meterlo static al estar fuera del main, poner static cuando está fuera del main siempre.
    static void main() { //Coloco en el main el nombre de las funciones que voy a usar después. De esta forma si algo me da fallo puedo ir "anulando" función a función a ver que es lo que va mal.
        leerDatos();
        esEstudiante(respuestaEstudiante);
        calcularDescuento();
        calcularPrecioFinal();
        precioConIva();
        quiereMaterial(respuestaMaterial);
       imprimirInforme();

    }

    static void leerDatos() { //Void, no me devuelve nada, me guarda ,la info porque tengo el scanner.
        System.out.println("Nombre: ");
        nombreUsuario = scanner.nextLine(); //Poniendolo así, a la variable EDAD se le asigna el valor que el usuario le asigne en esta casilla.
        System.out.println("Edad: ");
        edad = scanner.nextInt();
        System.out.println("¿Eres estudiante? si/no");
        respuestaEstudiante = scanner.next();
        esEstudiante(respuestaEstudiante); //Lo que me salga de este SOUT va a ser el resultado de la variable respuestaEstudiante
        System.out.println("¿Necesitas material? si/no");
        respuestaMaterial = scanner.next();
        quiereMaterial(respuestaMaterial);
    }

    static boolean esEstudiante(String respuestaEstudiante) { //Aquí saco un booleano para saber si es estudiante o no, a través de la respuesta del sout de arriba.
        //Pongo el ignore case con tilde y no y el return, si la respuesta es si es que va a ser estduainte, y el else, es que no va a ser estudiante.
        if (respuestaEstudiante.equalsIgnoreCase("Sí") || //Ignore case para que todo vaya a minuscula, al poner opción con y sin tilde, todas las opciones verdaderas están cubiertas.
                respuestaEstudiante.equalsIgnoreCase("si")) {
            return true; //Si es estudiante, el return es que va a ser true
        } else { //Cualquier otro caso que no sea si, va a ser detectado como no estudiante. Solo se dan dos opciones de respuesta si/no.
            return false; //Si no es estudiante, el return es que va a ser false
        }

    }

    static boolean quiereMaterial(String respuestaMaterial) { //Exactamente el mismo caso que la función anterior, en este caso para ver si quiere material.
        if (respuestaMaterial.equalsIgnoreCase("si") ||
                respuestaMaterial.equalsIgnoreCase("Sí")) {
            return true; //Si la respuesta de que necesita material es un si, el boolean será true
        } else {
            return false; //No necesita --> booleano falso.
        }

    }
    static double calcularDescuento() { //Función que me va a devolver un double, por tanto, static DOUBLE. Aquí calculo el descuento total para el informe final
        if (edad < 18 && esEstudiante(respuestaEstudiante)) { //Voy generando estructuras de if/else if/else, cubriendo todas las posibilidades.
            descuentoTotal = (DESCUENTO_ESTUDIANTE + DESCUENTO_MENORES);
            System.out.println("Menor y estudiante tu descuento es: " + descuentoTotal);//En función de la respuesta que encaje, se varía el valor de descuentoTotal, que, al no ser final, puede ir variando a lo largo de la función.
        } else if (esEstudiante(respuestaEstudiante)) {
            descuentoTotal = DESCUENTO_ESTUDIANTE;
            System.out.println("Mayor y estudiante tu descuento es: " + (descuentoTotal * 100) + " % ");
        } else if (edad < 18) {
            descuentoTotal = DESCUENTO_MENORES; //Voy poniendo los souts para ver que la función pasa por todas las opciones y no dejo nada al azar, además del cálculo de *100 para que el porcentaje se muestre bien.
            System.out.println("Menor y no estudiante tu descuento es: " + (descuentoTotal * 100) + " % ");
        } else {
            descuentoTotal = 0;
            System.out.println("No eres menor ni estudias, tu descuento es " + (descuentoTotal * 100) + " % ");
        }
        return descuentoTotal; //El return, que es lo que me devuelve la función, va a ser el valor de descuentoTotal, una vez haya pasado por todos los if.
    }
    static double calcularPrecioFinal() { //Misma estructura que en la función anterior, pero ahora, se aplica directamente el descuento sobre el precio.
        if (edad < 18 && esEstudiante(respuestaEstudiante)) {
            precioFinal = precioBase - (precioBase * (DESCUENTO_ESTUDIANTE + DESCUENTO_MENORES)); //Si es menor y estudiante, se aplican todos los descuentos.
        } else if (esEstudiante(respuestaEstudiante)) { //Si es estudiante, pero mayor de 18, se aplica esta.
            precioFinal = precioBase - (precioBase * DESCUENTO_ESTUDIANTE);
        } else if (edad < 18) { //Arriba se descartó que fuera estudiante, de ahí que la función haya seguido yendo para abajo. En este caso pregunta su edad, si es menor de 18, descuento.
            precioFinal = precioBase - (precioBase * DESCUENTO_MENORES);
        } else { //Si eres viejo y no estudias no descuentas.
            precioFinal = precioBase;
        } System.out.println("Tu precio sin IVA ni material es: " + precioFinal);
        return precioFinal; //Me devuelve el precio final (sin iva y sin material), que, en función de las circuntancias de cada uno, será uno u otro.
    }

     static double precioConIva() { //El iva se aplica al precio final, por lo que primero tengo que saber, los descuentos (los tengo arriba) y si quiere material o no.
        if (quiereMaterial(respuestaMaterial)) {
            precioAdeudado = ((precioFinal + RECARGO_MATERIAL) * IVA) + (precioFinal);
            System.out.println("El total adeudado es: " + precioAdeudado);
        } else {
            precioAdeudado = ((precioFinal * IVA) + precioFinal);
        }
        System.out.println("El total adeudado es: " + precioAdeudado);
        return precioAdeudado;
    }
    static void imprimirInforme (){ //Imprimo el informe
        System.out.println("---INFORME---");
        System.out.println("Nombre: " + nombreUsuario);
        System.out.println("Edad: " + edad);
        System.out.printf("Se te ha descontado un %.2f%% sobre el precio final%n", descuentoTotal * 100);
        /* Printf porque el salto de linea se da con el %n del final. Pongo %2.f%% para redondear dos cifras al final del cálculo. En el lugar donde coloclo el %2.f%%
        es donde se hace la cuenta, que se coloca después del texto con coma y sin paréntesis*/
        System.out.println("El total adeudado es: " + precioAdeudado + "€");
    }

}


