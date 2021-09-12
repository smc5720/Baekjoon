#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>

using namespace std;

// https://www.acmicpc.net/problem/4307

int T;
int l, n;
int ant[100000];

int bigNum(int num)
{
	if (num > l - num)
	{
		return num;
	}

	else
	{
		return l - num;
	}
}

int smallNum(int num)
{
	if (num > l - num)
	{
		return l - num;
	}

	else
	{
		return num;
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> T;

	for (int i = 0; i < T; i++)
	{
		cin >> l >> n;

		for (int i = 0; i < n; i++)
		{
			cin >> ant[i];
		}

		int shortTime, longTime;
		shortTime = 0;
		longTime = 0;

		for (int i = 0; i < n; i++)
		{
			int sNum, lNum;
			sNum = smallNum(ant[i]);
			lNum = bigNum(ant[i]);

			if (shortTime < sNum)
			{
				shortTime = sNum;
			}

			if (longTime < lNum)
			{
				longTime = lNum;
			}
		}

		cout << shortTime << " " << longTime << "\n";
	}

	return 0;
}