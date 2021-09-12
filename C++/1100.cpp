#include <iostream>
#include <math.h>
using namespace std;

// https://www.acmicpc.net/problem/1100

int main()
{
	char map[9][9];

	int count;

	count = 0;

	for (int i = 0; i < 8; i++)
	{
		cin >> map[i];

		for (int j = 0; j < 8; j++)
		{
			if (map[i][j] == 'F')
			{
				if ((i + j) % 2 == 0)
				{
					count++;
				}
			}
		}
	}

	cout << count << endl;

	return 0;
}