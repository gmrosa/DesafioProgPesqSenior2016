import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Guilherme Murilo da Rosa
 */
class EmPerigo {

	public static void main(String[] args) throws IOException {
		try (InputStreamReader ir = new InputStreamReader(System.in); BufferedReader in = new BufferedReader(ir)) {
			String linha = "";

			while (!(linha = in.readLine()).equals("00e0")) {
				System.out.println(resolverLinha(linha));
			}
		}
	}

	/**
	 * Embora a solu��o ficou absurdamente enxuta, o foco mesmo foi na performance do algor�timo. Esse algor�timo foi refatorado sucessivamente at� ter um entendimento melhor da
	 * solu��o, pois o resultado segue um padr�o muito simples, basta resolver em sequencia de (1 a 15) para notar. Todo numero oriundo de uma pot�ncia de 2, ap�s subtrair 1, obt�m
	 * um n�mero cujo seu resultado (�ltimo vivo) � ele mesmo.
	 * 
	 * @param linha
	 * @return resultado
	 */
	public static int resolverLinha(String linha) {
		char[] aLinha = linha.toCharArray();
		int z = aLinha[3] - '0';
		int qtdRebeldes = ((aLinha[0] - '0') * 10) + aLinha[1] - '0';

		qtdRebeldes *= Math.pow(10, z);
		// (rebeldes - pot�ncia de 2 mais pr�xima - 1) * 2 + 1;
		return qtdRebeldes == 0 ? 0 : qtdRebeldes < 3 ? 1 : (qtdRebeldes - ((2 << (30 - Integer.numberOfLeadingZeros(qtdRebeldes))) - 1) - 1) * 2 + 1;
	}

}