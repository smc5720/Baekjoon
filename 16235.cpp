#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>

using namespace std;

// https://www.acmicpc.net/problem/16235

int N, M, K;
int A[11][11];
int field[12][12];

// 나무의 나이가 저장된다.
vector<int> Tree[12][12];

void spring()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			int size;
			size = Tree[i][j].size();

			bool isDead;
			isDead = false;

			// 해당 좌표에 나무가 있다면?
			if (size > 0)
			{
				vector<int> v;

				// 나이가 어린 순서부터 정렬
				sort(Tree[i][j].begin(), Tree[i][j].end());

				for (int k = 0; k < size; k++)
				{
					if (!isDead)
					{
						// 양분이 남아있다면
						if (field[i][j] >= Tree[i][j][k])
						{
							field[i][j] -= Tree[i][j][k];
							v.push_back(Tree[i][j][k] + 1);
						}

						else
						{
							isDead = true;

							int num;
							num = Tree[i][j][k] / 2;
							field[i][j] += num;
						}
					}

					// 없으면 나머지 애들은 양분으로
					else
					{
						int num;
						num = Tree[i][j][k] / 2;
						field[i][j] += num;
					}
				}

				Tree[i][j] = v;
			}
		}
	}
}

void autumn()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			int size;
			size = Tree[i][j].size();

			// 해당 좌표에 나무가 있다면?
			if (size > 0)
			{
				for (int k = 0; k < size; k++)
				{
					if (Tree[i][j][k] / 5 > 0 && Tree[i][j][k] % 5 == 0)
					{
						Tree[i - 1][j - 1].push_back(1);
						Tree[i - 1][j].push_back(1);
						Tree[i - 1][j + 1].push_back(1);
						Tree[i][j - 1].push_back(1);
						Tree[i][j + 1].push_back(1);
						Tree[i + 1][j - 1].push_back(1);
						Tree[i + 1][j].push_back(1);
						Tree[i + 1][j + 1].push_back(1);
					}
				}
			}
		}
	}
}

void winter()
{
	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			field[i][j] += A[i][j];
		}
	}
}

void countTree()
{
	int sum;
	sum = 0;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			int size;
			size = Tree[i][j].size();
			sum += size;
		}
	}

	cout << sum << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M >> K;

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			cin >> A[i][j];
			field[i][j] = 5;
		}
	}

	for (int i = 0; i < M; i++)
	{
		int x, y, z;
		cin >> x >> y >> z;

		Tree[x][y].push_back(z);
	}

	for (int i = 0; i < K; i++)
	{
		spring();
		autumn();
		winter();
	}

	countTree();

	return 0;
}