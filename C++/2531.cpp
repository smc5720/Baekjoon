#include <iostream>
#include <math.h>
#include <algorithm>
#include <vector>
#include <string>
#include <deque>
#include <queue>
#include <map>

using namespace std;

// https://www.acmicpc.net/problem/2531

int N, d, k, c;
int sushi[3001];
int arr[30000];
queue <int> q;
int maxAns;
int ans;
bool useCoupon;

void pushSushi(int sh)
{
	q.push(sh);

	if (sushi[sh] == 0)
	{
		ans += 1;
	}

	if (sh == c && !useCoupon)
	{
		useCoupon = true;
	}

	sushi[sh] += 1;
}

void popSushi()
{
	int sh;
	sh = q.front();

	if (sushi[sh] == 1)
	{
		ans -= 1;

		if (sh == c)
		{
			useCoupon = false;
		}
	}

	sushi[sh] -= 1;

	q.pop();
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	maxAns = 0;
	useCoupon = false;

	cin >> N >> d >> k >> c;

	for (int i = 1; i <= d; i++)
	{
		sushi[i] = 0;
	}

	for (int i = 0; i < N; i++)
	{
		cin >> arr[i];

		if (i > N - k)
		{
			q.push(arr[i]);

			if (arr[i] == c)
			{
				useCoupon = true;
			}

			if (sushi[arr[i]] == 0)
			{
				ans += 1;
			}

			sushi[arr[i]] += 1;
		}
	}

	for (int i = 0; i < N; i++)
	{
		int sh;
		sh = arr[i];

		pushSushi(sh);

		if (!useCoupon)
		{
			if (maxAns < ans + 1)
			{
				maxAns = ans + 1;
			}
		}

		else
		{
			if (maxAns < ans)
			{
				maxAns = ans;
			}
		}

		popSushi();
	}

	cout << maxAns << "\n";

	return 0;
}