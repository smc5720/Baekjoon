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

// https://www.acmicpc.net/problem/2529

int k;
char arr[9];
bool visited[10];
long long maxVal, minVal, cVal;

long long powNum(int n)
{
	long long ans;
	ans = 1;

	for (int i = 0; i < n; i++)
	{
		ans *= 10;
	}

	return ans;
}

bool checkState(int a, int idx, int b)
{
	if (arr[idx] == '<')
	{
		return a < b;
	}

	else
	{
		return a > b;
	}
}

void DFS(int depth, int val)
{
	if (depth < k)
	{
		for (int i = 0; i < 10; i++)
		{
			if (!visited[i])
			{
				if (checkState(val, depth, i))
				{
					// printf("%d %c %d = true\n", val, arr[depth], i);
					visited[i] = true;
					cVal += i * powNum(k - (depth + 1));
					DFS(depth + 1, i);
					visited[i] = false;
					cVal -= i * powNum(k - (depth + 1));
				}
			}
		}
	}

	else
	{
		if (cVal > maxVal)
		{
			maxVal = cVal;
		}

		if (cVal < minVal)
		{
			minVal = cVal;
		}
	}
}

int main()
{
	cin.tie(NULL);
	ios::sync_with_stdio(false);

	cin >> k;

	for (int i = 0; i < 10; i++)
	{
		visited[i] = false;
	}

	for (int i = 0; i < k; i++)
	{
		cin >> arr[i];
	}

	maxVal = 0;
	minVal = 9876543210;
	cVal = 0;

	for (int i = 0; i < 10; i++)
	{
		visited[i] = true;
		cVal += i * powNum(k);
		DFS(0, i);
		visited[i] = false;
		cVal -= i * powNum(k);
	}

	int size;
	size = k - (int)log10(maxVal);

	for (int i = 0; i < size; i++)
	{
		cout << 0;
	}

	cout << maxVal << "\n";

	size = k - (int)log10(minVal);

	for (int i = 0; i < size; i++)
	{
		cout << 0;
	}

	cout << minVal << "\n";

	return 0;
}