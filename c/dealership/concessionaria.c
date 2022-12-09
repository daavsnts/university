// Inclusoes de bibliotecas padrao:
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <locale.h>

// Definicoes:
#define MAX_CLIENTES 2
#define MAX_VEICULOS 3
#define NAO_ALTERAR_PRIMEIRO 9999
#define CODIGO_REMOVIDO 9799

#define MENU_CLIENTE 1
#define MENU_COMPRAS 2

#define VERIFICAR_CLIENTE 1
#define CADASTRAR_CLIENTE 2
#define REMOVER_CLIENTE 3
#define ALTERAR_CLIENTE 4
#define SAIR 0

#define COMPRAR_VEICULO 1

// Declaracoes de structs:
typedef struct veiculo {

	char nome[25];
	char marca[10];
	float preco;
	int idVeiculo;

} veiculo;

typedef struct cliente {

	char nomeCompleto[25];
	char dataNascimento[20];
	int rg;
	int cpf;
	int codigo;
	int idVeiculo;
	veiculo *veiculo;

} cliente;

// Declaracoes de funcoes:
char pegarConfirmacao(char opcaoDesejada[]);
void printarUmSoCliente(cliente clientes[], int indiceCliente, veiculo veiculos[]);
void printarMenuPrincipal();
void printarMenuClientes();
void cadastrarOuAlterarCliente(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, int opcao, int indiceEnviado, veiculo veiculos[]);
int pesquisarClientePorCodigo(cliente clientes[], int inicio, int fim, int codigoPesquisado);
int leValidaCodigoCliente();
void verificarCliente(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]);
void removerCliente(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]);
void abrirMenuClientes(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]);
int selecionarOpcaoPrincipal(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]);
void comprarVeiculoPorId(cliente clientes[], veiculo veiculos[], int indiceCliente);
void printarMenuCompras();
void comprarVeiculo(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]);
void printarListaVeiculos(veiculo veiculos[]);

// Funcoes:
void printarMenuPrincipal() {

	printf("- Bem vindo ao Sistema de Concessionaria! -\n\n");
	printf("1. Clientes\n");
	printf("2. Compras\n");
	printf("0. Finalizar\n");
	printf("\n");

}

void printarMenuCouV(char tipo[]) {

	printf("- Menu de Gerenciamento de %s -\n\n", tipo);
	printf("1. Verificar %s\n", tipo);
	printf("2. Cadastrar %s\n", tipo);
	printf("3. Remover %s\n", tipo);
	printf("4. Alterar %s\n", tipo);
	printf("0. Voltar\n");
	printf("\n");

}

void cadastrarOuAlterarCliente(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, int opcao, int indiceEnviado, veiculo veiculos[]) {

	int codigoPesquisa, indiceCliente, i;
	char opcaoEscolhida[10];
	char alteracao = 'N';
	int substituido = 0, removido = 0;

	if (opcao == CADASTRAR_CLIENTE) {

		for (i = 0; i <= *qntClientesReg; i++) {

			if (strcmp(clientes[i].nomeCompleto, "Removido") == 0) {

				indiceCliente = i;
				i = *qntClientesReg;
				substituido = 1;

			} else {

				indiceCliente = *qntClientesReg;

			}

		}

		strcpy(opcaoEscolhida, "Cadastro");

	} else if (opcao == ALTERAR_CLIENTE) {

		if (indiceEnviado == NAO_ALTERAR_PRIMEIRO) {

			codigoPesquisa = leValidaCodigoCliente();
			indiceCliente = pesquisarClientePorCodigo(clientes, 0, *qntClientesReg, codigoPesquisa);

			if (strcmp(clientes[indiceCliente].nomeCompleto, "Removido") == 0) {

				alteracao = 'N';
				removido = 1;

			} else {

				alteracao = 'S';

			}

		} else {

			indiceCliente = indiceEnviado;
			printarUmSoCliente(clientes, indiceCliente, veiculos);
			alteracao = pegarConfirmacao("alterar");

		}

		strcpy(opcaoEscolhida, "Alteracao");

	}

	if (alteracao == 'S' || opcao == CADASTRAR_CLIENTE) {
		
		printf("- %s de cliente -\n\n", opcaoEscolhida);
		printf("Digite o Nome: ");
		fflush(stdin);
		scanf("%[^\n]", clientes[indiceCliente].nomeCompleto);
		system("cls");

		printf("- %s de cliente -\n\n", opcaoEscolhida);
		printf("Digite a Data de Nascimento: ");
		fflush(stdin);
		scanf("%[^\n]", clientes[indiceCliente].dataNascimento);
		system("cls");

		printf("- %s de cliente -\n\n", opcaoEscolhida);
		printf("Digite o RG: ");
		scanf("%d", &clientes[indiceCliente].rg);
		system("cls");

		printf("- %s de cliente -\n\n", opcaoEscolhida);
		printf("Digite o CPF: ");
		scanf("%d", &clientes[indiceCliente].cpf);
		system("cls");

	}

	if (opcao == CADASTRAR_CLIENTE) {

		if (substituido) {

			printf("[SISTEMA]* - O codigo gerado para o cliente %s e: %d\n\n", clientes[indiceCliente].nomeCompleto, clientes[indiceCliente].codigo);
			system("pause");
			(*qntClientesTotal)++;

		} else {

			clientes[indiceCliente].codigo = 100+(*qntClientesReg);
			clientes[indiceCliente].veiculo = NULL;

			printf("[SISTEMA]* - O codigo gerado para o cliente %s e: %d\n\n", clientes[indiceCliente].nomeCompleto, clientes[indiceCliente].codigo);

			(*qntClientesReg)++;
			(*qntClientesTotal)++;
			system("pause");

		}

	} else if (opcao == ALTERAR_CLIENTE && alteracao == 'S') {

		printf("[SISTEMA]* - O Cliente de codigo %d foi alterado!\n\n", clientes[indiceCliente].codigo);
		system("pause");

	} else if (alteracao == 'N' && !removido) {

		printf("[SISTEMA]* - O Cliente de codigo %d nao foi alterado!\n\n", clientes[indiceCliente].codigo);
		system("pause");

	} else if (alteracao == 'N' && removido) {

		printf("[SISTEMA]* - Cliente nao encontrado!\n");
		system("pause");

	}

	system("cls");

}

int pesquisarClientePorCodigo(cliente clientes[], int inicio, int fim, int codigoPesquisado) {

	while (inicio <= fim) {

		int meio = (inicio+fim)/2;

		if (codigoPesquisado == clientes[meio].codigo) {

			return meio;

		} else if (clientes[meio].codigo < codigoPesquisado) {

			return pesquisarClientePorCodigo(clientes, meio+1, fim, codigoPesquisado);

		} else {

			return pesquisarClientePorCodigo(clientes, inicio, meio-1, codigoPesquisado);

		}

	}

	return -1;

}

void printarUmSoCliente(cliente clientes[], int indiceCliente, veiculo veiculos[]) {

	printf("[SISTEMA]* - So ha um cliente registrado!\n\n");
	printf("- Dados do cliente - \n");
	printf("Nome: %s\n", clientes[indiceCliente].nomeCompleto);
	printf("Data de nascimento: %s\n", clientes[indiceCliente].dataNascimento);
	printf("RG: %d\n", clientes[indiceCliente].rg);
	printf("CPF: %d\n", clientes[indiceCliente].cpf);
	printf("Codigo: %d\n", clientes[indiceCliente].codigo);

	if (clientes[indiceCliente].veiculo != NULL) {

		printf("Veiculo: %s %s\n\n", clientes[indiceCliente].veiculo->marca, clientes[indiceCliente].veiculo->nome);

	}

}

char pegarConfirmacao(char opcaoDesejada[]) {

	char confirmacao;

	printf("Deseja %s os dados deste cliente? (S) ou (N): ", opcaoDesejada);
	fflush(stdin);
	confirmacao = getchar();
	system("cls");
	confirmacao = toupper(confirmacao);

	return confirmacao;

}

int leValidaCodigoCliente() {

	int codigoPesquisa;

	// Fazer processo de validação!
	printf("Digite o codigo do cliente: ");
	scanf("%d", &codigoPesquisa);
	system("cls");

	return codigoPesquisa;
}

void verificarCliente(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]) {

	int codigoPesquisa, indiceCliente = 0, fim, i;

	if (*qntClientesTotal == 1) {

		for (i = 0; i <= *qntClientesReg; i++) {

			if (strcmp(clientes[i].nomeCompleto, "Removido") == -1 && strlen(clientes[i].nomeCompleto) > 0) {

				indiceCliente = i;

			}

		}

		printarUmSoCliente(clientes, indiceCliente, veiculos);

	} else if (*qntClientesReg > 1 && *qntClientesTotal > 1) {

		codigoPesquisa = leValidaCodigoCliente();

		indiceCliente = pesquisarClientePorCodigo(clientes, 0, *qntClientesReg, codigoPesquisa);

		if (indiceCliente == -1 || strcmp(clientes[indiceCliente].nomeCompleto, "Removido") == 0) {

			printf("[SISTEMA]* - Cliente nao encontrado!\n");

		} else {

			printf("- Dados do cliente - \n");
			printf("Nome: %s\n", clientes[indiceCliente].nomeCompleto);
			printf("Data de nascimento: %s\n", clientes[indiceCliente].dataNascimento);
			printf("RG: %d\n", clientes[indiceCliente].rg);
			printf("CPF: %d\n", clientes[indiceCliente].cpf);
			printf("Codigo: %d\n\n", clientes[indiceCliente].codigo);

		}

	} else {

		printf("[SISTEMA]* - Nao ha clientes cadastrados!\n\n");

	}

}

void removerCliente(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]) {

	int codigoPesquisa, indiceCliente = 0, fim, i;
	char remocao;

	if (*qntClientesTotal == 1) {

		for (i = 0; i <= *qntClientesReg; i++) {

			if (strcmp(clientes[i].nomeCompleto, "Removido") == -1 && strlen(clientes[i].nomeCompleto) > 5) {

				indiceCliente = i;

			}

		}

		printarUmSoCliente(clientes, indiceCliente, veiculos);
		remocao = pegarConfirmacao("remover");

		if (remocao == 'S') {

			strcpy(clientes[indiceCliente].nomeCompleto, "Removido");
			strcpy(clientes[indiceCliente].dataNascimento, " ");
			clientes[indiceCliente].rg = 0;
			clientes[indiceCliente].cpf = 0;
			clientes[indiceCliente].veiculo = NULL;
			(*qntClientesTotal)--;

			printf("[SISTEMA]* - Cliente removido com sucesso!\n\n");

		}

	} else if (*qntClientesReg > 1 && *qntClientesTotal > 1) {

		codigoPesquisa = leValidaCodigoCliente();
		indiceCliente = pesquisarClientePorCodigo(clientes, 0, *qntClientesReg, codigoPesquisa);

		if (indiceCliente == -1 || strcmp(clientes[indiceCliente].nomeCompleto, "Removido") == 0) {

			printf("[SISTEMA]* - Cliente nao encontrado!\n");

		} else {

			strcpy(clientes[indiceCliente].nomeCompleto, "Removido");
			strcpy(clientes[indiceCliente].dataNascimento, " ");
			clientes[indiceCliente].rg = 0;
			clientes[indiceCliente].cpf = 0;
			clientes[indiceCliente].veiculo = NULL;
			(*qntClientesTotal)--;

			printf("[SISTEMA]* - Cliente removido com sucesso!\n\n");

		}

	} else {

		printf("[SISTEMA]* - Nao ha clientes cadastrados!\n\n");

	}

}

void abrirMenuClientes(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]) {

	int opcao = 99, indiceCliente = 0, i;

	while (opcao != 0) {

		printarMenuCouV("Clientes");
		printf("Digite a opcao desejada: ");
		scanf("%d", &opcao);
		system("cls");

		switch(opcao) {

			case VERIFICAR_CLIENTE:

				if (*qntClientesReg > 0 && *qntClientesTotal > 0) {

					verificarCliente(clientes, qntClientesReg, qntClientesTotal, veiculos);

				} else {

					printf("[SISTEMA]* - Nao ha clientes cadastrados!\n\n");

				}

				break;

			case CADASTRAR_CLIENTE:

				if (*qntClientesTotal <= MAX_CLIENTES) {

					cadastrarOuAlterarCliente(clientes, qntClientesReg, qntClientesTotal, CADASTRAR_CLIENTE, NAO_ALTERAR_PRIMEIRO, veiculos);

				} else {

					printf("[SISTEMA]* - Numero maximo de clientes cadastrados!\n\n");

				}

				break;

			case REMOVER_CLIENTE:

				if (*qntClientesReg > 0 && *qntClientesTotal > 0) {

					removerCliente(clientes, qntClientesReg, qntClientesTotal, veiculos);

				} else {

					printf("[SISTEMA]* - Nao ha clientes cadastrados!\n\n");

				}

				break;

			case ALTERAR_CLIENTE:

				if (*qntClientesReg > 1 && *qntClientesTotal > 1) {

					cadastrarOuAlterarCliente(clientes, qntClientesReg, qntClientesTotal, ALTERAR_CLIENTE, NAO_ALTERAR_PRIMEIRO, veiculos);

				} else if (*qntClientesTotal == 1) {

					for (i = 0; i <= *qntClientesReg; i++) {

						if (strcmp(clientes[i].nomeCompleto, "Removido") == -1 && strlen(clientes[i].nomeCompleto) > 5) {

							indiceCliente = i;

						}

					}

					cadastrarOuAlterarCliente(clientes, qntClientesReg, qntClientesTotal, ALTERAR_CLIENTE, indiceCliente, veiculos);

				} else {

					printf("[SISTEMA]* - Nao ha clientes cadastrados!\n\n");

				}

				break;

			case SAIR:

				break;

		}

	}

}

void printarListaVeiculos(veiculo veiculos[]) {

	int i;

	for (i = 0; i <= 2; i++) {

		printf("-----------------\n");
		printf("Veiculo: %s\n", veiculos[i].nome);
		printf("Marca: %s\n", veiculos[i].marca);
		printf("Valor: %0.2f\n", veiculos[i].preco);
		printf("Id: %d\n", veiculos[i].idVeiculo);
		printf("-----------------\n");

	}

}

void comprarVeiculoPorId(cliente clientes[], veiculo veiculos[], int indiceCliente) {

	int idVeiculo, i;

	printf("Digite o id do veiculo desejado: ");
	scanf("%d", &clientes[indiceCliente].idVeiculo);
	system("cls");

	for (i = 0; i <= 3; i++) {

		if (clientes[indiceCliente].idVeiculo == veiculos[i].idVeiculo) {

			clientes[indiceCliente].veiculo = malloc(sizeof(veiculo));
			clientes[indiceCliente].veiculo = &veiculos[i];

		}

	}

}

void comprarVeiculo(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]) {

	int codigoPesquisa, indiceCliente = 0, fim, i;
	char confirmacaoCompra;

	if (*qntClientesTotal == 1) {

		for (i = 0; i <= *qntClientesReg; i++) {

			if (strcmp(clientes[i].nomeCompleto, "Removido") == -1 && strlen(clientes[i].nomeCompleto) > 5) {

				indiceCliente = i;

			}

		}

		printarUmSoCliente(clientes, indiceCliente, veiculos);
		printf("\n");
		printf("Deseja comprar um veiculo no nome deste cliente? (S) ou (N): ");
		fflush(stdin);
		confirmacaoCompra = getchar();
		system("cls");
		confirmacaoCompra = toupper(confirmacaoCompra);

		if (confirmacaoCompra == 'S') {

			printarListaVeiculos(veiculos);
			comprarVeiculoPorId(clientes, veiculos, indiceCliente);

			printf("[SISTEMA]* - Veiculo comprado com sucesso!\n\n");

		}

	} else if (*qntClientesReg > 1 && *qntClientesTotal > 1) {

		codigoPesquisa = leValidaCodigoCliente();
		indiceCliente = pesquisarClientePorCodigo(clientes, 0, *qntClientesReg, codigoPesquisa);

		if (indiceCliente == -1 || strcmp(clientes[indiceCliente].nomeCompleto, "Removido") == 0) {

			printf("[SISTEMA]* - Cliente nao encontrado!\n");

		} else {

			printarListaVeiculos(veiculos);
			comprarVeiculoPorId(clientes, veiculos, indiceCliente);

			printf("[SISTEMA]* - Veiculo comprado com sucesso!\n\n");

		}

	} else {

		printf("[SISTEMA]* - Nao ha clientes cadastrados!\n\n");

	}

}

void printarMenuCompras() {

	printf("- Menu de Compra de Veiculos -\n\n");
	printf("1. Comprar Veiculo\n");
	printf("0. Voltar\n");
	printf("\n");

}

void abrirMenuCompras(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]) {

	int opcao = 99, indiceCliente = 0, i;

	while (opcao != 0) {

		printarMenuCompras();
		printf("Digite a opcao desejada: ");
		scanf("%d", &opcao);
		system("cls");

		switch(opcao) {

			case COMPRAR_VEICULO:

				comprarVeiculo(clientes, qntClientesReg, qntClientesTotal, veiculos);
				break;

			case SAIR:

				break;

		}

	}

}

int selecionarOpcaoPrincipal(cliente clientes[], int *qntClientesReg, int *qntClientesTotal, veiculo veiculos[]) {

	int opcao = 99;

	while(opcao != 0) {

		printarMenuPrincipal();
		printf("Digite a opcao desejada: ");
		scanf("%d", &opcao);
		system("cls");

		switch(opcao) {

			case MENU_CLIENTE:

				abrirMenuClientes(clientes, qntClientesReg, qntClientesTotal, veiculos);
				break;

			case MENU_COMPRAS:

				abrirMenuCompras(clientes, qntClientesReg, qntClientesTotal, veiculos);
				break;

			case 0:

				return 0;

		}

	}

}

int main() {

	cliente clientes[MAX_CLIENTES];
	veiculo veiculos[MAX_VEICULOS];
	int opcao = 99, qntClientesReg = 0, qntClientesTotal = 0, qtdLeitura, qtdEscrita, i, qtd;

	FILE *arqVeiculos;
	arqVeiculos = fopen("veiculos.bin", "rb");

	if (arqVeiculos == NULL) {

		printf("Nao foi possivel ler o arquivo!..\n");
		exit(1);

	}

	qtd = fread(&veiculos[0], sizeof(struct veiculo), 3, arqVeiculos);

	while(opcao != 0) {

		opcao = selecionarOpcaoPrincipal(clientes, &qntClientesReg, &qntClientesTotal, veiculos);

	}

	printf("[SISTEMA]* - Programa finalizado com sucesso!\n");

	return 0;

}
