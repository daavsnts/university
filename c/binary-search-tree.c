//	Synthesis
//		Objective: "Binary Search Tree with AVL Balancing"
//		Author: "Davi Santos Luciano - UC19106165 - Eng. Software"
//		Date: "27/05/2020"

#include <stdio.h>
#include <stdlib.h>

// Types declarations:
struct tKnot {
	struct tKnot *left;
	int data;
	struct tKnot *right;
};

// Functions prototypes:
int getOptionFromMenu(void);
void initializeTree(struct tKnot**);
void includeKnot(struct tKnot**, struct tKnot*);
int checkHeight(struct tKnot*);
int checkBalancingFactor(struct tKnot*);
void inOrderPrinter(struct tKnot*);
void printWithRepresentation(struct tKnot*, int depth);
void leftSimpleRotation(struct tKnot **);
void rightSimpleRotation(struct tKnot **);
void AVLBalancing(struct tKnot **);
struct tKnot *consultKnot(struct tKnot*, int);
void treeDestroyer(struct tKnot*);
int checkDegree(struct tKnot*);
struct tKnot *findSmallerKnot(struct tKnot*);
int deleteKnot(struct tKnot**, int);

// Main function:
int main(void) {
	int option, newOption, value, result;
	struct tKnot *p, *tree;

	initializeTree(&tree);
	do {
		option = getOptionFromMenu();
		switch (option) {
			case 1:
				printf("\n\n-- Include --\n\n");
				printf("Insert the knot value: ");
				scanf("%d", &value);
				p = malloc(sizeof(struct tKnot));
				p->data = value;
				includeKnot(&tree, p);
				break;
			case 2:
				printf("\n\n-- List --\n\n");
				(tree == NULL) ? printf("The list is empty!\n") : inOrderPrinter(tree);
				break;
			case 3:
				printf("\n\n-- Consult --\n\n");
				printf("Insert the knot value: ");
				scanf("%d", &value);
				if (consultKnot(tree, value) != NULL)
					printf("Founded!\n");
				else
					printf("Not founded!\n");
				break;
			case 4:
				printf("\n\n-- Delete --\n\n");
				printf("Insert the knot value: ");
				scanf("%d", &value);
				if (deleteKnot(&tree, value))
					printf("Deleted!\n");
				else
					printf("Not founded!\n");
				break;

			case 5:
				do {
					if (tree != NULL) {
						newOption = getOptionFromNewMenu();
						switch(newOption) {
							case 1:
								printf("\n\n-- Check if the first are the father of the second --\n\n");
								result = goToOption(tree, 1);
								if (result == 1) {
									printf("Yes, the first are the father of the second!\n");
								} else {
									if (result == -1) {
										printf("Error!\n");
									} else {
										printf("No, the first aren't the father of the second!\n");
									}
								}
								break;

							case 2:
								printf("\n\n-- Check if the first are the children of the second --\n\n");
								result = goToOption(tree, 2);
								if (result == 1) {
									printf("Yes, the first are the children of the second!\n");
								} else {
									if (result == -1) {
										printf("Error!\n");
									} else {
										printf("No, the first aren't the children of the second!\n");
									}
								}
								break;

							case 3:
								printf("\n\n-- Check if the first are the Grandchildren of the second --\n\n");
								result = goToOption(tree, 3);
								if (result == 1) {
									printf("Yes, the first are the Grandchildren of the second!\n");
								} else {
									if (result == -1) {
										printf("Error!\n");
									} else {
										printf("No, the first aren't the Grandchildren of the second!\n");
									}
								}
								break;

							/*case 4:
								printf("\n\n-- Check knot nivel --\n\n");
								level = goToOption(tree, 4);
								printf("The level of this Knot is: %d!\n", level);
								break;*/
						}
					} else {
						printf("The list is empty!\n");
						newOption = 0;
					}
				} while (newOption != 0);
				break;
		}
	} while (option != 0);
	treeDestroyer(tree);
	return 0;
}

int goToOption(struct tKnot *root, int option) {
	int firstValue, secondValue, result;
	struct tKnot *firstKnot, *secondKnot;

	printf("Insert the knot value: ");
	scanf("%d", &firstValue);
	firstKnot = consultKnot(root, firstValue);
	if (option != 4 && option != 6 && option != 7 && option != 8) {
		printf("Insert the second knot value: ");
		scanf("%d", &secondValue);
		system("cls");
		secondKnot = consultKnot(root, secondValue);
	}

	if (firstKnot == NULL || secondKnot == NULL) {
		option = 88;
		result = -1;
		printf("One of the Knots is empty!\n");
		return result;
	}
	
	switch(option) {
		case 1:
			result = checkIfIAmTheFather(root, firstKnot, secondKnot);
			break;

		case 2:
			result = checkIfIAmTheChildren(root, firstKnot, secondKnot);
			break;

		case 3:
			result = checkIfIAmTheGrandchildren(root, firstKnot, secondKnot);
			break;
		/*case 4:
			result = checkKnotLevel(root, firstKnot);
			break;*/
	}
	
	return result;
}

// Check knot level function
/*int checkKnotLevel(struct tKnot *root, struct tKnot *knot) {
	return knot->level;
}*/

// Check if the first are the father of the second function
int checkIfIAmTheFather(struct tKnot *root, struct tKnot *firstKnot, struct tKnot *secondKnot) {
	if (firstKnot->left == secondKnot || firstKnot->right == secondKnot) {
		return 1;
	} else {
		return 0;
	}
}

// Check if the first are the children of the second function
int checkIfIAmTheChildren(struct tKnot *root, struct tKnot *firstKnot, struct tKnot *secondKnot) {
	if (secondKnot->left == firstKnot || secondKnot->right == firstKnot) {
		return 1;
	} else {
		return 0;
	}
}

// Check if the first are the grandchildren of the second function
int checkIfIAmTheGrandchildren(struct tKnot *root, struct tKnot *firstKnot, struct tKnot *secondKnot) {
	if (secondKnot->left != NULL) {
		if (secondKnot->left->left == firstKnot || secondKnot->left->right == firstKnot) {
			return 1;
		}
	}

	if (secondKnot->right != NULL) {
		if (secondKnot->right->left == firstKnot || secondKnot->right->right == firstKnot) {
			return 1;
		}
	}

	return 0;
}

// Get option from menu function:
int getOptionFromMenu(void) {
	int option;
	printf("\n\n-- Menu Options --\n\n");
	printf("1. Include\n");
	printf("2. List\n");
	printf("3. Consult\n");
	printf("4. Delete\n");
	printf("5. New Options!\n");
	printf("0. Exit\n\n");
	printf("Insert your option: ");
	scanf("%d", &option);
	system("cls");
	return option;
}

// Get option from new menu function:
int getOptionFromNewMenu(void) {
	int option;
	printf("\n\n-- New Menu Options --\n\n");
	printf("1. Check if the first are the father of the second\n");
	printf("2. Check if the first are the son of the second\n");
	printf("3. Check if the first are grandchildren of the second\n");
	printf("4. Check Knot Level\n");
	printf("5. Check the depth of the second Knot based on the first Knot!\n");
	printf("6. Check Knot Degree\n");
	printf("7. Check if the Knot is a Leaf\n");
	printf("0. Back\n\n");
	printf("Insert your option: ");
	scanf("%d", &option);
	system("cls");
	return option;
}

// Initialize tree function:
void initializeTree(struct tKnot **root) {
	(*root) = NULL;
}

// Include knot function:
void includeKnot(struct tKnot **root, struct tKnot *newKnot) {
	if ((*root) == NULL) {
		(*root) = newKnot;
		newKnot->left = newKnot->right = NULL;
	} else {
		if (newKnot->data < (*root)->data) {
			includeKnot(&((*root)->left), newKnot);
		} else {
			includeKnot(&((*root)->right), newKnot);
		}
	}
	AVLBalancing(root);
}

// Check Height function:
int checkHeight(struct tKnot *tree) {
	if(tree == NULL)
		return -1;

	int leftHeight, rightHeight;

	leftHeight = checkHeight(tree->left);
	rightHeight = checkHeight(tree->right);

	if (leftHeight > rightHeight)
		return leftHeight + 1;
	return rightHeight + 1;
}

// Check balancing factor:
int checkBalancingFactor(struct tKnot* tree) {
	if (tree == NULL)
		return 0;
	return checkHeight(tree->right) - checkHeight(tree->left);
}

// In order printer function:
void inOrderPrinter(struct tKnot *root) {
//	if (root == NULL)
//		return;
//	inOrderPrinter(root->left);
//	printf("%d - ", root->data);
//	inOrderPrinter(root->right);
	printWithRepresentation(root, 0);
}

// Print with representation function:
void printWithRepresentation(struct tKnot* tree, int depth) {
	if (tree == NULL)
		return;

	int newDepth = depth+1;

	while (depth--) printf("  ");

	printf(" %d: [%d|%d]\n", tree->data, checkDegree(tree), checkBalancingFactor(tree));
	printWithRepresentation(tree->left, newDepth);
	printWithRepresentation(tree->right, newDepth);
}

// Simple left rotation function:
void leftSimpleRotation(struct tKnot **father) {
	struct tKnot *children = (*father)->right;
	(*father)->right = children->left;
	children->left = (*father);
	(*father) = children;
}

// Right left rotation function:
void rightSimpleRotation(struct tKnot **father) {
	struct tKnot *children = (*father)->left;
	(*father)->left = children->right;
	children->right = (*father);
	(*father) = children;
}

// AVL Balancing function:
void AVLBalancing(struct tKnot **root) {
	int fat = checkBalancingFactor(*root);

	if(fat != 2 && fat !=-2)
		return;

	if (fat == 2 && checkBalancingFactor((*root)->right) == 1) {
		leftSimpleRotation(root);
	} else if (fat == -2 && checkBalancingFactor((*root)->left) == -1) {
		rightSimpleRotation(root);
	} else if (fat == 2 && checkBalancingFactor((*root)->right) == -1) {
		rightSimpleRotation(&((*root)->right));
		leftSimpleRotation(root);
	} else {
		leftSimpleRotation(&((*root)->left));
		rightSimpleRotation(root);
	}
}

// Consult knot function:
struct tKnot *consultKnot(struct tKnot *root, int value) {
	if ((root == NULL) || (root->data == value))
		return root;
	if (value < root->data)
		return consultKnot(root->left, value);
	return consultKnot(root->right, value);
}

// Tree destroyer function:
void treeDestroyer(struct tKnot *root) {
	if (root == NULL)
		return;
	treeDestroyer(root->left);
	treeDestroyer(root->right);
	free(root);
}

// Check degree function:
int checkDegree(struct tKnot *root) {
	if ((root->left == NULL) && (root->right == NULL))
		return 0;
	if ((root->right != NULL) && (root->right != NULL))
		return 2;
	return 1;
}

// Find smaller function:
struct tKnot *findSmallerKnot(struct tKnot *root) {
	if ((root == NULL) || (root->left == NULL))
		return root;
	findSmallerKnot(root->left);
}

// Delete knot function:
int deleteKnot(struct tKnot **root, int value) {
	struct tKnot *p;
	int result;
	if (*root == NULL)
		return 0;
	if ((*root)->data == value) {
		if (checkDegree(*root) == 0) { // Leaf
			free(*root);
			*root = NULL;
		} else {
			if (checkDegree(*root) == 1) {
				p = *root;
				if ((*root)->left != NULL)
					*root = (*root)->left;
				else
					*root = (*root)->right;
				free(p);
			} else { // 2 children's
				p = findSmallerKnot((*root)->right);
				(*root)->data = p->data;
				deleteKnot(&((*root)->right), p->data);
			}
		}
		return 1;
	}
	if ((*root)->left != NULL && value <= (*root)->left->data) {
		result = deleteKnot(&((*root)->left), value);
	} else {
		result = deleteKnot(&((*root)->right), value);
	}

	AVLBalancing(root);

	return result;
}
