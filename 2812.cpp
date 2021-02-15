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

// https://www.acmicpc.net/problem/2812

int N, K, top;
string num;
char st[500000];

void printStack()
{
	cout << "stack: ";

	for (int i = 0; i <= top; i++)
	{
		cout << st[i] << " ";
	}

	cout << "\n";
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> N >> K;
	cin >> num;

	int cnt;
	cnt = 0;
	top = 0;

	for (int i = 0; i < N; i++)
	{
		if (i == 0)
		{
			st[top] = num[i];
		}

		else
		{
			while (cnt < K && top >= 0 && st[top] < num[i])
			{
				cnt += 1;
				top -= 1;
			}

			top += 1;
			st[top] = num[i];
		}
	}

	for (int i = 0; i <= top - (K - cnt); i++)
	{
		cout << st[i];
	}

	cout << "\n";

	return 0;
}