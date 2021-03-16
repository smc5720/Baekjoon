#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>

using namespace std;

// https://www.acmicpc.net/problem/1063

// -1: 장외
//  0: 아무 것도 없음
//  1: 왕
//  2: 돌 
int board[10][10];
int N;

pair<int, int> kingPos;
pair<int, int> stonePos;

void moveKing(string c)
{
	// →
	if (c == "R")
	{
		if (board[kingPos.second][kingPos.first + 1] == 0)
		{
			board[kingPos.second][kingPos.first + 1] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.first += 1;
		}

		else if (board[kingPos.second][kingPos.first + 1] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second][stonePos.first + 1] == 0)
			{
				board[kingPos.second][kingPos.first + 1] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second][stonePos.first + 1] = 2;
				kingPos.first += 1;
				stonePos.first += 1;
			}

			else
			{
				return;
			}
		}
	}

	// ←
	else if (c == "L")
	{
		if (board[kingPos.second][kingPos.first - 1] == 0)
		{
			board[kingPos.second][kingPos.first - 1] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.first -= 1;
		}

		else if (board[kingPos.second][kingPos.first - 1] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second][stonePos.first - 1] == 0)
			{
				board[kingPos.second][kingPos.first - 1] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second][stonePos.first - 1] = 2;
				kingPos.first -= 1;
				stonePos.first -= 1;
			}

			else
			{
				return;
			}
		}
	}

	// ↓
	else if (c == "B")
	{
		if (board[kingPos.second + 1][kingPos.first] == 0)
		{
			board[kingPos.second + 1][kingPos.first] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.second += 1;
		}

		else if (board[kingPos.second + 1][kingPos.first] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second + 1][stonePos.first] == 0)
			{
				board[kingPos.second + 1][kingPos.first] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second + 1][stonePos.first] = 2;
				kingPos.second += 1;
				stonePos.second += 1;
			}

			else
			{
				return;
			}
		}
	}

	// ↑
	else if (c == "T")
	{
		if (board[kingPos.second - 1][kingPos.first] == 0)
		{
			board[kingPos.second - 1][kingPos.first] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.second -= 1;
		}

		else if (board[kingPos.second - 1][kingPos.first] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second - 1][stonePos.first] == 0)
			{
				board[kingPos.second - 1][kingPos.first] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second - 1][stonePos.first] = 2;
				kingPos.second -= 1;
				stonePos.second -= 1;
			}

			else
			{
				return;
			}
		}
	}

	// ↗
	else if (c == "RT")
	{
		if (board[kingPos.second - 1][kingPos.first + 1] == 0)
		{
			board[kingPos.second - 1][kingPos.first + 1] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.second -= 1;
			kingPos.first += 1;
		}

		else if (board[kingPos.second - 1][kingPos.first + 1] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second - 1][stonePos.first + 1] == 0)
			{
				board[kingPos.second - 1][kingPos.first + 1] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second - 1][stonePos.first + 1] = 2;
				kingPos.second -= 1;
				stonePos.second -= 1;
				kingPos.first += 1;
				stonePos.first += 1;
			}

			else
			{
				return;
			}
		}
	}

	// ↖
	else if (c == "LT")
	{
		if (board[kingPos.second - 1][kingPos.first - 1] == 0)
		{
			board[kingPos.second - 1][kingPos.first - 1] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.second -= 1;
			kingPos.first -= 1;
		}

		else if (board[kingPos.second - 1][kingPos.first - 1] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second - 1][stonePos.first - 1] == 0)
			{
				board[kingPos.second - 1][kingPos.first - 1] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second - 1][stonePos.first - 1] = 2;
				kingPos.second -= 1;
				stonePos.second -= 1;
				kingPos.first -= 1;
				stonePos.first -= 1;
			}

			else
			{
				return;
			}
		}
	}

	// ↘
	else if (c == "RB")
	{
		if (board[kingPos.second + 1][kingPos.first + 1] == 0)
		{
			board[kingPos.second + 1][kingPos.first + 1] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.second += 1;
			kingPos.first += 1;
		}

		else if (board[kingPos.second + 1][kingPos.first + 1] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second + 1][stonePos.first + 1] == 0)
			{
				board[kingPos.second + 1][kingPos.first + 1] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second + 1][stonePos.first + 1] = 2;
				kingPos.second += 1;
				stonePos.second += 1;
				kingPos.first += 1;
				stonePos.first += 1;
			}

			else
			{
				return;
			}
		}
	}

	// ↙
	else if (c == "LB")
	{
		if (board[kingPos.second + 1][kingPos.first - 1] == 0)
		{
			board[kingPos.second + 1][kingPos.first - 1] = 1;
			board[kingPos.second][kingPos.first] = 0;
			kingPos.second += 1;
			kingPos.first -= 1;
		}

		else if (board[kingPos.second + 1][kingPos.first - 1] == -1)
		{
			return;
		}

		else
		{
			if (board[stonePos.second + 1][stonePos.first - 1] == 0)
			{
				board[kingPos.second + 1][kingPos.first - 1] = 1;
				board[kingPos.second][kingPos.first] = 0;
				board[stonePos.second + 1][stonePos.first - 1] = 2;
				kingPos.second += 1;
				stonePos.second += 1;
				kingPos.first -= 1;
				stonePos.first -= 1;
			}

			else
			{
				return;
			}
		}
	}
}

void printPos()
{
	printf("king: %d, %d\n", kingPos.second, kingPos.first);
	printf("stone: %d, %d\n", stonePos.second, stonePos.first);
}

void printBoard()
{
	for (int i = 1; i < 9; i++)
	{
		for (int j = 1; j < 9; j++)
		{
			cout << board[i][j] << " ";
		}

		cout << "\n";
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int i = 0; i < 10; i++)
	{
		for (int j = 0; j < 10; j++)
		{
			if (i == 0 || i == 9 || j == 0 || j == 9)
			{
				board[i][j] = -1;
			}

			else
			{
				board[i][j] = 0;
			}
		}
	}

	char input[2];

	cin >> input[0] >> input[1];
	kingPos.first = input[0] - 64;
	kingPos.second = 9 - (input[1] - 48);

	cin >> input[0] >> input[1];
	stonePos.first = input[0] - 64;
	stonePos.second = 9 - (input[1] - 48);

	cin >> N;

	board[kingPos.second][kingPos.first] = 1;
	board[stonePos.second][stonePos.first] = 2;

	//printBoard();

	for (int i = 0; i < N; i++)
	{
		string s;

		cin >> s;
		moveKing(s);

		//printBoard();
	}

	char output[2];

	output[0] = kingPos.first + 64;
	output[1] = 9 - kingPos.second + 48;
	cout << output[0] << output[1] << "\n";

	output[0] = stonePos.first + 64;
	output[1] = 9 - stonePos.second + 48;
	cout << output[0] << output[1] << "\n";

	return 0;
}