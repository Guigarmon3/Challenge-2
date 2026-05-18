import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
--- MENÚ MASTERCHEF ---
1. Cargar ficheros de participantes
2. Mostrar todos los participantes
3. Buscar participante por nombre
4. Generar fichero participantes.txt
5. Mostrar estadísticas
Escribe 'FIN' para salir
                """);

        try (BufferedReader respuesta = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("tu respuesta es: ");
            String numero = respuesta.readLine();

            if (numero.equalsIgnoreCase("FIN")) {
                System.out.println("Saliendo del programa. ¡Hasta pronto!");
                return;
            }

            switch (numero) {
                case "1" -> Ejercicio1();
                case "2" -> Ejercicio2();
                case "3" -> Ejercicio3();
                case "4" -> Ejercicio4();
                case "5" -> Ejercicio5();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void Ejercicio5() {
        int contador_rojo = 0;
        int contador_azul = 0;

        try (BufferedReader rojo = new BufferedReader(new FileReader("cocina_roja.txt"))) {
            while (rojo.readLine() != null) {
                contador_rojo++;
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_roja.txt");
        }

        try (BufferedReader azul = new BufferedReader(new FileReader("cocina_azul.txt"))) {
            while (azul.readLine() != null) {
                contador_azul++;
            }
        } catch (IOException e) {
            System.out.println("No se ha podido lere el archivo cocina_azul.txt");
        }

        System.out.println("--- ESTADISTICAS ---");
        System.out.println("Participantes en Cocina roja: " + contador_rojo);
        System.out.println("Participantes en Cocina azul: " + contador_azul);
        System.out.println("Total de participantes:  " + (contador_azul + contador_rojo));
    }

    private static void Ejercicio4() {
        ArrayList<String> personas = new ArrayList<>();
        String linea;

        try (BufferedReader azul = new BufferedReader(new FileReader("cocina_azul.txt"))) {
            while ((linea = azul.readLine()) != null) {
                personas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_azul.txt");
        }

        try (BufferedReader rojo = new BufferedReader(new FileReader("cocina_roja.txt"))) {
            while ((linea = rojo.readLine()) != null) {
                personas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_roja.txt");
        }

        personas.sort(Comparator.naturalOrder());

        try (BufferedWriter contenido = new BufferedWriter(new FileWriter("participantes.txt"))) {
            for (String persona : personas) {
                contenido.write(persona);
                contenido.newLine();
            }
            System.out.println("Se ha creado el archivo participantes.txt con " + personas.size() + " participantes ordenados alfabeticamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo participantes.txt");
        }
    }

    private static void Ejercicio3() {
        try (BufferedReader nombreScanner = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Nombre de la persona: ");
            String nom = nombreScanner.readLine();
            String linea;

            try (BufferedReader rojo = new BufferedReader(new FileReader("cocina_roja.txt"))) {
                while ((linea = rojo.readLine()) != null) {
                    if (linea.equalsIgnoreCase(nom)) {
                        System.out.println(linea + " Está en Rojo");
                    }
                }
            } catch (IOException e) {
                System.out.println("No se ha podido leer el archivo cocina_roja.txt");
            }

            try (BufferedReader azul = new BufferedReader(new FileReader("cocina_azul.txt"))) {
                while ((linea = azul.readLine()) != null) {
                    if (linea.equalsIgnoreCase(nom)) {
                        System.out.println(linea + " Está en Azul");
                    }
                }
            } catch (IOException e) {
                System.out.println("No se ha podido leer el archivo cocina_azul.txt");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void Ejercicio2() {
        String linea;

        try (BufferedReader rojo = new BufferedReader(new FileReader("cocina_roja.txt"))) {
            while ((linea = rojo.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_roja.txt");
        }

        try (BufferedReader azul = new BufferedReader(new FileReader("cocina_azul.txt"))) {
            while ((linea = azul.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_azul.txt");
        }
    }

    private static void Ejercicio1() {
        ArrayList<String> cantidad = new ArrayList<>();
        String linea;

        try (BufferedReader azul = new BufferedReader(new FileReader("cocina_azul.txt"))) {
            while ((linea = azul.readLine()) != null) {
                cantidad.add(linea);
            }
            System.out.println("Participantes de Cocina Azul cargados: " + cantidad.size());
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_azul.txt");
        }

        cantidad.clear();

        try (BufferedReader rojo = new BufferedReader(new FileReader("cocina_roja.txt"))) {
            while ((linea = rojo.readLine()) != null) {
                cantidad.add(linea);
            }
            System.out.println("Participantes de Cocina Roja cargados: " + cantidad.size());
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo cocina_roja.txt");
        }
    }
}