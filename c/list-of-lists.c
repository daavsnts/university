// Sintese
// objetivo "Gincana. Modalidade Programação. Nível júnior. 2a Fase de 2011.
// site "https://olimpiada.ic.unicamp.br/pratique/pj/2011/f2/gincana/"
// Requisito "1 - Lista de lista"
// autor "Davi Santos Luciano"
// data "24/06/2020"

#include <stdio.h>
#include <stdlib.h>

struct tNo {
	int aluno;
	struct tNo *prox;
	struct tNo *time;
};

void incluir(struct tNo **lst, struct tNo *novo) {
	struct tNo *p = (*lst), *q;
	if ((*lst) == NULL) {
		novo->prox = NULL;
		(*lst) = novo;
	} else {
		while (p->prox != NULL) {
			p = p->prox;
		}
		p->prox = novo;
		novo->prox = NULL;
	}
}

void setarTimes(struct tNo **lst, int iAluno, int jAluno) {
	struct tNo *p = (*lst), *j = (*lst);
	while (p != NULL) {
		if (p->aluno == iAluno) {
			while (j != NULL) {
				if (j->aluno == jAluno) {
					if (p->time == NULL) {
						p->time = j;
					} else {
						while (p->time != NULL) {
							p = p->time;
						}
						p->time = j;
					}
				}
				j = j->prox;
			}
		}
		p = p->prox;
	}
}

int checarTimes(struct tNo *lst, int nAlunos) {
	struct tNo *p = lst, *j;
	int qtdTimes = 0, visitados[nAlunos], i, alunosSolo[nAlunos];

	for (i = 1; i < nAlunos; i++) {
		visitados[i] = i;
		alunosSolo[i] = i;
	}

	while (p != NULL) {
		j = p;
		if (j->time != NULL) {
			j = j->time;
			if (visitados[j->aluno] == j->aluno) {
				visitados[j->aluno] = -1;
			} else {
				qtdTimes++;
			}
		} else {
			alunosSolo[p->aluno] = -1;
		}
		p = p->prox;
	}

	for (i = 1; i < nAlunos; i++) {
		if (alunosSolo[i] == -1) {
			if (visitados[i] != -1) {
				qtdTimes++;
			}	
		}
	}

	return qtdTimes;
}

int main(void) {
	int nAlunos, mLista;
	struct tNo *lista=NULL, *novoAluno;
	int iAluno, jAluno, times=0, i, j;

	printf("Digite o numero de alunos: ");
	scanf("%d", &nAlunos);

	for (i = 1; i <= nAlunos; i++) {
		novoAluno = malloc(sizeof(struct tNo));
		novoAluno->aluno = i;
		novoAluno->time = NULL;
		incluir(&lista, novoAluno);
	}

	printf("Digite o tamanho da lista: ");
	scanf("%d", &mLista);

	for (i = 1; i <= mLista; i++) {
		printf("Digite a dupla numero %d (ex: 1 5): ", i);
		scanf("%d %d", &iAluno, &jAluno);
		
		setarTimes(&lista, iAluno, jAluno);

	}

	times = checarTimes(lista, nAlunos);
	
	printf("%d", times);	
	
	return 0;
}
