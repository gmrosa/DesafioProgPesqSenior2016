#include <stdio.h>

/**
 * @author Guilherme Murilo da Rosa
 */
int main() {
	int linhas;
	scanf("%d", &linhas);

	while (linhas > 0) {
		int numero;
		scanf("%d", &numero);

		if (numero > 2) {
			// Se é par não é primo
			if (numero % 2 == 0) {
				printf("Not Prime\n");
			} else {
				int j = 3;
				int ehPrimo = 1;
				// Otimização para não percorrer todos os números
				while (j * j <= numero) {
					if (numero % j == 0) {
						ehPrimo = 0;
						printf("Not Prime\n");
						break;
					}
					j += 2;
				}
				if (ehPrimo == 1) {
					printf("Prime\n");
				}
			}
		} else if (numero == 0 || numero == 1) {
			printf("Not Prime\n");
		} else {
			printf("Prime\n");
		}
		linhas--;
	}
	return 0;
}