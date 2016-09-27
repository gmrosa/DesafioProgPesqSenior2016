import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Isso aqui ficou uma bagunça, embora a performance ​ficou aceitável, se eu tivesse utilizado notação polonesa teria ficado mais legível e mais simples de depurar.
 * 
 * @author Guilherme Murilo da Rosa
 */
public class Amigos {

	private static final String CONJUNTO_VAZIO = "{}";
	private static List<Set<String>> conjuntos;
	private static List<String> simbolos;

	public static void main(String[] args) throws IOException {
		String linha = "";
		try (InputStreamReader ir = new InputStreamReader(System.in); BufferedReader in = new BufferedReader(ir)) {
			while ((linha = in.readLine()) != null && !linha.isEmpty()) {
				System.out.println(resolverLinha(linha));
			}
		}
	}

	public static String resolverLinha(String linha) {
		linha = linha.replace("{}", "{1}");
		conjuntos = getConjuntos(linha);

		if (!conjuntos.isEmpty()) {
			if (conjuntos.size() == 1) {
				Set<String> conjunto = conjuntos.get(0);
				if (isConjuntoVazio(conjunto)) {
					return CONJUNTO_VAZIO;
				}
				return getResultado();
			} else {
				simbolos = getSimbolos(linha);

				while (conjuntos.size() > 1) {
					conjuntos = executaOperacao(simbolos, conjuntos);
				}
				return getResultado();
			}
		} else {
			return CONJUNTO_VAZIO;
		}
	}

	private static List<Set<String>> getConjuntos(String linha) {
		List<Set<String>> conjuntos = new ArrayList<>();
		String[] arrayPartes = linha.split("\\+|\\*|\\-|\\(|\\)|\\{|\\}");
		for (String p : arrayPartes) {
			if (!p.isEmpty()) {
				Set<String> conjunto = new TreeSet<>();
				for (char c : p.toCharArray()) {
					conjunto.add(String.valueOf(c));
				}
				conjuntos.add(conjunto);
			}
		}
		return conjuntos;
	}

	private static List<String> getSimbolos(String linha) {
		List<String> simbolos = new ArrayList<>();
		for (String s : linha.split("\\{(\\w)*\\}")) {
			if (!s.isEmpty()) {
				for (char c : s.toCharArray()) {
					simbolos.add(String.valueOf(c));
				}
			}
		}
		int abreParenteses = 0;
		int fechaParenteses = 0;
		for (int i = 0; i + 1 < simbolos.size(); i++) {
			String s1 = simbolos.get(i);
			String s2 = simbolos.get(i + 1);
			if (i > 0) {
				if (s1.equals("(")) {
					++abreParenteses;
				}
			}
			if (s2.equals(")")) {
				++fechaParenteses;
			}
			if (s1.equals("(") && s2.equals(")")) {
				simbolos.remove(i);
				simbolos.remove(i);
			}
		}
		if (abreParenteses == fechaParenteses && simbolos.get(0).equals("(") && simbolos.get(simbolos.size() - 1).equals(")")) {
			simbolos.remove(0);
			simbolos.remove(simbolos.size() - 1);
		}
		return simbolos;
	}

	private static String getResultado() {
		String res = "";
		if (!conjuntos.isEmpty()) {
			for (String s : conjuntos.get(0)) {
				res += s;
			}
		}
		return '{' + res + '}';
	}

	private static List<Set<String>> executaOperacao(List<String> simbolos, List<Set<String>> conjuntos) {
		int indexSimbolo = getIndiceProximoSimbolo(simbolos);
		char simbolo = simbolos.get(indexSimbolo).toCharArray()[0];
		ParDeConjuntos parDeConjuntos = null;

		while (simbolo == '(') {
			List<Set<String>> conjuntosAux = new ArrayList<>();
			int indexFechaParenteses = getIndiceFechaParenteses(simbolos);

			List<String> simbolosAux = new ArrayList<>();

			boolean naoRemoveuParenteses = true;
			for (int i = indexSimbolo; i < indexFechaParenteses; i++) {
				if (naoRemoveuParenteses) {
					simbolos.remove(indexSimbolo);
					naoRemoveuParenteses = false;
				}
				if (indexSimbolo + 1 < indexFechaParenteses) {
					simbolosAux.add(simbolos.get(indexSimbolo));

				}
				simbolos.remove(indexSimbolo);
				--indexFechaParenteses;

				if (quantidadeOperacoes(simbolos) < conjuntos.size()) {
					conjuntosAux.add(conjuntos.get(i));
					conjuntos.remove(i);
				}
				--i;
			}
			while (conjuntosAux.size() > 1) {
				conjuntosAux = executaOperacao(simbolosAux, conjuntosAux);
			}
			if (!conjuntosAux.isEmpty()) {
				conjuntos = adicionarNaPosicao(conjuntos, indexSimbolo, conjuntosAux.get(0));
			}
			indexSimbolo = getIndiceProximoSimbolo(simbolos);
			if (simbolos.isEmpty()) {
				return conjuntos;
			}
			simbolo = simbolos.get(indexSimbolo).toCharArray()[0];
		}

		simbolo = simbolos.remove(indexSimbolo).toCharArray()[0];
		if (conjuntos.size() >= 2) {
			parDeConjuntos = new ParDeConjuntos(conjuntos.remove(indexSimbolo), conjuntos.remove(indexSimbolo), simbolo);
			Set<String> resultado = tratarOperacao(parDeConjuntos);
			if (!resultado.isEmpty()) {
				return adicionarNaPosicao(conjuntos, indexSimbolo, resultado);
			}
			return conjuntos;
		}
		return conjuntos;
	}

	private static List<Set<String>> adicionarNaPosicao(List<Set<String>> conjuntos, int posicao, Set<String> conjunto) {
		List<Set<String>> conjuntosAux = new ArrayList<>();
		if (!conjunto.isEmpty()) {
			boolean adicionou = false;
			for (int i = 0; i < conjuntos.size(); i++) {
				if (i == posicao) {
					conjuntosAux.add(conjunto);
					adicionou = true;
				}
				conjuntosAux.add(conjuntos.get(i));
			}
			if (!adicionou) {
				conjuntosAux.add(conjunto);
			}
		}
		return conjuntosAux;
	}

	private static int quantidadeOperacoes(List<String> simbolos) {
		int i = 0;
		for (String s : simbolos) {
			if (s.equals("+") || s.equals("-") || s.equals("*")) {
				++i;
			}
		}
		return i;
	}

	private static int getIndiceFechaParenteses(List<String> simbolos) {
		int indice = 0;
		int fechaParenteses = 0;
		int abreParenteses = 0;
		for (int i = 0; i < simbolos.size(); i++) {
			if (simbolos.get(i).equals("(")) {
				++abreParenteses;
			}
			if (simbolos.get(i).equals(")")) {
				indice = i;
				++fechaParenteses;
				if (abreParenteses == fechaParenteses) {
					return i;
				}
			}
		}
		return indice;
	}

	private static int getIndiceProximoSimbolo(List<String> simbolos) {
		if (simbolos.size() >= 2) {
			String s = simbolos.get(0);
			if ((s.equals("+") || s.equals("-"))) {
				s = simbolos.get(1);
				if ((s.equals("+") || s.equals("-"))) {
					return 0;
				}
			}
		}
		for (int i = 0; i < simbolos.size(); i++) {
			String s = simbolos.get(i);
			if (s.equals("(")) {
				return i;
			}
		}
		for (int i = 0; i < simbolos.size(); i++) {
			String s = simbolos.get(i);
			if (s.equals("*")) {
				return i;
			}
		}
		return 0;
	}

	private static Set<String> tratarOperacao(ParDeConjuntos parDeConjuntos) {
		Set<String> conjunto = new TreeSet<>();
		switch (parDeConjuntos.simbolo) {
		case '+':
			if (!isConjuntoVazio(parDeConjuntos.op1)) {
				conjunto.addAll(parDeConjuntos.op1);
			}
			if (!isConjuntoVazio(parDeConjuntos.op2)) {
				conjunto.addAll(parDeConjuntos.op2);
			}
			break;
		case '-':
			if (!isConjuntoVazio(parDeConjuntos.op1)) {
				conjunto.addAll(parDeConjuntos.op1);
			}
			if (!isConjuntoVazio(parDeConjuntos.op2)) {
				conjunto.removeAll(parDeConjuntos.op2);
			}
			break;
		case '*':
			if (!isConjuntoVazio(parDeConjuntos.op1) && !isConjuntoVazio(parDeConjuntos.op2)) {
				for (String s : parDeConjuntos.op1) {
					if (parDeConjuntos.op2.contains(s)) {
						conjunto.add(s);
					}
				}
			}
			break;
		default:
			break;
		}
		return conjunto;
	}

	private static boolean isConjuntoVazio(Set<String> conjunto) {
		if (conjunto.size() == 1) {
			if (conjunto.toArray()[0].equals("1")) {
				return true;
			}
		}
		return false;
	}

	private static class ParDeConjuntos {
		private Set<String> op1;
		private Set<String> op2;
		private char simbolo;

		public ParDeConjuntos(Set<String> op1, Set<String> op2, char simbolo) {
			this.op1 = op1;
			this.op2 = op2;
			this.simbolo = simbolo;
		}

	}

}
