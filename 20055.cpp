#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>
#include <stack>
#include <set>

using namespace std;

// https://www.acmicpc.net/problem/20055

int N, K;
int A[201];
int upPoint, downPoint;
bool robot[201];
int ans;

int cntZero()
{
	int cnt;
	cnt = 0;

	for (int i = 1; i <= 2 * N; i++)
	{
		if (A[i] == 0)
		{
			cnt += 1;
		}
	}

	return cnt;
}

void rotateBelt()
{
	//printf("day%d\tRotate Belt\n", ans);

	upPoint -= 1;
	downPoint -= 1;

	if (upPoint == 0)
	{
		upPoint = 2 * N;
	}

	if (downPoint == 0)
	{
		downPoint = 2 * N;
	}

	if (robot[downPoint])
	{
		robot[downPoint] = false;
	}
}

void robotMove()
{
	//printf("day%d\tMove Robot\n", ans);

	int i;
	i = downPoint;

	while (i != upPoint)
	{
		int n;
		n = i + 1;

		if (n == 2 * N + 1)
		{
			n = 1;
		}

		if (robot[i] && A[n] > 0 && !robot[n])
		{
			robot[i] = false;
			robot[n] = true;
			A[n] -= 1;

			if (n == downPoint)
			{
				robot[n] = false;
			}
		}

		i -= 1;

		if (i == 0)
		{
			i = 2 * N;
		}
	}

	int n;
	n = i + 1;

	if (n == 2 * N + 1)
	{
		n = 1;
	}

	if (robot[i] && A[n] > 0 && !robot[n])
	{
		robot[i] = false;
		robot[n] = true;
		A[n] -= 1;

		if (n == downPoint)
		{
			robot[n] = false;
		}
	}
}

void robotOn()
{
	//printf("day%d\tRobot On\n", ans);

	if (!robot[upPoint] && A[upPoint] > 0)
	{
		A[upPoint] -= 1;
		robot[upPoint] = true;
	}
}

void printBelt()
{
	int i = upPoint;

	while (i != downPoint)
	{
		if (robot[i])
		{
			cout << "*\t";
		}

		else
		{
			cout << " \t";
		}

		i += 1;

		if (i == 2 * N + 1)
		{
			i = 1;
		}

		if (i == downPoint)
		{
			if (robot[i])
			{
				cout << "*\t";
			}

			else
			{
				cout << " \t";
			}
		}
	}

	cout << "\n";

	i = upPoint;

	while (i != downPoint)
	{
		cout << A[i] << "\t";

		i += 1;

		if (i == 2 * N + 1)
		{
			i = 1;
		}

		if (i == downPoint)
		{
			cout << A[i] << "\t";
		}
	}

	cout << "\n";

	i = upPoint - 1;

	if (i == 0)
	{
		i = 2 * N;
	}

	while (i != downPoint)
	{
		cout << A[i] << "\t";

		i -= 1;

		if (i == 0)
		{
			i = 2 * N;
		}
	}

	cout << "\n";

	i = upPoint - 1;

	if (i == 0)
	{
		i = 2 * N;
	}

	while (i != downPoint)
	{
		if (robot[i])
		{
			cout << "*\t";
		}

		else
		{
			cout << " \t";
		}

		i -= 1;

		if (i == 0)
		{
			i = 2 * N;
		}
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;

	upPoint = 1;
	downPoint = N;

	for (int i = 1; i <= 2 * N; i++)
	{
		cin >> A[i];
		robot[i] = false;
	}

	//printBelt();
	ans = 0;

	while (cntZero() < K)
	{
		ans += 1;
		rotateBelt();
		//printBelt();

		robotMove();
		//printBelt();

		robotOn();
		//printBelt();
	}

	cout << ans << "\n";

	return 0;
}