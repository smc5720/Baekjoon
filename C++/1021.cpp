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

// https://www.acmicpc.net/problem/1021

int N, M;
deque <int> q;

void func1()
{
	q.pop_front();
}

void func2(int i)
{
	for (int j = 0; j < i; j++)
	{
		int temp;
		temp = q.front();
		q.pop_front();
		q.push_back(temp);
	}
}

void func3(int i)
{
	for (int j = 0; j < i; j++)
	{
		int temp;
		temp = q.back();
		q.pop_back();
		q.push_front(temp);
	}
}

int findIndex(int n)
{
	int size;
	size = q.size();

	for (int i = 0; i < size; i++)
	{
		if (q[i] == n)
		{
			return i;
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> M;

	for (int i = 1; i <= N; i++)
	{
		q.push_back(i);
	}

	int ans;
	ans = 0;

	for (int i = 0; i < M; i++)
	{
		int n;
		cin >> n;

		if (q.front() == n)
		{
			func1();
		}

		else
		{
			int f2, f3;
			f2 = findIndex(n);
			f3 = q.size() - f2;

			if (f2 > f3)
			{
				ans += f3;
				func3(f3);
				func1();
			}

			else
			{
				ans += f2;
				func2(f2);
				func1();
			}
		}
	}

	cout << ans << "\n";

	return 0;
}