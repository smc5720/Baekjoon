#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>

using namespace std;

// https://www.acmicpc.net/problem/2082

int ans[4];
char num[10][15] = { { '#', '#', '#',
					   '#', '.', '#',
					   '#', '.', '#',
					   '#', '.', '#',
					   '#', '#', '#', },

					 { '.', '.', '#',
					   '.', '.', '#',
					   '.', '.', '#',
					   '.', '.', '#',
					   '.', '.', '#', },

					 { '#', '#', '#',
					   '.', '.', '#',
					   '#', '#', '#',
					   '#', '.', '.',
					   '#', '#', '#', },

					 { '#', '#', '#',
					   '.', '.', '#',
					   '#', '#', '#',
					   '.', '.', '#',
					   '#', '#', '#', },

					 { '#', '.', '#',
					   '#', '.', '#',
					   '#', '#', '#',
					   '.', '.', '#',
					   '.', '.', '#', },

					 { '#', '#', '#',
					   '#', '.', '.',
					   '#', '#', '#',
					   '.', '.', '#',
					   '#', '#', '#', },

					 { '#', '#', '#',
					   '#', '.', '.',
					   '#', '#', '#',
					   '#', '.', '#',
					   '#', '#', '#', },

					 { '#', '#', '#',
					   '.', '.', '#',
					   '.', '.', '#',
					   '.', '.', '#',
					   '.', '.', '#', },

					 { '#', '#', '#',
					   '#', '.', '#',
					   '#', '#', '#',
					   '#', '.', '#',
					   '#', '#', '#', },

					 { '#', '#', '#',
					   '#', '.', '#',
					   '#', '#', '#',
					   '.', '.', '#',
					   '#', '#', '#', } };

char timeArr[4][15];

int checkNumber(int n)
{
	if (n == 0)
	{
		for (int i = 0; i < 3; i++)
		{
			bool check;
			check = false;

			for (int j = 0; j < 15; j++)
			{
				if (timeArr[n][j] != num[i][j])
				{
					if (timeArr[n][j] == '#')
					{
						check = true;
						break;
					}
				}
			}

			if (check)
			{
				continue;
			}

			else
			{
				return i;
			}
		}
	}

	else if (n == 1)
	{
		if (ans[0] == 2)
		{
			for (int i = 0; i < 4; i++)
			{
				bool check;
				check = false;

				for (int j = 0; j < 15; j++)
				{
					if (timeArr[n][j] != num[i][j])
					{
						if (timeArr[n][j] == '#')
						{
							check = true;
							break;
						}
					}
				}

				if (check)
				{
					continue;
				}

				else
				{
					return i;
				}
			}
		}

		else
		{
			for (int i = 0; i < 10; i++)
			{
				bool check;
				check = false;

				for (int j = 0; j < 15; j++)
				{
					if (timeArr[n][j] != num[i][j])
					{
						if (timeArr[n][j] == '#')
						{
							check = true;
							break;
						}
					}
				}

				if (check)
				{
					continue;
				}

				else
				{
					return i;
				}
			}
		}
	}

	else if (n == 2)
	{
		for (int i = 0; i < 6; i++)
		{
			bool check;
			check = false;

			for (int j = 0; j < 15; j++)
			{
				if (timeArr[n][j] != num[i][j])
				{
					if (timeArr[n][j] == '#')
					{
						check = true;
						break;
					}
				}
			}

			if (check)
			{
				continue;
			}

			else
			{
				return i;
			}
		}
	}

	else
	{
		for (int i = 0; i < 10; i++)
		{
			bool check;
			check = false;

			for (int j = 0; j < 15; j++)
			{
				if (timeArr[n][j] != num[i][j])
				{
					if (timeArr[n][j] == '#')
					{
						check = true;
						break;
					}
				}
			}

			if (check)
			{
				continue;
			}

			else
			{
				return i;
			}
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	for (int i = 0; i < 5; i++)
	{
		string s[4];

		for (int j = 0; j < 4; j++)
		{
			cin >> s[j];
		}

		for (int j = 0; j < 4; j++)
		{
			for (int m = 0; m < 3; m++)
			{
				timeArr[j][m + i * 3] = s[j][m];
			}
		}
	}

	for (int i = 0; i < 4; i++)
	{
		ans[i] = checkNumber(i);
	}

	printf("%d%d:%d%d\n", ans[0], ans[1], ans[2], ans[3]);

	return 0;
}