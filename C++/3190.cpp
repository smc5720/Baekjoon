#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>

using namespace std;

// https://www.acmicpc.net/problem/3190

struct Snake
{
	int row;
	int col;
};

struct Control
{
	int count;
	char direction;
};

int N, K, L;

// -1: ���
//  0: �ƹ� �͵� ����
//  1: ��� ����
//  2: ���� ����
int board[102][102];
Control ctrl[100];
deque <Snake> snake;

// 1: ��
// 2: ��
// 3: ��
// 4: ��
int state;
int timeVal;

void printBoard()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cout << board[i][j] << " ";
		}

		cout << "\n";
	}

	cout << "\n";
}

bool moveSnake()
{
	// 1�� �߰�
	timeVal += 1;

	// ���� ��ġ�� ���ϱ� �� �Ӹ��� ������ ��ġ
	int headRow, headCol;
	int tailRow, tailCol;

	headRow = snake[0].row;
	headCol = snake[0].col;
	tailRow = snake[snake.size() - 1].row;
	tailCol = snake[snake.size() - 1].col;

	// �� ���̸� �ø���.
	Snake s;

	if (state == 1) // ��
	{
		s.row = headRow - 1;
		s.col = headCol;
	}

	else if (state == 2) // ��
	{
		s.row = headRow + 1;
		s.col = headCol;
	}

	else if (state == 3) // ��
	{
		s.row = headRow;
		s.col = headCol - 1;
	}

	else if (state == 4) // ��
	{
		s.row = headRow;
		s.col = headCol + 1;
	}

	snake.push_front(s);

	// 0: �ƹ� �͵� ���� ��
	if (board[snake[0].row][snake[0].col] == 0)
	{
		// �� ���̸� ���δ�.
		snake.pop_back();
		board[tailRow][tailCol] = 0;
		board[snake[0].row][snake[0].col] = 2;

		return true;
	}

	// 1: ����� ���� ��
	else if (board[snake[0].row][snake[0].col] == 1)
	{
		// �� ���̸� ������ �ʴ´�.
		// snake.pop_back();
		board[snake[0].row][snake[0].col] = 2;

		return true;
	}

	// -1 or 2: ���̳� ���� �΋H���� ��
	else if (board[snake[0].row][snake[0].col] == -1 || board[snake[0].row][snake[0].col] == 2)
	{
		return false;
	}
}

void changeDir(char d)
{
	// ���� ���� ���� ��
	if (state == 1)
	{
		// �������� ȸ��
		if (d == 'L')
		{
			state = 3;
		}

		// ���������� ȸ��
		else if (d == 'D')
		{
			state = 4;
		}
	}

	// ���� �Ʒ��� ���� ��
	else if (state == 2)
	{
		// �������� ȸ��
		if (d == 'L')
		{
			state = 4;
		}

		// ���������� ȸ��
		else if (d == 'D')
		{
			state = 3;
		}
	}

	// ���� ������ ���� ��
	else if (state == 3)
	{
		// �������� ȸ��
		if (d == 'L')
		{
			state = 2;
		}

		// ���������� ȸ��
		else if (d == 'D')
		{
			state = 1;
		}
	}

	// ���� �������� ���� ��
	else if (state == 4)
	{
		// �������� ȸ��
		if (d == 'L')
		{
			state = 1;
		}

		// ���������� ȸ��
		else if (d == 'D')
		{
			state = 2;
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int i = 0; i < 102; i++)
	{
		for (int j = 0; j < 102; j++)
		{
			board[i][j] = -1;
		}
	}

	cin >> N >> K;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			board[i][j] = 0;
		}
	}

	Snake head;
	head.row = 1;
	head.col = 1;

	state = 4;

	snake.push_back(head);

	for (int i = 0; i < K; i++)
	{
		int row, col;
		cin >> row >> col;

		board[row][col] = 1;
	}

	board[1][1] = 2;

	cin >> L;

	for (int i = 0; i < L; i++)
	{
		int cnt;
		char c;

		cin >> cnt >> c;

		ctrl[i].count = cnt;
		ctrl[i].direction = c;
	}

	timeVal = 0;

	// printBoard();

	for (int i = 0; i < L; i++)
	{
		int cnt;
		char dir;

		cnt = ctrl[i].count;
		dir = ctrl[i].direction;

		while (cnt > timeVal)
		{
			// printBoard();

			if (!moveSnake())
			{
				cout << timeVal << "\n";
				exit(0);
			}
		}

		changeDir(dir);

		if (i + 1 == L)
		{
			// printBoard();

			while (moveSnake())
			{
				// printBoard();
			}

			cout << timeVal << "\n";
			exit(0);
		}
	}

	return 0;
}