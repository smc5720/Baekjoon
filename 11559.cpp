#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/11559

char field[12][6];
int cnt, combo;
bool isCombo;

void printField()
{
	for (int i = 0; i < 12; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			cout << field[i][j];
		}

		cout << "\n";
	}

	cout << "\n";
}

void boomField()
{
	for (int i = 0; i < 12; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			if (field[i][j] == 'C')
			{
				field[i][j] = '.';
			}
		}
	}
}

void sortField()
{
	bool isChanged;
	isChanged = false;

	for (int i = 0; i < 6; i++)
	{
		for (int j = 10; j >= 0; j--)
		{
			if (field[j][i] != '.' && field[j + 1][i] == '.')
			{
				char tmp;
				tmp = field[j][i];
				field[j][i] = '.';
				field[j + 1][i] = tmp;

				isChanged = true;
			}
		}
	}

	if (isChanged)
	{
		sortField();
	}
}

void checkCnt(char c)
{
	if (cnt < 4)
	{
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				if (field[i][j] == 'K')
				{
					field[i][j] = c;
				}
			}
		}
	}

	else
	{
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				if (field[i][j] == 'K')
				{
					field[i][j] = 'C';
				}
			}
		}

		isCombo = true;
	}
}

void DFS(int row, int col, char c)
{
	if (field[row][col] == '.' || field[row][col] == 'K' || field[row][col] == 'C')
	{
		return;
	}

	else
	{
		field[row][col] = 'K';

		// 왼쪽
		if (col - 1 >= 0 && c == field[row][col - 1])
		{
			cnt += 1;
			DFS(row, col - 1, c);
		}

		// 오른쪽
		if (col + 1 < 6 && c == field[row][col + 1])
		{
			cnt += 1;
			DFS(row, col + 1, c);
		}

		// 위
		if (row - 1 >= 0 && c == field[row - 1][col])
		{
			cnt += 1;
			DFS(row - 1, col, c);
		}

		// 아래
		if (row + 1 < 12 && c == field[row + 1][col])
		{
			cnt += 1;
			DFS(row + 1, col, c);
		}
	}
}

void searchField()
{
	isCombo = false;

	for (int i = 0; i < 12; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			char c;
			c = field[i][j];
			cnt = 1;
			DFS(i, j, c);
			checkCnt(c);
		}
	}

	if (isCombo)
	{
		combo += 1;
		isCombo = false;

		boomField();
		sortField();
		searchField();
	}
}

int main()
{
	combo = 0;

	for (int i = 0; i < 12; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			cin >> field[i][j];
		}
	}

	searchField();

	cout << combo << "\n";

	return 0;
}