package com.nttdata.mvn;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Ismael Mercado Calero
 * @version 1.0
 */
public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		int INTENTOS_TOTALES = 0; // Constante con el limite de fallos
		int intentos = 0;
		int aciertos = 0;
		int alea = 0;
		char[] desguazada;
		char[] copia;
		char[] tusRespuestas;
		String palElegida;
		// Leer por teclado
		Scanner teclado = new Scanner(System.in);
		teclado.useDelimiter("\n");
		char resp;
		String resp2;
		// Random para elegir una palabra al azar
		Random rnd = new Random();

		// Creamos unas palabras y le asignamos una aleatoria a un String

		String[] arrayPalabras = new String[3];
		arrayPalabras[0] = "bootcamp";
		arrayPalabras[1] = "formacion";
		arrayPalabras[2] = "nttdata";

		do {

			// Desguazamos la palabra y la guardamos en un array de caracteres
			alea = rnd.nextInt(arrayPalabras.length);
			INTENTOS_TOTALES = alea + 7;
			desguazada = desguaza(arrayPalabras[alea]);
			copia = desguaza(arrayPalabras[alea]); // Algo auxiliar para mas tarde
			palElegida = arrayPalabras[alea];
			// Array para pintar en pantalla(Guiones o letras)
			tusRespuestas = new char[desguazada.length];

			// Rellenamos palabras con guiones
			for (int i = 0; i < tusRespuestas.length; i++) {
				tusRespuestas[i] = '_';
			}

			// Empezamos a pintar en pantalla
			System.out.println("Adivina la palabra!");

			// Mientras que no nos pasemos con los intentos y no la acertemos...
			while (intentos < INTENTOS_TOTALES && aciertos != tusRespuestas.length) {
				imprimeOculta(tusRespuestas);
				// Preguntamos por teclado
				System.out.println("\nIntroduce una letra: ");
				resp = teclado.next().toLowerCase().charAt(0);

				// Recorremos el array y comprobamos si se ha producido un acierto
				for (int i = 0; i < desguazada.length; i++) {
					if (desguazada[i] == resp) {
						tusRespuestas[i] = desguazada[i];
						desguazada[i] = ' ';
						aciertos++;

					}
				}
				System.out.println("Tu letra se encuentra " + StringUtils.countMatches(palElegida, resp) + " veces");
				intentos++;
			}
			// Si hemos acertado todas imprimimos un mensaje
			if (aciertos == tusRespuestas.length) {
				System.out.print("\nFelicidades!! Has acertado la palabra: ");
				System.out.println(copia);
			}
			// Si no otro
			else {
				System.out.print("\nQue torpe! La palabra era: ");
				for (int i = 0; i < copia.length; i++) {
					System.out.print(copia[i] + " ");
				}
			}
			// Reseteamos contadores
			intentos = 0;
			aciertos = 0;
			// Volvemos a preguntarle al usuario si quiere volver a perder el tiempo
			resp = pregunta("\n\nQuieres volver a jugar?", teclado);
		} while (resp != 'n');

	}

	/**
	 * Esto desguaza el String en un array de caracteres
	 * 
	 * @return array de letras.
	 */
	private static char[] desguaza(String palAzar) {
		char[] letras;
		letras = new char[palAzar.length()];
		for (int i = 0; i < palAzar.length(); i++) {
			letras[i] = palAzar.charAt(i);
		}
		return letras;
	}

	/**
	 * Esto imprime la palabra con espacios
	 * 
	 * @param tusRespuestas el array de caracteres
	 */
	private static void imprimeOculta(char[] tusRespuestas) {

		for (int i = 0; i < tusRespuestas.length; i++) {
			System.out.print(tusRespuestas[i] + " ");
		}
	}

	/**
	 * Esto nos pregunta si queremos volver a jugar y comprueba los caracteres
	 * introducidos
	 * 
	 * @param men texto para mostrar al usuario
	 * @return caracter de respuesta (s/n)
	 */
	public static char pregunta(String men, Scanner teclado) {
		char resp;
		System.out.println(men + " (s/n)");
		resp = teclado.next().toLowerCase().charAt(0);
		while (resp != 's' && resp != 'n') {
			System.out.println("Error! solo se admite S o N");
			resp = teclado.next().toLowerCase().charAt(0);
		}
		return resp;
	}
}
