#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/1913

int N;
int number;

int numArray[1000][1000];

int main()
{
	cin >> N >> number;

	int idxX, idxY;
	int startX, startY;
	int resultX, resultY;

	resultX = 0;
	resultY = 0;

	idxX = N / 2 + 1;
	idxY = N / 2 + 1;

	startX = idxX;
	startY = idxY;

	int rnd;
	rnd = N / 2;

	int num;
	num = 1;

	numArray[idxY][idxX] = num;
	num += 1;

	for (int i = 1; i <= rnd; i++)
	{
		idxY -= 1;

		while (idxX <= startX + i)
		{
			if (num == number)
			{
				resultX = idxX;
				resultY = idxY;
			}

			numArray[idxY][idxX] = num;
			num += 1;
			idxX += 1;
		}

		idxX -= 1;
		idxY += 1;

		while (idxY <= startY + i)
		{
			if (num == number)
			{
				resultX = idxX;
				resultY = idxY;
			}

			numArray[idxY][idxX] = num;
			num += 1;
			idxY += 1;
		}

		idxY -= 1;
		idxX -= 1;

		while (idxX >= startX - i)
		{
			if (num == number)
			{
				resultX = idxX;
				resultY = idxY;
			}

			numArray[idxY][idxX] = num;
			num += 1;
			idxX -= 1;
		}

		idxX += 1;
		idxY -= 1;

		while (idxY >= startY - i)
		{
			if (num == number)
			{
				resultX = idxX;
				resultY = idxY;
			}

			numArray[idxY][idxX] = num;
			num += 1;
			idxY -= 1;
		}

		idxY += 1;
	}

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cout << numArray[i][j] << " ";
		}

		cout << "\n";
	}

	cout << resultY << " " << resultX << "\n";

	return 0;
}