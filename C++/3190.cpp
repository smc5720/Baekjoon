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

// -1: 장외
//  0: 아무 것도 없음
//  1: 사과 있음
//  2: 뱀이 있음
int board[102][102];
Control ctrl[100];
deque <Snake> snake;

// 1: 상
// 2: 하
// 3: 좌
// 4: 우
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
	// 1초 추가
	timeVal += 1;

	// 몸의 위치가 변하기 전 머리와 꼬리의 위치
	int headRow, headCol;
	int tailRow, tailCol;

	headRow = snake[0].row;
	headCol = snake[0].col;
	tailRow = snake[snake.size() - 1].row;
	tailCol = snake[snake.size() - 1].col;

	// 몸 길이를 늘린다.
	Snake s;

	if (state == 1) // 상
	{
		s.row = headRow - 1;
		s.col = headCol;
	}

	else if (state == 2) // 하
	{
		s.row = headRow + 1;
		s.col = headCol;
	}

	else if (state == 3) // 좌
	{
		s.row = headRow;
		s.col = headCol - 1;
	}

	else if (state == 4) // 우
	{
		s.row = headRow;
		s.col = headCol + 1;
	}

	snake.push_front(s);

	// 0: 아무 것도 없을 때
	if (board[snake[0].row][snake[0].col] == 0)
	{
		// 몸 길이를 줄인다.
		snake.pop_back();
		board[tailRow][tailCol] = 0;
		board[snake[0].row][snake[0].col] = 2;

		return true;
	}

	// 1: 사과가 있을 때
	else if (board[snake[0].row][snake[0].col] == 1)
	{
		// 몸 길이를 줄이지 않는다.
		// snake.pop_back();
		board[snake[0].row][snake[0].col] = 2;

		return true;
	}

	// -1 or 2: 몸이나 벽에 부딫혔을 때
	else if (board[snake[0].row][snake[0].col] == -1 || board[snake[0].row][snake[0].col] == 2)
	{
		return false;
	}
}

void changeDir(char d)
{
	// 뱀이 위를 향할 때
	if (state == 1)
	{
		// 왼쪽으로 회전
		if (d == 'L')
		{
			state = 3;
		}

		// 오른쪽으로 회전
		else if (d == 'D')
		{
			state = 4;
		}
	}

	// 뱀이 아래를 향할 때
	else if (state == 2)
	{
		// 왼쪽으로 회전
		if (d == 'L')
		{
			state = 4;
		}

		// 오른쪽으로 회전
		else if (d == 'D')
		{
			state = 3;
		}
	}

	// 뱀이 왼쪽을 향할 때
	else if (state == 3)
	{
		// 왼쪽으로 회전
		if (d == 'L')
		{
			state = 2;
		}

		// 오른쪽으로 회전
		else if (d == 'D')
		{
			state = 1;
		}
	}

	// 뱀이 오른쪽을 향할 때
	else if (state == 4)
	{
		// 왼쪽으로 회전
		if (d == 'L')
		{
			state = 1;
		}

		// 오른쪽으로 회전
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