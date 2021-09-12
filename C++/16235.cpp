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

// ������ ���̰� ����ȴ�.
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

			// �ش� ��ǥ�� ������ �ִٸ�?
			if (size > 0)
			{
				vector<int> v;

				// ���̰� � �������� ����
				sort(Tree[i][j].begin(), Tree[i][j].end());

				for (int k = 0; k < size; k++)
				{
					if (!isDead)
					{
						// ����� �����ִٸ�
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

					// ������ ������ �ֵ��� �������
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

			// �ش� ��ǥ�� ������ �ִٸ�?
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