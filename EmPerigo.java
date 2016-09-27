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
	 * Embora a solução ficou absurdamente enxuta, o foco mesmo foi na performance do algorítimo. Esse algorítimo foi refatorado sucessivamente até ter um entendimento melhor da
	 * solução, pois o resultado segue um padrão muito simples, basta resolver em sequencia de (1 a 15) para notar. Todo numero oriundo de uma potência de 2, após subtrair 1, obtém
	 * um número cujo seu resultado (último vivo) é ele mesmo.
	 * 
	 * @param linha
	 * @return resultado
	 */
	public static int resolverLinha(String linha) {
		char[] aLinha = linha.toCharArray();
		int z = aLinha[3] - '0';
		int qtdRebeldes = ((aLinha[0] - '0') * 10) + aLinha[1] - '0';

		qtdRebeldes *= Math.pow(10, z);
		// (rebeldes - potência de 2 mais próxima - 1) * 2 + 1;
		return qtdRebeldes == 0 ? 0 : qtdRebeldes < 3 ? 1 : (qtdRebeldes - ((2 << (30 - Integer.numberOfLeadingZeros(qtdRebeldes))) - 1) - 1) * 2 + 1;
	}

}